'use strict';

web.controller('AuthCtrl', function($scope, Auth, $location) 
{
	
	$scope.location = $location;
       
    $scope.login = function() 
	{
    	Auth.login
		({ 
			userId: $scope.userId, 
			passwd: $scope.passwd 
		});
    };

    $scope.forgot = function() 
	{
		Auth.forgot
		({
			userId: $scope.userId,
			email: $scope.email
		});    	
		
    };

    $scope.rePassword = function() 
	{
		Auth.changePw
		({
			passwd: $scope.passwd,
			changePw: $scope.changePw
		});    	

    };

    $scope.pageClass = 'fadeZoom';
});