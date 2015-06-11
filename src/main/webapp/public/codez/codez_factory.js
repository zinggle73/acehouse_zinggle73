'use strict';

web.factory('CodezFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/codez';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}
	
	function getUrlForId( codezId ) 
	{
		return getUrl( ) +"/"+ codezId;
	}
	
	function getUrlForOr( codezId ) 
	{
		return getUrl( ) +"/YN/"+ codezId;
	}
	
	service.all = function( )
	{
   		return $http.get( getUrl() );
   	};
   	
   	service.groupAll = function( )
	{
   		return $http.get( getUrl() );
   	};

   	service.show = function ( codezId ) 
   	{
   		return $http.get( getUrlForId( codezId ) );
   	};

   	service.create = function ( codez )
   	{
   		return $http.post( getUrl(), codez );
   	};

   	service.toggle = function ( codez ) 
   	{
   		return $http.put( getUrlForOr( codez.codeOr ), codez );
   	};
   	
   	service.update = function ( codez ) 
   	{
   		return $http.put( getUrlForId( codez.id ), codez );
   	};
   	
	service.remove = function ( codezId )
	{
		return $http.delete( getUrlForId( codezId ) );
	};
	
   	service.groups = function ( )
   	{
   		return $http.get( getUrl() + '/group' );
   	};
   	
   	service.detailCodes = function ( groupCode )
   	{
   		return $http.get( getUrl() + '/detailCodes/' + groupCode );
   	};
   	
   	return service;
}]);

