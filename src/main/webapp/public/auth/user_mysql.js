'use strict';

//var mysql           =        	require('mysql');
var bcrypt 			= 			require('bcryptjs');
var moment 			= 			require('moment');
var jwt	 			= 			require('jwt-simple');

var tokenSecret = 'your unique secret';
/*

function  comparePassword(candidatePassword,  cb) 
{
	bcrypt.compare(candidatePassword, this.password, function(err, isMatch) 
	{
		if (err) return cb(err);
		cb(null, isMatch);
	});
};
*/

function createJwtToken(user)
{
	var payload = {
		user: user,
		iat: new Date().getTime(),
		exp: moment().add('days', 7).valueOf()
	};
	return jwt.encode(payload, tokenSecret);
}

exports.signup = function(req, res, next)
{
	var input = JSON.parse(JSON.stringify(req.body));
	
	bcrypt.genSalt(10, function(err, salt) 
	{
		if (err) return next(err);
		
		bcrypt.hash(input.password, salt, function(err, hash) 
		{
			if (err) return next(err);
			
			input.password = hash;
		});
		bcrypt.hash(input.confirmkey, salt, function(err, hash) 
		{
			if (err) return next(err);
			
			input.confirmkey = hash;
		});
	});	
					
	req.getConnection(function(err, connection)
	{
		var user = { 			
			'email'		:	input.email,
			'password' 	:	input.password,			
			'confirmkey':	input.confirmkey
		};

		connection.beginTransaction(function(err) 
		{
			if (err) {
				throw err;
			}		
			
			connection.query("INSERT INTO users SET ?", [user], function(err, rows)
			{
				if(err)
				{
					console.log("Error Selecting : %s ", err );
					connection.rollback(function () 
					{
                        console.error('rollback error');
                        throw err;
                    });
				}

				connection.commit(function (err) 
				{
					if (err) 
					{
						console.error(err);
						connection.rollback(function ()
						{
						   console.error('rollback error');
						   throw err;
						});
					}// if err
					
					console.log("signup user : %s ", user.password );
					console.log("signup rows : %s ", rows.password );
					
					res.send(200, user);
					//res.end(JSON.stringify(rows));

				});// commit

			});			
		});
	});	
};

exports.login = function(req, res, next)
{
	var email = req.body.email;
	console.log("signup login : %s ", email );
	req.getConnection(function(err, connection)
	{
		connection.query("SELECT * FROM  users WHERE email = ?", [email], function(err, rows)
		{
			if(!rows)
			{
				console.log("Error Selecting : %s ",err );
				return res.send(401, 'User does not exist');
			}
			else
			{
				console.log("signup rows[0].password : %s ", rows[0].password );
				//comparePassword(req.body.password, function(err, isMatch) 
				bcrypt.compare(req.body.password, rows[0].password, function(err, isMatch) 
				{
					if (!isMatch) return res.send(401, 'Invalid email and/or password');
					
					var token = createJwtToken(rows[0]);
					res.send({ token: token });
				});
			}
		});
	});	
};

exports.users = function(req, res, next)
{
	var email = req.query.email;

	if (!email) 
	{
		return res.send(400, { message: 'Email parameter is required.' });
	}
	
	req.getConnection(function(err, connection)
	{
		connection.query("SELECT * FROM  users WHERE email = ?", [email], function(err, rows)
		{
			if(err)
			{
				console.log("Error Selecting : %s ",err );
				return next(err);
			}
			console.log("email Selecting : %s ",rows[0] );
			res.send({ available: !rows[0] });
			
		});
	});	
};

//connection.end();



