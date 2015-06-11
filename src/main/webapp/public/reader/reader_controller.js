'use strict';

web.controller('ReaderCtrl', ['$scope', 'ReaderFactory', 'CodezFactory', '$location',  '$filter', '$routeParams',  '$alert',
   	function ($scope, ReaderFactory, CodezFactory, $location, $filter, $routeParams,  $alert) 
{
	$scope.location = $location;
	$scope.currentPage = 0;
    $scope.pageSize = 20;
    $scope.datas = [];
    $scope.qq = '';

    $scope.getData = function () 
    {
      return $filter('filter')($scope.datas, $scope.qq);
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }

	$scope.createReader = function () 
    {
		/* http */
		ReaderFactory.create( $routeParams.husId, $scope.room )
		.success(function(ok) 
		{
			$scope.reader = {};
			$scope.all();
		});

    };
   
    $scope.editReader = function ( id ) 
    {
    	ReaderFactory.show( $routeParams.husId, id )
    	.success(function(data) 
    	{
    		$scope.reader = data;
    		//$scope.all();
    	});
    };
    
    $scope.updateReader = function () 
    {
    	ReaderFactory.update( $routeParams.husId, $scope.reader )
        .success(function(data) 
    	{
        	$scope.reader = {};
        	$scope.all();
    	});
    };
    
    $scope.deleteReader = function (readerId) 
    {
    	ReaderFactory.remove( $routeParams.husId, readerId )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.reader = {};
    	$scope.all();
    };
    
 	// get all
    $scope.all = function ( )
	{
    	ReaderFactory.all( $routeParams.husId )
		.success(function (data)
		{			
			$scope.readers = data;	
			$scope.datas = data;	// paging 관련	
			$scope.reader = { husId: $routeParams.husId };
		});
		
	    // 주거형태 공통코드 추출
	    CodezFactory.detailCodes( 'A0002' )
	    .success(function (data)
	    {
	    	$scope.codes = data;
	    });

	}
  
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	   
}]);


