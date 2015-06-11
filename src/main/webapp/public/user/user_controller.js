'use strict';


web.controller('UserCtrl', ['$scope', 'AdminFactory', 'CompanyFactory', 'CodezFactory', '$location', '$filter', 'Auth',
	function ($scope, AdminFactory, CompanyFactory, CodezFactory, $location, $filter, Auth) 
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
    
	$scope.createUser = function () 
    {
		/* http */
		AdminFactory.create($scope.user)
        .success(function(ok) 
        {
        	$scope.user = {};
        	$scope.all();
        });

    };

    $scope.editUser = function (id) 
    {
    	AdminFactory.show(id)
    	.success(function(data) 
    	{
    		$scope.user = data;
    	});
    };
    
    $scope.updateUser = function (userId) 
    {
    	AdminFactory.update($scope.user)
        .success(function(data) 
    	{
        	$scope.user = {};
        	$scope.all();
     	});
        
        
    };
    
 // callback for ng-click 'deleteUser':
    $scope.deleteUser = function (userId) 
    {
    	AdminFactory.remove( userId )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.user = {};
    	$scope.all();
    };
    
    // get all
    $scope.all = function ()
	{
    	CompanyFactory.all()
		.success(function (data)
		{
			$scope.coms = data;
		});
		
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0001' )
	    .success(function (data)
	    {
	    	$scope.works = data;
	    });
	    
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0004' )
	    .success(function (data)
	    {
	    	$scope.auths = data;
	    });
	    
	    AdminFactory.all()
		.success(function (data)
		{
			$scope.users = data;	
			$scope.datas = data;	// paging 관련	
		});
		
		
	}
    
    $scope.all();
	$scope.pageClass = 'fadeZoom';
	

    
}]);
  	

web.controller('UserListCtrl', ['$scope',  'UserFactory', '$location',  '$filter',
    function ($scope, UserFactory, $location, $filter) 
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
	    $scope.editUser = function (userId) 
	    {
	        $location.path('/user-detail/' + userId);
	    };
	
	    // callback for ng-click 'deleteUser':
	    $scope.deleteUser = function (userId) 
	    {
	    	UserFactory.remove( userId )
	        .success(function(data)
	        {
	        	$scope.all();
	        });
	    };
	
	    // callback for ng-click 'createUser':
	    $scope.createNewUser = function () 
	    {
	        $location.path('/user-create');
	    };
	
	    //$scope.users = UserFactory.all();
	    // get all
	    $scope.all = function ()
		{
			UserFactory.all()
			.success(function (data)
			{
				$scope.users = data;	
				$scope.datas = data;	// paging 관련	
			});
		}
	    
	    $scope.all();
		$scope.pageClass = 'fadeZoom';
}]);

//angular.module('MyApp')
web.controller('UserDetailCtrl', ['$scope', '$routeParams', 'UserFactory', '$location', 'CompanyFactory', 'CodezFactory', '$alert', 'Auth', '$filter',
    function ($scope, $routeParams, UserFactory, $location, CompanyFactory, CodezFactory, $alert, Auth, $filter) 
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
    
	// callback for ng-click 'updateUser':
    $scope.updateUser = function () 
    {
    	UserFactory.update( $scope.user )
        .success(function(data) 
    	{
        	if( data.message == 'OK')
    		{
        		$scope.showDetail();
            	
            	$alert
    			({
    				title: '업데이트!! ',
    				content: ' 업데이트에 성공하셨습니다. 다시 로그인 하세요..^^',
    				animation: 'fadeZoomFadeDown',
    				type: 'material',
    				duration: 3
    			});
            	
            	Auth.logout();
    		}
        	else if( data.message == 'noPass' )
    		{
        		$alert
    			({
    				title: '실패!! ',
    				content: ' 확인 비밀번호가 필요합니다. 다시 시도해주세요.^^',
    				animation: 'fadeZoomFadeDown',
    				type: 'material',
    				duration: 3
    			});
    		}
        	else
    		{
        		$alert
    			({
    				title: '실패!! ',
    				content: ' 업데이트에 실패하셨습니다. 비밀번호를 확인하시기 바랍니다..^^',
    				animation: 'fadeZoomFadeDown',
    				type: 'material',
    				duration: 3
    			});
    		}
        	
        	//$location.path('/user-list');
    	});	        
    };

    $scope.showDetail = function ()
	{
    	/*
    	CompanyFactory.all()
		.success(function (data)
		{
			$scope.coms = data;
		});
		
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0001' )
	    .success(function (data)
	    {
	    	$scope.works = data;
	    });
	    
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0004' )
	    .success(function (data)
	    {
	    	$scope.auths = data;
	    });
	    */
    	
	    UserFactory.show( $routeParams.userId )
		.success(function (data)
		{
			$scope.user = data;
			$scope.historys = data.historys;
			$scope.datas = data.historys;	// paging 관련	
		});
	}
    
  	    
    $scope.showDetail();
	$scope.pageClass = 'fadeZoom';
}]);

//angular.module('MyApp')
web.controller('UserCreateCtrl', ['$scope', 'UserFactory', '$location',
    function ($scope, UserFactory, $location) 
{

        // callback for ng-click 'createNewUser':
        $scope.createNewUser = function () 
        {
        	UserFactory.create($scope.user)
	        .success(function(ok) 
	        {
	        	$location.path('/user-list');
	        });
            
        };
        
	    // callback for ng-click 'cancel':
	    $scope.cancel = function () 
	    {
	        $location.path('/user-list');
	    };
	    
	    CompanyFactory.all()
		.success(function (data)
		{
			$scope.coms = data;
		});
		
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0001' )
	    .success(function (data)
	    {
	    	$scope.works = data;
	    });
	    
	    // 회사구분 공통코드 추출
	    CodezFactory.detailCodes( 'A0004' )
	    .success(function (data)
	    {
	    	$scope.auths = data;
	    });
	    
	    
}]);


web.controller('HistoryCtrl', ['$scope', 'AdminFactory', '$location', '$routeParams', '$filter',
	function ($scope, AdminFactory, $location, $routeParams, $filter) 
{
	$scope.location = $location;
	$scope.currentPage = 0;
    $scope.pageSize = 50;
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
    
	// callback for ng-click 'cancel':
	$scope.cancel = function () 
	{
	    $location.path('/user-make');
	};
	
	$scope.showDetail = function ()
	{
	
		AdminFactory.show( $routeParams.id )
		.success(function (data)
		{
			$scope.user = data;
			$scope.datas = data.historys;	// paging 관련	
		});
	}
  	    
	$scope.showDetail();
	$scope.pageClass = 'fadeZoom';

}]);