'use strict';

web.controller('CalendarCtrl', ['$scope', 'CalendarFactory', 
   	function ($scope, CalendarFactory) 
{

    $scope.all = function ( )
	{
    	CalendarFactory.all( )
		.success(function (data)
		{			
			$(document).ready(function() 
			{				
				$('#calendar').fullCalendar({
					header:	{
						left: 'prev,next,today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					defaultDate: new Date(),
					editable: true,
					eventLimit: true,
					events:  data
				});
			});		        
		});
	}

    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);


web.controller('CalendarUListCtrl', ['$scope', 'CalendarBizFactory', '$routeParams',
   	function ($scope, CalendarBizFactory, $routeParams) 
{
    $scope.all = function ( )
	{
    	CalendarBizFactory.all( $routeParams.userId )
		.success(function (data)
		{			
			$(document).ready(function() 
			{				
				$('#calendar').fullCalendar({
					header:	{
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					defaultDate: new Date(),
					editable: true,
					eventLimit: true,
					events:  data
				});
			});		        
		});
	}

    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);


web.controller('CalendarCListCtrl', ['$scope', 'CalendarBizFactory', '$routeParams',
   	function ($scope, CalendarBizFactory, $routeParams) 
{
    $scope.all = function ( )
	{
    	CalendarBizFactory.all( $routeParams.comId )
		.success(function (data)
		{			
			$(document).ready(function() 
			{				
				$('#calendar').fullCalendar({
					header:	{
						left: 'prev,next today',
						center: 'title',
						right: 'month,agendaWeek,agendaDay'
					},
					defaultDate: new Date(),
					editable: true,
					eventLimit: true,
					events:  data
				});
			});		        
		});
	}

    $scope.all();
	$scope.pageClass = 'fadeZoom';
	
}]);