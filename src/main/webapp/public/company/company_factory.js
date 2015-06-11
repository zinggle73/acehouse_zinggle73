'use strict';

web.factory('CompanyFactory', ['$http', function($http)
{
   	var service = this;
   	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/admin/company';

	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}
	
	function getUrlForId( comId ) 
	{
		return getUrl( ) +"/"+ comId;
	}

	service.all = function( )
	{
   		return $http.get( getUrl() );
   	};

	service.emptyAll = function( )
	{
   		return $http.get( getUrl()+"/empty" );
   	};
   	
   	service.show = function ( comId ) 
   	{
   		return $http.get( getUrlForId( comId ) );
   	};
 
   	service.create = function ( company )
   	{
   		return $http.post( getUrl(), company );
   	};

   	service.update = function ( company ) 
   	{
   		return $http.put( getUrlForId( company.id ), company );
   	};
   	
	service.remove = function ( comId )
	{
		return $http.delete( getUrlForId( comId ) );
	};
   	
   	return service;
}]);



web.factory('CompanyBizFactory', ['$http', function($http)
{
	var service = this;
	var ENDPOINT_URI = ''; //'http://localhost:5080';
	var path = '/biz/company';

	function getUrl() 
	{
		return ENDPOINT_URI + path;
	}
	
	function getUrlForId( id ) 
	{
		return getUrl( ) +"/"+ id;
	}
	
  	service.all = function( comId )
	{
  		return $http.get( getUrlForId( comId ) );
  	};

  	service.show = function ( comId ) 
  	{
  		return $http.get( getUrlForId( "fetch/"+comId ) );
  	};
  	
  	service.update = function ( company ) 
  	{
  		return $http.put( getUrlForId( company.id ), company );
  	};
      
/*
  	service.all = function( )
	{
  		return $http.get( getUrl() );
  	};
  	service.create = function ( company )
  	{
  		return $http.post( getUrl(), company );
  	};
	
   	service.remove = function ( comId )
   	{
   		return $http.delete( getUrlForId( comId ) );
   	};
*/
	return service;
}]);