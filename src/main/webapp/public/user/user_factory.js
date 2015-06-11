'use strict';


web.factory('AdminFactory', ['$http', function($http)
{
	var service = this;
	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}

	function getUrlForId( userId ) 
	{
		return getUrl( ) +"/"+ userId;
	}
	
	service.all = function( )
	{
		//console.log(ENDPOINT_URI + path);
		return $http.get( getUrl() );
	};

	service.works = function ( field ) 
	{
		return $http.get( getUrl() + '/workfield/' + field );
	};
	
	service.show = function ( userId ) 
	{
		return $http.get( getUrlForId( userId ) );
	};
	
	service.create = function ( user )
	{
		return $http.post( getUrl(), user );
	};

	service.update = function ( user ) 
	{
		return $http.put( getUrlForId( user.id ), user );
	};

	service.remove = function ( userId )
	{
		return $http.delete( getUrlForId( userId ) );
	};
  
	return service;
}]);



web.factory('UserFactory', ['$http', function($http)
 {
 	var service = this;
 	var ENDPOINT_URI = ''; //'http://localhost:5080';
 	var path = '/biz/user';
 	
 	function getUrl() 
 	{
 		return ENDPOINT_URI + path;
 	}

 	function getUrlForId( userId ) 
 	{
 		return getUrl( ) +"/"+ userId;
 	}
 	
 	service.all = function( )
 	{
 		//console.log(ENDPOINT_URI + path);
 		return $http.get( getUrl() );
 	};

 	service.works = function ( field ) 
 	{
 		return $http.get( getUrl() + '/workfield/' + field );
 	};
 	
 	service.show = function ( userId ) 
 	{
 		return $http.get( getUrlForId( userId ) );
 	};

 	service.create = function ( user )
 	{
 		return $http.post( getUrl(), user );
 	};

 	service.update = function ( user ) 
 	{
 		return $http.put( getUrlForId( user.id ), user );
 	};

 	service.remove = function ( userId )
 	{
 		return $http.delete( getUrlForId( userId ) );
 	};
   
 	return service;
 }]);


