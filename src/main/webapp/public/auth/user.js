'use strict';

var mongoose = require('mongoose');
var bcrypt = require('bcryptjs');
var moment = require('moment');
var jwt = require('jwt-simple');

var tokenSecret = 'your unique secret';

//mongoose.connect('mongodb://128.199.153.151:27017/test');
mongoose.connect('mongodb://localhost:27017/test');

var userSchema = new mongoose.Schema
({
	name: { type: String, trim: true, required: true },
	email: { type: String, unique: true, lowercase: true, trim: true },
	password: String
});

userSchema.pre('save', function(next)
{
	var user = this;
	if (!user.isModified('password')) return next();
	bcrypt.genSalt(10, function(err, salt) 
	{
		if (err) return next(err);
		bcrypt.hash(user.password, salt, function(err, hash) 
		{
			if (err) return next(err);
			user.password = hash;
			console.log("userSchema password hash : %s ", hash );
			console.log("userSchema user.password : %s ", user.password );
			next();
		});
	});
});

userSchema.methods.comparePassword = function(candidatePassword, cb) 
{
	bcrypt.compare(candidatePassword, this.password, function(err, isMatch) 
	{
		if (err) return cb(err);
		cb(null, isMatch);
	});
};

var User = mongoose.model('User', userSchema);


exports.signup = function(req, res, next)
{
	var user = new User({
		name: req.body.name,
		email: req.body.email,
		password: req.body.password
	});
	user.save(function(err) 
	{
		if (err) return next(err);
		
		console.log("signup user.password : %s ", user.password );
		res.send(200);
	});
};

exports.login = function(req, res, next)
{
	User.findOne({ email: req.body.email }, function(err, user)
	{
		if (!user) return res.send(401, 'User does not exist');
		
		user.comparePassword(req.body.password, function(err, isMatch) 
		{
			if (!isMatch) return res.send(401, 'Invalid email and/or password');
			
			var token = createJwtToken(user);
			res.send({ token: token });
		});
	});
};

exports.users = function(req, res, next)
{
	if (!req.query.email) 
	{
		return res.send(400, { message: 'Email parameter is required.' });
	}

	User.findOne({ email: req.query.email }, function(err, user)
	{
		if (err) return next(err);
		res.send({ available: !user });
	});
};

function createJwtToken(user)
{
	var payload = {
		user: user,
		iat: new Date().getTime(),
		exp: moment().add('days', 7).valueOf()
	};
	return jwt.encode(payload, tokenSecret);
}
