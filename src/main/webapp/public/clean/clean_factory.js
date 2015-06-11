'use strict';

web.factory('CleanFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/clean';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}
	
	function getUrlForId( id ) 
	{
		return getUrl( ) +"/"+ id;
	}
	
	function getUrlForIdCode( code ) 
 	{
 		return getUrl( ) +"/ing/"+ code;
 	}
	
	service.all = function(  )
	{
	   	return $http.get( getUrl() );
   	};
   	
   	service.wheres = function( code )
	{
	   	return $http.get( getUrl()+'/where/'+code );
   	};

   	service.show = function ( cleanId ) 
   	{
   		return $http.get( getUrlForId( cleanId ) );
   	};
   	
   	service.create = function ( room ) 
   	{
   		return $http.post( getUrl( ), room );
   	};
   	   	
   	service.update = function ( clean ) 
   	{
   		return $http.put( getUrlForId( clean.id ), clean );
   	};
   	
   	service.clickIng = function ( clean, code )
   	{
   		return $http.put( getUrlForIdCode( code ), clean );
   	};
   	
    	
   	return service;
}]);


web.factory('CleanBizFactory', ['$http', function($http)
{
	var service = this;
	var ENDPOINT_URI = ''; //'http://localhost:5080';
 	var path = '/biz/clean';
 	
 	function getUrl() 
 	{
 		return ENDPOINT_URI + path;
 	}
 	
 	function getUrlForId( id ) 
 	{
 		return getUrl( ) +"/"+ id;
 	}
 	 	
	function getUrlForIdCode( id, code ) 
 	{
 		return getUrl( ) +"/"+ id +"/"+ code;
 	}
	
 	service.all = function( id )
 	{
 	   	return $http.get( getUrlForId( id ) );
	};
    	
	service.show = function ( cleanId ) 
	{
		return $http.get( getUrlForId( cleanId ) );
	};
	
   	service.create = function ( room ) 
   	{
   		return $http.post( getUrl( ), room );
   	};
   	
	service.update = function ( clean ) 
	{
		return $http.put( getUrlForId( clean.id ), clean );
	};
    	
   	service.clickIng = function ( clean, code )
   	{
   		return $http.put( getUrlForIdCode( clean.id, code ) );
   	};
   	
	return service;
 }]);


