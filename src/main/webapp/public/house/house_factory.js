'use strict';

web.factory('HouseFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/house';

	function getUrl() 
	{
		return ENDPOINT_URI + path;
	};
	
	function getUrlForId( comId ) 
	{
		return getUrl() + "/" + comId;
	};
	
	function getPathForId( comId, id ) 
	{
		return getUrl() + "/" + comId +"/"+ id;
	};
	
	service.all = function( comId )
	{
   		return $http.get( getUrlForId( comId ) );
   	};

   	service.show = function ( comId, house ) 
   	{
   		return $http.get( getPathForId( comId, house.id ) );
   	};
   	
   	service.create = function ( comId, house )
   	{
   		return $http.post( getUrlForId( comId ), house );
   	};

   	service.update = function ( comId, house ) 
   	{
   		return $http.put( getPathForId( comId, house.id ), house );
   	};
   	
   	service.toggle = function ( house ) 
   	{
   		return $http.put( getUrlForId( house.isIng ), house );
   	};
   	
   	service.del = function ( comId, house ) 
   	{
   		return $http.delete( getPathForId( comId, house.id ) );
   	};
  
   	
   	return service;
}]);


web.factory('HouseBizFactory', ['$http', function($http)
{
	var service = this;
	var ENDPOINT_URI = ''; //'http://localhost:5080';
 	var path = '/biz/house';

 	function getUrl() 
 	{
 		return ENDPOINT_URI + path;
 	};
 	
 	function getUrlForId( comId ) 
 	{
 		return getUrl() + "/" + comId;
 	};
 	
 	function getPathForId( comId, id ) 
 	{
 		return getUrl() + "/" + comId +"/"+ id;
 	};
 	
 	service.all = function( comId )
 	{
 		return $http.get( getUrlForId( comId ) );
	};

	///////////////////////////////
	service.show = function ( comId, id ) 
	{
		return $http.get( getPathForId( comId, id ) );
	};
    //////////////////////	
	///////////////////////
	service.create = function ( comId, house )
	{
		return $http.post( getUrlForId( comId ), house );
	};
	/////////////////////////////
	
	service.update = function ( comId, house ) 
	{
		return $http.put( getPathForId( comId, house.id ), house );
	};
    	
   	service.toggle = function ( house ) 
   	{
   		return $http.put( getUrlForId( house.isIng ), house );
   	};
///////////////////////
	service.del = function ( comId, house ) 
	{
		return $http.delete( getPathForId( comId, house.id ) );
	};
///////////////////////
    	
	return service;
 }]);

