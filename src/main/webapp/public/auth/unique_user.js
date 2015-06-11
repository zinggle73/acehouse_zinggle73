'use strict';

web.directive('uniqueUser', function($http) 
{
	return {
		restrict: 'A',
		require: 'ngModel',
		link: function(scope, element, attrs, ngModel) 
		{
			element.bind('blur', function() 
			{
				if ( ngModel.$modelValue ) 
				{

					$http.get( '/auth/unique/'+ ngModel.$modelValue )
					.success(function(data)
					{
						//console.log("userId unique : %s ", !data.userId );
						ngModel.$setValidity( 'unique', !data.userId ); 
					});
				}
			});
			element.bind('keyup', function() 
			{
				ngModel.$setValidity( 'unique', true );
			});
		}
    };
});