'use strict';

web.factory('RoomFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/room';
	
	function getUrl()
	{
		return ENDPOINT_URI + path;
	};
	
	function getUrlForId( husId ) 
	{
		return getUrl() + "/" + husId;
	};
	
	function getPathForId( husId, id ) 
	{
		return getUrl() + "/" + husId +"/"+ id;
	};
	

	service.all = function( husId )
	{
   		return $http.get( getUrlForId( husId ) );
   	};

   	service.show = function ( husId, id ) 
   	{
   		return $http.get( getPathForId( husId, id )  );
   	};
   	
   	service.create = function ( husId, room )
   	{
   		return $http.post( getUrlForId(husId), room );
   	};

   	service.toggle = function ( room ) 
   	{
   		return $http.put( getUrlForId( room.isZero ), room );
   	};
   	
   	service.update = function ( husId, room ) 
   	{
   		return $http.put( getPathForId( husId, room.id ), room );
   	};
   	
	service.remove = function ( husId, id )
	{
		return $http.delete( getPathForId( husId, id ) );
	};
   	
   	return service;
}]);



web.factory('RoomBizFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/biz/room';
	
	function getUrl()
	{
		return ENDPOINT_URI + path;
	};
	
	function getUrlForId( husId ) 
	{
		return getUrl() + "/" + husId;
	};
	
	function getPathForId( husId, id ) 
	{
		return getUrl() + "/" + husId +"/"+ id;
	};
       
	service.all = function( husId )
	{
   		return $http.get( getUrlForId( husId ) );
   	};

   	service.show = function ( husId, id ) 
   	{
   		return $http.get( getPathForId( husId, id )  );
   	};
   	
   	service.create = function ( husId, room )
   	{
   		return $http.post( getUrlForId(husId), room );
   	};

   	service.toggle = function ( room ) 
   	{
   		return $http.put( getUrlForId( room.isZero ), room );
   	};
   	
   	service.update = function ( husId, room ) 
   	{
   		return $http.put( getPathForId( husId, room.id ), room );
   	};
   	
	service.remove = function ( husId, roomId )
	{
		return $http.delete( getPathForId( husId, roomId ) );
	};
               	
	return service;
}]);