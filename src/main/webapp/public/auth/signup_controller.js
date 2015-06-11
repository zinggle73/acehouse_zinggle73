'use strict';

web.controller('SignupCtrl', function($scope, Auth, $location, CompanyFactory) 
{
	$scope.location = $location;
    $scope.signup = function() 
	{
		Auth.signup
		({
			comId: $scope.comId,
			userId: $scope.userId,
			passwd: $scope.passwd
		});    	
		
    };
    
    CompanyFactory.all()
	.success(function (data)
	{
		$scope.coms = data;
	});
    

    $scope.pageClass = 'fadeZoom'
});