'use strict';

web.controller('RoomCtrl', ['$scope', 'RoomFactory', 'CodezFactory', 'CleanFactory', '$location',  '$filter', '$routeParams',  '$alert',
   	function ($scope, RoomFactory, CodezFactory, CleanFactory, $location, $filter, $routeParams,  $alert) 
{
	$scope.location = $location;
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
    	RoomFactory.toggle( room )
        .success(function(data) 
    	{
         	$scope.all();
    	});
    };
    
	$scope.createRoom = function () 
    {
		/* http */
		RoomFactory.create( $routeParams.husId, $scope.room )
		.success(function(ok) 
		{
			$scope.room = {};
			$scope.all();
		});

    };
   
    $scope.editRoom = function ( id ) 
    {
    	RoomFactory.show( $routeParams.husId, id )
    	.success(function(data) 
    	{
    		$scope.room = data;
    		//$scope.all();
    	});
    };
    
    $scope.updateRoom = function () 
    {
    	RoomFactory.update( $routeParams.husId, $scope.room )
        .success(function(data) 
    	{
        	$scope.room = {};
        	$scope.all();
    	});
    };
    
    $scope.deleteRoom = function (roomId) 
    {
    	RoomFactory.remove( $routeParams.husId, roomId )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.room = {};
    	$scope.all();
    };
    
 	// get all
    $scope.all = function ( )
	{
    	RoomFactory.all( $routeParams.husId )
		.success(function (data)
		{			
			$scope.rooms = data;	
			$scope.datas = data;	// paging 관련	
			$scope.room = { husId: $routeParams.husId };
		});
		
	    // 주거형태 공통코드 추출
	    CodezFactory.detailCodes( 'A0002' )
	    .success(function (data)
	    {
	    	$scope.codes = data;
	    });

	}
    
    $scope.createClean = function( room )
    {
    	//room.hopeDate = $filter("date")(room.hopeDate, 'yyyy-MM-dd');
    	//console.log('hopeDate : '+room.hopeDate);
    	//return;
    	CleanFactory.create( room )
    	.success(function(ok)
		{
    		$alert
			({
				title: '접수!! ',
				content: '청소주문이 접수되었습니다. \n 주문확인은 CLEAN메뉴에서 확인해세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 10
			});
    		
    		$scope.all();
		})
    	.error(function()
    	{
    		$alert
			({
				title: '에러!! ',
				content: ' 새로고침후 재시도해 주세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
    	});
    	
    	
    }
    
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	

    
}]);


web.controller('RoomListCtrl', ['$scope',  'RoomBizFactory', '$location',  '$filter', 'CleanBizFactory', '$routeParams', 'CodezFactory', '$window', '$alert',
    function ($scope, RoomBizFactory, $location, $filter, CleanBizFactory, $routeParams, CodezFactory, $window, $alert) 
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
         	$scope.all();
    	});
    };
    
    $scope.createRoom = function () 
    {
		/* http */
    	RoomBizFactory.create( $routeParams.husId, $scope.room )
		.success(function(ok) 
		{
			$scope.room = {};
			$scope.all();
		});

    };
    
    $scope.editRoom = function ( id ) 
    {
    	RoomBizFactory.show( $routeParams.husId, id )
    	.success(function(data) 
    	{
    		$scope.room = data;
    		//$scope.all();
    	});
    };
    
    $scope.updateRoom = function () 
    {
    	RoomBizFactory.update( $routeParams.husId, $scope.room )
        .success(function(data) 
    	{
        	$scope.room = {};
        	$scope.all();
        	
    	});
    };
    
    $scope.deleteRoom = function ( room ) 
    {
    	RoomBizFactory.remove( $routeParams.husId, room )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.room = {};
    	$scope.all();
    	//$window.history.back();
    };
    
    // callback for ng-click 'cancel':
    $scope.back = function () 
    {
    	$window.history.back();
    };

 // get all
    $scope.all = function ( )
	{
    	RoomBizFactory.all( $routeParams.husId )
		.success(function (data)
		{			
			$scope.rooms = data;	
			$scope.datas = data;	// paging 관련	
			$scope.room = { husId: $routeParams.husId };
		});
		
	    // 주거형태 공통코드 추출
	    CodezFactory.detailCodes( 'A0002' )
	    .success(function (data)
	    {
	    	$scope.codes = data;
	    });

	}

    
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
 
    	CleanBizFactory.create( room )
    	.success(function(ok)
		{
    		$alert
			({
				title: '접수!! ',
				content: '청소주문이 접수되었습니다. \n 주문확인은 CLEAN메뉴에서 확인해세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 10
			});
    		
    		$scope.all();
		})
    	.error(function()
    	{
    		$alert
			({
				title: '에러!! ',
				content: ' 새로고침후 재시도해 주세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
    	});
    	
    	
    }
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
}]);


web.controller('RoomCreateCtrl', ['$scope', 'RoomBizFactory', '$location', 'CodezFactory', '$routeParams', '$alert',
	function ($scope, RoomBizFactory, $location, CodezFactory, $routeParams, $alert) 
{

           // callback for ng-click 'createNewUser':
	$scope.createRoom = function ( husId ) 
	{
		RoomFactory.create(husId, $scope.room)
		.success(function(ok) 
		{
	     	$alert
			({
				title: '생성!! ',
				content: ' Room생성에 성공하셨습니다..^^',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
	     	
	     	$location.path('/house-detail/'+husId);
	     });
	       
	};
   
	 // callback for ng-click 'cancel':
	 $scope.cancel = function (husId) 
	 {
	 	$location.path('/house-detail/'+husId);
	 };
	
	//주거형태 공통코드 추출
	 CodezFactory.detailCodes( 'A0002' )
	 .success(function (data)
	 {
	 	$scope.codes = data;
	 });
	 
	    
	 $scope.room = { husId: $routeParams.husId };
	 $scope.pageClass = 'fadeZoom';
     
}]);



web.controller('RoomDetailCtrl', ['$scope', '$routeParams', '$location', 'RoomBizFactory', 'CodezFactory', '$alert', '$filter', 'CleanBizFactory', '$window',
	function ($scope, $routeParams, $location, RoomBizFactory, CodezFactory, $alert, $filter, CleanBizFactory, $window) 
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
    
        
 	// callback for ng-click 'updateUser':
 	$scope.updateRoom = function ( husId ) 
 	{
 		RoomBizFactory.update( husId, $scope.room )
 		.success(function(data) 
 		{
 			//$location.path('/house-detail/'+husId);
 			$window.history.back();
 			$alert
 			({
 				title: '업데이트!! ',
 				content: ' Room업데이트에 성공하셨습니다..^^',
 				animation: 'fadeZoomFadeDown',
 				type: 'material',
 				duration: 3
 			});
 		});	        
 	};

 	$scope.cancel = function (husId) 
    {
    	//$location.path('/house-detail/'+husId);
    	$window.history.back();
    };
    
 	$scope.showDetail = function ()
 	{
 		//주거형태 공통코드 추출
 		 CodezFactory.detailCodes( 'A0002' )
 		 .success(function (data)
 		 {
 		 	$scope.codes = data;
 		 });	   
   	
 		RoomBizFactory.show( $routeParams.husId, $routeParams.id )
 		.success(function (data)
 		{
 			$scope.room = data;
 			//$scope.cleans = data.cleans;
 			//$scope.datas = data.houses;	// paging 관련	
 		});
 	}
   
     	    
     	$scope.showDetail();
     	$scope.pageClass = 'fadeZoom';
 }]);
