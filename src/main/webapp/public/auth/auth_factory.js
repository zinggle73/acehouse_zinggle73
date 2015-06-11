'use strict';


web.factory('Auth', function($http, $location, $rootScope, $alert, $window)
{
    var tokens = $window.localStorage.token;
    var service = this;

    service.sessionCheck = function(user)
    {
		return $http.post('/auth/session', user)
		.success(function(data) 
		{
			//$window.sessionStorage.user = JSON.stringify(data.user);
			$rootScope.currentUser = data.user;
		})
    };
    
    if (tokens) 
	{
		var user = {
			//userId : tokens.split('||')[1], 
			id : tokens.split('||')[1], 
			token : tokens.split('||')[0]
		};

		service.sessionCheck(user);	
    };
/*
    function sessionCheck (user)
	{
		return $http.post('/auth/session', user)
		.success(function(data) 
		{
			//$window.sessionStorage.user = JSON.stringify(data.user);
			$rootScope.currentUser = data.user;
		})
	}
*/
  
    
    
    service.forgot = function(user)
    {
		return $http.post('/auth/forgot', user)
		.success(function(data) 
		{
			if( data != 'null' )
			{
				$alert
				({
					title: '!! ',
					content: data.userName+'님 비밀번호찾기 메일요청이 발급되었습니다. 확인바람니다.^^',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 5
				});
				
				$location.path('/login');
			}
			else
			{				
				$alert
				({
					title: ' ',
					content: '입력데이터가 맞지않습니다. 확인하시고 다시 입력해주시기 바랍니다.',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 5
				});
			}				
		});		
    };
    
    service.changePw = function(user)
    {
		return $http.post('/auth/changePw', user)
		.success(function(data) 
		{
			if( data.message == 'OK' )
			{
				$alert
				({
					title: '!! ',
					content: '비밀번호가 교체되었습니다.^^',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 3
				});
				
				service.logout();
			}
			else
			{				
				$alert
				({
					title: ' ',
					content: '현재 비밀번호가 맞지않습니다.',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 3
				});
			}				
		});		
    };
    
	service.login = function(user) 
	{
		return $http.post('/auth/login', user)
		.success(function(data) 
		{
			if(data.user != null)
			{
				$window.localStorage.token = data.user.token+"||"+data.user.id;
				//$window.localStorage.token = data.user.token+"||"+data.user.userId;
				//$window.sessionStorage.user = JSON.stringify(data.user);
				$rootScope.currentUser = data.user;
				
				if( data.user.workField == '11' && data.user.userAuth == '1' )
				{
					$location.path('/calendar-make');
				}
				else if( data.user.workField == '01' && ( data.user.userAuth == '2' || data.user.userAuth == '3' ) )
				{
					$location.path('/calendar-clist/'+data.user.comId);
				}
				else if( data.user.workField == '03'  && ( data.user.userAuth == '2' || data.user.userAuth == '3' ) )
				{
					$location.path('/calendar-ulist/'+data.user.userId);
				}
				else if( data.user.userAuth == '5' )
				{
					$location.path('/user-detail/'+data.user.userId);
				}
				else
				{
					$location.path('/');
				}
					
				$alert
				({
					title: '로그인!! ',
					content: data.user.userName+'님 로그인에 성공하셨습니다.^^',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 5
				});
			}
			else
			{
				delete $window.localStorage.token;
				$alert
				({
					title: '에러!! ',
					content: ' 아이디또는 비밀번호가 맞지않습니다.',
					animation: 'fadeZoomFadeDown',
					type: 'material',
					duration: 3
				});
			}
			
		});
	};
	
	service.signup = function(user) 
	{
		return $http.post('/auth/signup', user)
		.success(function(res) 
		{       
			$location.path('/login');
			$alert	
			({
				title: '축하합니다!! ',
				content: ' 계정생성에 성공했습니다.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
		})
		.error(function(res) 
		{
			$alert
			({
				title: '에러!! ',
				content: ' 계정생성에 실패했습니다.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
		});
	};
	
	service.logout = function() 
	{
		return $http.get('/auth/logout')
		.success(function(data) 
		{
			delete $window.localStorage.token;
			//delete $window.sessionStorage.token;
			$rootScope.currentUser = null;
			
			$location.path('/login');
			$alert
			({
				content: '로그아웃 되었습니다.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 4
			});
		})
	};
	
	return service;

});