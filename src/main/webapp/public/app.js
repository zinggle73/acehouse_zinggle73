'use strict';

//var web = angular.module('MyApp', ['ngResource', 'ngMessages', 'ngRoute', 'ngAnimate', 'mgcrea.ngStrap']);
var web = angular.module('MyApp', ['ngResource', 'ngRoute', 'ngAnimate', 'ngMessages', 'mgcrea.ngStrap']);
web.config(function ($routeProvider, $locationProvider) 
{
    //$locationProvider.html5Mode(true).hashPrefix('!');

    $routeProvider.when('/', 						{ templateUrl: '/public/main/views/home.html', 				controller: 'MainCtrl'			});
	$routeProvider.when('/login', 					{ templateUrl: '/public/auth/views/login.html', 			controller: 'AuthCtrl' 			});
	$routeProvider.when('/signup', 					{ templateUrl: '/public/auth/views/signup.html', 			controller: 'SignupCtrl' 		});
	$routeProvider.when('/forgot', 					{ templateUrl: '/public/auth/views/forgot.html', 			controller: 'AuthCtrl' 			});
	$routeProvider.when('/change-pw', 				{ templateUrl: '/public/auth/views/changepw.html',			controller: 'AuthCtrl' 			});
	$routeProvider.when('/codez-make', 				{ templateUrl: '/public/codez/views/codez-make.html', 		controller: 'CodezCtrl' 		});
	$routeProvider.when('/contract-make',			{ templateUrl: '/public/contract/views/contract-make.html', controller: 'ContractCtrl' 		});
	$routeProvider.when('/user-make', 				{ templateUrl: '/public/user/views/user-make.html', 		controller: 'UserCtrl' 			});
	$routeProvider.when('/history-make/:id',		{ templateUrl: '/public/user/views/history-make.html', 		controller: 'HistoryCtrl'		});
	$routeProvider.when('/company-make', 			{ templateUrl: '/public/company/views/company-repeat.html',	controller: 'CompanyCtrl' 		});
	$routeProvider.when('/house-make/:comId',		{ templateUrl: '/public/house/views/house-make.html', 		controller: 'HouseCtrl' 		});
	$routeProvider.when('/room-make/:husId',		{ templateUrl: '/public/room/views/room-make.html', 		controller: 'RoomCtrl' 			});
	$routeProvider.when('/clean-make',				{ templateUrl: '/public/clean/views/clean-make.html', 		controller: 'CleanCtrl' 		});
	$routeProvider.when('/empty-make',				{ templateUrl: '/public/company/views/empty-make.html', 	controller: 'ComEmptyCtrl' 		});
	$routeProvider.when('/calendar-make',			{ templateUrl: '/public/calendar/views/calendar-make.html', controller: 'CalendarCtrl' 		});

	$routeProvider.when('/user-detail/:userId', 	{ templateUrl: '/public/user/views/user-detail.html', 		controller: 'UserDetailCtrl' 	});
	$routeProvider.when('/company-detail/:comId', 	{ templateUrl: '/public/company/views/company-detail.html', controller: 'ComDetailCtrl' 	});
	$routeProvider.when('/house-detail/:comId/:id',	{ templateUrl: '/public/house/views/house-detail.html',		controller: 'HouseDetailCtrl' 	});
	$routeProvider.when('/house-create/:comId',		{ templateUrl: '/public/house/views/house-create.html', 	controller: 'HouseCreateCtrl' 	});
	$routeProvider.when('/room-list/:husId',		{ templateUrl: '/public/room/views/room-list.html', 		controller: 'RoomListCtrl' 		});
	$routeProvider.when('/room-create/:husId',		{ templateUrl: '/public/room/views/room-create.html', 		controller: 'RoomCreateCtrl' 	});
	$routeProvider.when('/room-detail/:husId/:id',	{ templateUrl: '/public/room/views/room-detail.html', 		controller: 'RoomDetailCtrl' 	});
	$routeProvider.when('/clean-clist/:comId',		{ templateUrl: '/public/clean/views/clean-list.html', 		controller: 'CleanCListCtrl' 	});
	$routeProvider.when('/clean-ulist/:userId',		{ templateUrl: '/public/clean/views/clean-list.html', 		controller: 'CleanUListCtrl' 	});
	$routeProvider.when('/calendar-clist/:comId',	{ templateUrl: '/public/calendar/views/calendar-list.html', controller: 'CalendarCListCtrl' });
	$routeProvider.when('/calendar-ulist/:userId',	{ templateUrl: '/public/calendar/views/calendar-list.html', controller: 'CalendarUListCtrl' });
	

	$routeProvider.otherwise({ redirectTo: '/login' });

})
.config(function ($httpProvider) 
{   
	$httpProvider.interceptors.push(function ($rootScope, $q, $window, $location) 
	{
		return {
			request: function(config) 
			{
				if ( $window.localStorage.token ) 
				{
					config.headers.Authorization = 'Bearer ' + $window.localStorage.token;					
				}
				return config;
			},
			/*response: function(response)
			{
	           return response || $q.when(response);
	        },*/
			responseError: function(response) 
			{				
				if (response.status === 401 || response.status === 403)
				{
					$location.path('/login');
				}
				return $q.reject(response);
			}
		}
    });

});