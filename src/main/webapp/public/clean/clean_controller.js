'use strict';


web.controller('CleanCtrl', ['$scope', 'CleanFactory', 'CodezFactory', 'UserFactory', '$location',  '$filter', '$routeParams', '$alert',
   	function ($scope, CleanFactory, CodezFactory, UserFactory, $location, $filter, $routeParams, $alert) 
{
	$scope.currentPage = 0;
    $scope.pageSize = 50;
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
    
    $scope.editClean = function ( cleanId ) 
    {
    	CleanFactory.show( cleanId )
        .success(function(data) 
    	{
         	//$scope.inputDate = data.inputDate;
        	$scope.hopeDate = data.hopeDate;
        	$scope.confirmDate = data.confirmDate;
        	$scope.clean = data;       
        	//$scope.all();
    	});
    };
    
    $scope.updateClean = function () 
    {
    	//$scope.clean.inputDate =  $filter("date")($scope.clean.inputDate, 'yyyy-MM-dd');
    	$scope.clean.hopeDate = $filter("date")($scope.clean.hopeDate, 'yyyy-MM-dd');
    	$scope.clean.confirmDate = $filter("date")($scope.clean.confirmDate, 'yyyy-MM-dd');
     	
    	CleanFactory.update( $scope.clean )
        .success(function(data) 
    	{
        	$scope.clean = [];
        	//$scope.inputDate = "";
        	$scope.hopeDate = "";
        	$scope.confirmDate = "";	
        	$scope.all();
    	});
    };
    
    $scope.actionIng = function ( clean, ingCode )
    {
    	if( ingCode == '200' )
		{
    		if( clean.charge == '' || clean.confirmDate == '' )
			{
    			$alert
				({
					title: '진행보류!! ',
					content: '청소담당 및 확정일자를 입력해주세요.^^',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 5
				});
  
    			$scope.editClean( clean.id );
    			
        		return;
			}

		}
    	
    	CleanFactory.clickIng(  clean, ingCode )
    	.success(function(data)
    	{
    		$scope.clean = [];
    		$scope.all();
    	});    	
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.clean = [];
    	$scope.qq = "";
    	//$scope.inputDate = "";
    	$scope.hopeDate = "";
    	$scope.confirmDate = "";	
    	$scope.all();
    };
    
 	// get all
    $scope.all = function ( )
	{
    	CleanFactory.all( )
		.success(function (data)
		{			
			$scope.cleans = data;	
			$scope.datas = data;	// paging 관련	
		});
		
    	 // 주거형태 공통코드 추출
        CodezFactory.detailCodes( 'A0002' )
        .success(function (data)
        {
        	$scope.livings = data;
        });
    	
    	 // 진행과정 공통코드 추출
        CodezFactory.detailCodes( 'A0003' )
        .success(function (data)
        {
        	$scope.ings = data;        	
        });
        
        UserFactory.works( '03' )  // 입주청소 담당자
        .success(function (data)
		{
        	$scope.chargezs = data;
		});
        
	}
    
    $scope.getExcel = function()
    {
    	CleanFactory.all( )
		.success(function (data)
		{			
			$scope.excels = data;	
			
		    alasql('SELECT * INTO XLSX("excel_order.xlsx",{headers:true}) FROM ?',[$scope.excels]);

		});
    }
    
    $scope.getWhere = function( day )
    {
    	CleanFactory.wheres( day )
    	.success(function (data)
    	{
    		$scope.cleans = data;	
			$scope.datas = data;	// paging 관련	
    	});
    	
    	 // 주거형태 공통코드 추출
        CodezFactory.detailCodes( 'A0002' )
        .success(function (data)
        {
        	$scope.livings = data;
        });
    	
    	 // 진행과정 공통코드 추출
        CodezFactory.detailCodes( 'A0003' )
        .success(function (data)
        {
        	$scope.ings = data;        	
        });
        
        UserFactory.works( '03' )  // 입주청소 담당자
        .success(function (data)
		{
        	$scope.chargezs = data;
		});
        
    }
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);


web.controller('CleanCListCtrl', ['$scope',  'CleanBizFactory', '$location',  '$filter', '$routeParams',
    function ($scope, CleanBizFactory, $location, $filter, $routeParams) 
{
	$scope.location = $location;
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.datas = [];
    $scope.qq = '';

    $scope.getData = function () 
    {
      return $filter('filter')($scope.datas, $scope.qq)
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
	// callback for ng-click 'editUser':
    $scope.editClean = function ( cleanId ) 
    {
        $location.path('/clean-detail/' + cleanId);
    };

    $scope.actionIng = function ( clean, ingCode )
    {
    	CleanBizFactory.clickIng(  clean, ingCode )
    	.success(function(data)
    	{
    		$scope.clean = [];
    		$scope.all();
    	});    	
    };
    
    // get all
    $scope.all = function ()
	{
    	CleanBizFactory.all( $routeParams.comId )
		.success(function (data)
		{
			$scope.cleans = data;	
			$scope.datas = data;	// paging 관련	
		});
	}
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);



web.controller('CleanUListCtrl', ['$scope',  'CleanBizFactory', '$location',  '$filter', '$routeParams',
    function ($scope, CleanBizFactory, $location, $filter, $routeParams) 
{
	$scope.location = $location;
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.datas = [];
    $scope.qq = '';

    $scope.getData = function () 
    {
      return $filter('filter')($scope.datas, $scope.qq)
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
	// callback for ng-click 'editUser':
    $scope.editClean = function (cleanId) 
    {
        $location.path('/clean-detail/' + cleanId);
    };

    $scope.actionIng = function ( clean, ingCode )
    {
    	CleanBizFactory.clickIng(  clean, ingCode )
    	.success(function(data)
    	{
    		$scope.clean = [];
    		$scope.all();
    	});    	
    };
 
    // get all
    $scope.all = function ()
	{
    	CleanBizFactory.all( $routeParams.userId )
		.success(function (data)
		{
			$scope.cleans = data;	
			$scope.datas = data;	// paging 관련	
		});
	}
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);

