'use strict';

//angular.module('MyApp')
web.controller('MenuCtrl', function($scope, Auth, $location, $rootScope, $window, $alert, $http) 
{
	$scope.location = $location;
	var tokens = $window.localStorage.token;
	
	$scope.checkToken = function()
	{
		if (tokens) 
		{
	    	if( !$rootScope.currentUser )
			{
	    		var user = {
	    			id : tokens.split('||')[1], 
	    			token : tokens.split('||')[0]
				};

	    		Auth.sessionCheck(user);
			} 
	    }		
	};

    $scope.logout = function() 
	{    	 
    	//$scope.checkToken();
    	
		Auth.logout();
    };
    
    $scope.alertLogin = function()
    {
    	$alert
		({
			title: '경고!! ',
			content: '  로그인이 필요합니다..~~ ^^.',
			animation: 'fadeZoomFadeDown',
			type: 'material',
			duration: 1
		});
    };
    
    $scope.alertAuth = function()
    {
    	$alert
		({
			title: '경고!! ',
			content: '  권한이 없습니다..~~ ^^.',
			animation: 'fadeZoomFadeDown',
			type: 'material',
			duration: 1
		});
    };
    
    $scope.user = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' )
    		{
        		$location.path('/user-make');
    		}
        	else if( $rootScope.currentUser.workField != '11' )
    		{
        		$location.path('/user-detail/'+$rootScope.currentUser.userId);
    		}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}    		
    };
    
    $scope.passChange = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		$location.path('/change-pw');
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}    		
    };
    
    $scope.code = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' )
    		{
        		$location.path('/codez-make');
    		}        
    		else
			{
    			$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}	    		
    };
    
    $scope.contract = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' )
    		{
        		$location.path('/contract-make');
    		}        
    		else
			{
    			$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}	    		
    };
    
    $scope.empty = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' || $rootScope.currentUser.workField == '02' )
    		{
        		$location.path('/empty-make');
    		}        
    		else
			{
    			$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}	    		
    };
    
    $scope.company = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' )
    		{
        		$location.path('/company-make');
    		}
        	else if( $rootScope.currentUser.workField == '01' )
    		{
        		$location.path('/company-detail/'+$rootScope.currentUser.comId);
    		}
        	else
			{
    			$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}    		
    };
    
    $scope.clean = function()
    {
    	//$scope.checkToken();
    	
    	if( $rootScope.currentUser )
		{
    		if( $rootScope.currentUser.workField == '11' )
    		{
        		$location.path('/clean-make');
    		}
        	else if( $rootScope.currentUser.workField == '01' )
    		{
        		$location.path('/clean-clist/'+$rootScope.currentUser.comId);        		
    		}
        	else if( $rootScope.currentUser.workField == '03' )
    		{
         		$location.path('/clean-ulist/'+$rootScope.currentUser.userId);        		
    		}
        	else
			{
    			$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}    		
    };
    
    $scope.calendar = function()
    {
    	if( $rootScope.currentUser )
		{
	    	if( $rootScope.currentUser.workField == '11' )
			{
	    		$location.path('/calendar-make');
			}
	    	else if( $rootScope.currentUser.workField == '01' )
			{
	    		$location.path('/calendar-clist/'+$rootScope.currentUser.comId);
			}
	    	else if( $rootScope.currentUser.workField == '03' )
			{
	    		$location.path('/calendar-ulist/'+$rootScope.currentUser.userId);
			}
	    	else
			{
				$scope.alertAuth();
			}
		}    	
    	else
    	{
    		$scope.alertLogin();
    		
    		$location.path('/login');
    	}  	
    }
    
    $scope.isActive = function(route) 
    {    	
         return route === $location.path();
    };
    
	$scope.pageClass = 'fadeZoom';
});

