'use strict';

web.factory('CalendarFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/calendar';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}

	service.all = function(  )
	{
	   	return $http.get( getUrl() );
   	};

   	return service;
}]);


web.factory('CalendarBizFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/biz/calendar';
	
	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}

	service.all = function( id  )
	{
	   	return $http.get( getUrl()+'/'+id );
   	};

   	return service;
}]);