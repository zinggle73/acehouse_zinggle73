'use strict';

web.factory('ContractFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/contract';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}
	
	function getUrlForId( id ) 
	{
		return getUrl( ) +"/"+ id;
	}

	service.all = function( )
	{
   		return $http.get( getUrl() );
   	};

   	service.show = function ( id ) 
   	{
   		return $http.get( getUrlForId( id ) );
   	};

   	service.create = function ( contract )
   	{
   		return $http.post( getUrl(), contract );
   	};

   	service.update = function ( contract ) 
   	{
   		return $http.put( getUrlForId( contract.id ), contract );
   	};
   	
	service.remove = function ( id )
	{
		return $http.delete( getUrlForId( id ) );
	};


   	return service;
}]);

