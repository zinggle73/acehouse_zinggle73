'use strict';

web.controller('HouseCtrl', ['$scope', 'HouseFactory', 'CompanyFactory', '$location',  '$filter', 'CodezFactory', '$routeParams',
   	function ($scope, HouseFactory, CompanyFactory, $location, $filter, CodezFactory, $routeParams) 
{
	$scope.location = $location;
	$scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.datas = [];
    $scope.qq = '';

    $scope.getData = function () 
    {
      return $filter('filter')( $scope.datas, $scope.qq );
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil( $scope.getData().length/$scope.pageSize );                
    }
    
	$scope.createHouse = function () 
    {
		/* http */
		HouseFactory.create( $routeParams.comId, $scope.house )
		.success(function( ok ) 
		{
			$scope.house = {};
			$scope.all();
		});

    };
   
    $scope.editHouse = function ( house ) 
    {
    	HouseFactory.show( $routeParams.comId, house )
    	.success(function( data ) 
    	{
    		$scope.house = data;
    		//$scope.all();
    	});
    };
    
    $scope.updateHouse = function () 
    {
    	HouseFactory.update( $scope.house )
        .success(function( data ) 
    	{
        	$scope.house = {};
        	$scope.all();
    	});
    };
    
      
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.house = {};
    	$scope.all();
    };
    
 	// get all
    $scope.all = function ()
	{
    	HouseFactory.all( $routeParams.comId )
		.success(function ( data )
		{
			$scope.houses = data;				
			$scope.datas = data;	// paging 관련	
			$scope.house = { comId: $routeParams.comId };
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
	}
    
    // 주거형태 공통코드 추출
    //CodezFactory.detailCodes( 'A0002' )
    //.success(function (data)
    //{
    //	$scope.codes = data;
    //});
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	

    
}]);



web.controller('HouseDetailCtrl', ['$scope', '$routeParams', '$location', 'HouseBizFactory', 'CodezFactory', '$alert', '$filter', 'RoomBizFactory', 'CleanBizFactory',
	function ($scope, $routeParams, $location, HouseBizFactory, CodezFactory, $alert, $filter, RoomBizFactory, CleanBizFactory) 
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

    $scope.zeroToggle = function( room )
    {
    	/*  입주청소 진행여부에 따라 공실체크 작동금지 
    	if(room.isZero == 'N')
		{
    		if( room.isIng && room.isIng != '500' )
			{
    			$alert
    			({
    				title: '경고!! ',
    				content: '입주청소의 진행상황이 완료후 진행하여 주시기 바랍니다.',
    				animation: 'fadeZoomFadeDown',
    				type: 'material',
    				duration: 10
    			});
    			return;
			}
		}
    	*/
    	RoomBizFactory.toggle( room )
        .success(function(data) 
    	{
         	//$scope.all();
        	$scope.showDetail();
    	});
    };
    
    $scope.createClean = function( room )
    {
    	room.hopeDate = $filter("date")(room.hopeDate, 'yyyy-MM-dd');
    	if( room.hopeDate == '' || room.hopeDate == null )
		{
     		$alert
			({
				title: '!! ',
				content: '  희망일자를 입력해주세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 5
			});
     		
     		return;
		}

    	CleanBizFactory.create(room)
    	.success(function(ok)
		{
    		$scope.showDetail();
    		
    		$alert
			({
				title: '접수!! ',
				content: '청소주문이 접수되었습니다. \n 주문확인은 CLEAN메뉴에서 확인해세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 10
			});
    		    		
		});    	    	
    	
    	
    }
    
 	// callback for ng-click 'updateUser':
 	$scope.updateHouse = function ( ) 
 	{
 		HouseBizFactory.update( $routeParams.comId, $scope.house )
 		.success(function(data) 
 		{
 			//$location.path('/company-detail/'+$routeParams.comId);
 			
 			$alert
 			({
 				title: '업데이트!! ',
 				content: ' House업데이트에 성공하셨습니다..^^',
 				animation: 'fadeZoomFadeDown',
 				type: 'material',
 				duration: 3
 			});
 		});	        
 	};

 	$scope.cancel = function () 
    {
    	$location.path('/company-detail/'+$routeParams.comId);
    };
    
 	$scope.showDetail = function ()
 	{
         // 지역구 공통코드 추출
         CodezFactory.detailCodes( 'A0005' )
         .success(function (data)
         {
         	$scope.locals = data;
         });
 	   
   	    HouseBizFactory.show( $routeParams.comId, $routeParams.id )
 		.success(function (data)
 		{
 			$scope.house = data;
 			//$scope.cleans = data.cleans;
 			//$scope.datas = data.houses;	// paging 관련	
 		});
 	}
   
     	    
	$scope.showDetail();
	$scope.pageClass = 'fadeZoom';
	
}]);


web.controller('HouseCreateCtrl', ['$scope', 'HouseBizFactory', '$location', 'CodezFactory', '$routeParams', '$alert',
      function ($scope, HouseBizFactory, $location, CodezFactory, $routeParams, $alert) 
{

          // callback for ng-click 'createNewUser':
	$scope.createHouse = function ( ) 
 	{
		HouseBizFactory.create( $routeParams.comId, $scope.house )
        .success(function(ok) 
        {
   
        	$alert
 			({
 				title: '생성!! ',
 				content: ' House생성에 성공하셨습니다..^^',
 				animation: 'fadeZoomFadeDown',
 				type: 'material',
 				duration: 3
 			});
        	
        	$location.path('/company-detail/'+$routeParams.comId);
        });
          
	};
      
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$location.path('/company-detail/'+$routeParams.comId);
    };
	
    // 회사구분 공통코드 추출
    CodezFactory.detailCodes( 'A0005' )
    .success(function (data)
    {
    	$scope.locals = data;
    });

    $scope.pageClass = 'fadeZoom';
    
  }]);
