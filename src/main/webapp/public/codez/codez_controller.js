'use strict';

web.controller('CodezCtrl', ['$scope', 'CodezFactory', '$location',  '$filter',
   	function ($scope, CodezFactory, $location, $filter) 
{
	$scope.currentPage = 0;
    $scope.pageSize = 20;
    $scope.datas = [];
    $scope.qq = '';

    $scope.toggleAddMode = function ()
    {
    	$scope.codez = {};
        $scope.addMode = !$scope.addMode;
    };

    $scope.toggleEditMode = function ( codez )
    {
    	codez.editMode = !codez.editMode;
    };
    
    $scope.getData = function () 
    {
      return $filter('filter')($scope.datas, $scope.qq)
    }
    
    $scope.numberOfPages = function()
    {
        return Math.ceil($scope.getData().length/$scope.pageSize);                
    }
    
    $scope.orToggle = function( codez )
    {
    	CodezFactory.toggle( codez )
        .success(function(data) 
    	{
         	$scope.all();
    	});
    };
    
	$scope.createCodez = function () 
    {
		/* http */
		CodezFactory.create( $scope.codez )
		.success(function(ok) 
		{
			$scope.codez = {};
			$scope.all();
		});

    };
   
    $scope.editCodez = function ( codeId ) 
    {
    	CodezFactory.show(codeId)
    	.success(function(data) 
    	{
    		$scope.codez = data;
    	});
    };
    
    $scope.updateCodez = function ( codez ) 
    {
    	CodezFactory.update( codez )
        .success(function(data) 
    	{
        	$scope.codez = {};
        	$scope.all();
    	});
    };
    
    $scope.deleteCodez = function (codeId) 
    {
    	CodezFactory.remove( codeId )
        .success(function(data)
        {
        	$scope.all();
        });
    };
    
    // callback for ng-click 'cancel':
    $scope.cancel = function () 
    {
    	$scope.codez = {};
    	//$scope.all();
    };
    
 	    // get all
    $scope.all = function ()
	{
    	//CodezFactory.all()
    	CodezFactory.groups()
		.success(function (data)
		{
			$scope.codezs = data;	
			$scope.datas = data;	// paging 관련	
		});
    	
        CodezFactory.groups()
        .success(function (data)
        {
        	$scope.codes = data;
        });
        
        $scope.yns = 
        [
          	{key:'사용', 		value:'Y'},
          	{key:'미사용',		value:'N'}
        ];
            
	}
    


    $scope.all();
	$scope.pageClass = 'fadeZoom';
	

    
}]);


