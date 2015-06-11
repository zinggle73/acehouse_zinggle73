'use strict';

web.controller('ContractCtrl', ['$scope', 'ContractFactory', '$location',  '$filter', 'CompanyFactory', 'CodezFactory', '$alert',
   	function ($scope, ContractFactory, $location, $filter, CompanyFactory, CodezFactory, $alert) 
{
	$scope.currentPage = 0;
    $scope.pageSize = 20;
    $scope.datas = [];
    $scope.qq = '';
    $scope.addMode = false;
    
    $scope.toggleAddMode = function ()
    {
    	$scope.contract = {};
        $scope.addMode = !$scope.addMode;
    };

    $scope.toggleEditMode = function ( contract )
    {
    	contract.editMode = !contract.editMode;
    };

    
    $scope.getData = function () 
    {
      return $filter('filter')($scope.datas, $scope.qq)
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
	$scope.createContract = function () 
    {
		/* http */
		ContractFactory.create($scope.contract)
		.success(function(ok) 
		{			
			$scope.contract = {};
			$scope.all();
			$alert
			({
				title: '저장!! ',
				content: ' SAVE에 성공하셨습니다..^^',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
		});

    };
   
    $scope.editContract = function ( id ) 
    {
    	ContractFactory.show( id )
    	.success(function(data) 
    	{
    		$scope.contract = data;
    	});
    };
    
    $scope.updateContract = function ( contract ) 
    {
    	ContractFactory.update( contract )
        .success(function(data) 
    	{
        	$scope.all();
        	
        	$alert
			({
				title: '업데이트!! ',
				content: ' 업데이트에 성공하셨습니다..^^',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
    	});
    };
    
    $scope.deleteContract = function ( id ) 
    {
    	ContractFactory.remove( id )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.contract = {};
    	//$scope.all();
    };
    
 	    // get all
    $scope.all = function ()
	{
    	ContractFactory.all()
		.success(function (data)
		{
			$scope.contracts = data;	
			$scope.datas = data;	// paging 관련	
		});
                   
    	CompanyFactory.all()
    	.success(function ( data )
    	{
    		$scope.coms = data;
    	});
    	
    	// 지역구 공통코드 추출
        CodezFactory.detailCodes( 'A0005' )
        .success(function (data)
        {
        	$scope.locals = data;
        });
        
	    // 주거형태 공통코드 추출
	    CodezFactory.detailCodes( 'A0002' )
	    .success(function (data)
	    {
	    	$scope.codes = data;
	    });
	    
	    var d = new Date();
		var n = d.getFullYear();
		
        $scope.years = 
        [			
          	{value : (n-1)},
          	{value : n},
          	{value : (n+1)},
          	{value : (n+2)}
        ];
            
    	
	}
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	

    
}]);


