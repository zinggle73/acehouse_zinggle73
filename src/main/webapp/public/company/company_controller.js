'use strict';

web.controller('CompanyCtrl', ['$scope', 'CompanyFactory', 'HouseFactory', 'RoomFactory', 'CleanFactory', 'CodezFactory', '$location',  '$filter', '$alert',
	function ($scope, CompanyFactory, HouseFactory, RoomFactory, CleanFactory, CodezFactory, $location, $filter, $alert) 
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
   
    $scope.createCompany = function ( company ) 
    {
    	$scope.companys = [];
    	
    	CompanyFactory.create( company )
    	.success(function( data ) 
    	{
    		$scope.company = {};

    		for(var i=0; i<data.length; i++)
  			{
  				$scope.companys.push( data[i] );
  			} 
     	});
    };
   
   	$scope.createHouse = function ( comId, house, comIndex ) 
   	{
   		$scope.companys[comIndex].houses = [];
   		
	   	HouseFactory.create( comId, house )
   		.success(function( data ) 
   		{   			
   			$scope.company = {};
   			$scope.house = {};   			
   			
   			for(var i=0; i<data.length; i++)
  			{
   				$scope.companys[comIndex].houses.push( data[i] );
  			} 
   		});
   	};
  
	$scope.createRoom = function ( husId, room, comIndex, husIndex ) 
	{		
		$scope.companys[comIndex].houses[husIndex].rooms = [];
		
		RoomFactory.create( husId, room )
  		.success(function( data ) 
  		{
  			$scope.company = {};
  			$scope.house = {};
  			$scope.room = {};
  			
  			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  			
  		});
	};
  
	$scope.editCompany = function ( comId ) 
   	{
   		CompanyFactory.show( comId )
   		.success(function(data) 
   		{
   			$scope.company = data;
   		});
   	};
   
   	$scope.editHouse = function ( comId, house ) 
   	{
	   HouseFactory.show( comId, house )
   		.success(function(data) 
   		{
   			$scope.house = data;
   		});
   	};
   
   	$scope.editRoom = function ( husId, roomId ) 
   	{
	   	RoomFactory.show( husId, roomId )
   		.success(function(data) 
   		{
   			$scope.room = data;
   		});
   	};
   
   	$scope.updateCompany = function ( company ) 
   	{
   		$scope.companys = [];
   		
   		CompanyFactory.update( company )
   		.success(function(data) 
    	{
   			$scope.company = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys.push( data[i] );
  			} 
   			//$scope.all();
    	});
   	};
   
   	$scope.updateHouse = function ( comId, house, comIndex ) 
   	{
   		$scope.companys[comIndex].houses = [];
   		
	   	HouseFactory.update( comId, house )
   		.success(function( data ) 
    	{
   			$scope.company = {};
   			$scope.house = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
   				$scope.companys[comIndex].houses.push( data[i] );
  			} 
   			//$scope.all();
    	});
   	};
   
   	$scope.updateRoom = function ( husId, room, comIndex, husIndex ) 
   	{
   		$scope.companys[comIndex].houses[husIndex].rooms = [];
   		
	   	RoomFactory.update( husId, room )
   		.success(function(data) 
    	{
   			$scope.company = {};
   			$scope.house = {};
   			$scope.room = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
   			//$scope.all();
    	});
   	};
   
   	$scope.deleteCompany = function ( comId ) 
   	{
   		$scope.companys = [];
   		
   		CompanyFactory.remove( comId )
       .success(function( data )
       {
    	   $scope.company = {};
    	   
    	   for(var i=0; i<data.length; i++)
 			{
 				$scope.companys.push( data[i] );
 			} 
       		//$scope.all();
       });
   	};
   
   	$scope.deleteRoom = function ( husId, roomId, comIndex, husIndex )  
   	{
   		$scope.companys[comIndex].houses[husIndex].rooms = [];
   		
   		RoomFactory.remove( husId, roomId )
   		.success(function( data )
   		{
   			$scope.company = {};
   			$scope.house = {};
   			$scope.room = {};
   			
    	   	for(var i=0; i<data.length; i++)
 			{
 				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
 			} 
       		//$scope.all();
      	});
   	};
   
   
   	// callback for ng-click 'cancel':
   	$scope.cancelCompany = function () 
   	{
   		$scope.company = {};
   		//$scope.all();
   	};
   
   	// callback for ng-click 'cancel':
   	$scope.cancelHouse = function () 
   	{
   		$scope.company = {};
   		$scope.house = {};
   		//$scope.all();
   	};
   
   	// callback for ng-click 'cancel':
   	$scope.cancelRoom = function () 
   	{
   		$scope.company = {};
   		$scope.house = {};
   		$scope.room = {};
   		//$scope.all();
   	};
   
	$scope.zeroToggle = function( room, comIndex, husIndex )
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

		$scope.companys[comIndex].houses[husIndex].rooms = [];

   		RoomFactory.toggle( room )
   		.success(function( data ) 
    	{
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
    	});
	};
   
	$scope.ingToggle = function( house, comIndex )
	{

		$scope.companys[comIndex].houses = [];

		HouseFactory.toggle( house )
   		.success(function( data ) 
    	{
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses.push( data[i] );
  			}  
    	});
	};
	
	$scope.createClean = function( room, comIndex, husIndex )
	{
		CleanFactory.create( room )
		.success(function( data )
		{
			$alert
			({
				title: '접수!! ',
				content: '청소주문이 접수되었습니다. \n 주문확인은 청소관리 메뉴에서 확인해세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 10
			});
   		
			$scope.companys[comIndex].houses[husIndex].rooms = [];
			
			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
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
	
	// get all
	$scope.all = function ()
	{
   		CompanyFactory.all()
   		.success(function (data)
   		{
   			$scope.companys = data;	
   			$scope.datas = data;	// paging 관련	
   		});

   		// 회사구분 공통코드 추출
   		CodezFactory.detailCodes( 'A0001' )
   		.success(function (data)
   		{
   			$scope.coms = data;
   		});
   
   		// 회사구분 공통코드 추출
   		CodezFactory.detailCodes( 'A0002' )
   		.success(function (data)
   		{
   			$scope.livings = data;
   		});
   
   		// 지역구 공통코드 추출
   		CodezFactory.detailCodes( 'A0005' )
   		.success(function (data)
   		{
       		$scope.locals = data;
   		});
   }
  
    $scope.comToggleAddMode = function ( company )
    {
    	$scope.house = {};
    	company.addMode = !company.addMode;
    };

    $scope.houseToggleAddMode = function ( house )
    {
    	$scope.room = {};
    	house.addMode = !house.addMode;
    };


   $scope.all();
   $scope.pageClass = 'fadeZoom';
   	   
}]);



web.controller('ComDetailCtrl', ['$scope', '$routeParams', '$location', 'CompanyBizFactory', 'HouseBizFactory', 'RoomBizFactory', 'CleanBizFactory', 'CodezFactory', '$alert', '$filter', 
 	function ($scope, $routeParams, $location, CompanyBizFactory, HouseBizFactory, RoomBizFactory, CleanBizFactory, CodezFactory, $alert, $filter) 
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

    $scope.createHouse = function ( comId, house, comIndex ) 
   	{
   		$scope.companys[comIndex].houses = [];
   		
	   	HouseBizFactory.create( comId, house )
   		.success(function( data ) 
   		{   			
   			$scope.house = {};   			
   			
   			for(var i=0; i<data.length; i++)
  			{
   				$scope.companys[comIndex].houses.push( data[i] );
  			} 
   		});
   	};
  
	$scope.createRoom = function ( husId, room, comIndex, husIndex ) 
	{		
		$scope.companys[comIndex].houses[husIndex].rooms = [];
		
		RoomBizFactory.create( husId, room )
  		.success(function( data ) 
  		{
   			$scope.house = {};
  			$scope.room = {};
  			
  			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  			
  		});
	};
	
	$scope.editCompany = function ( id ) 
   	{
   		CompanyBizFactory.show( id )
   		.success(function( data ) 
   		{
   			$scope.company = data;
   		});
   	};
   	
	$scope.editHouse = function ( comId, house ) 
   	{
	   HouseBizFactory.show( comId, house.id )
   		.success(function( data ) 
   		{
   			$scope.house = data;
   		});
   	};
   
   	$scope.editRoom = function ( husId, roomId ) 
   	{
	   	RoomBizFactory.show( husId, roomId )
   		.success(function(data) 
   		{
   			$scope.room = data;
   		});
   	};
   	
   	
	// callback for ng-click 'updateUser':
	$scope.updateCompany = function ( company ) 
	{
		$scope.companys = [];
		
		CompanyBizFactory.update( company )
		.success(function(data) 
		{
			$scope.company = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys.push( data[i] );
  			} 
   			
			$alert
			({
				title: '업데이트!! ',
				content: ' 업데이트에 성공하셨습니다..^^',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 3
			});
		});	        
	};

	$scope.updateHouse = function ( comId, house, comIndex ) 
   	{
   		$scope.companys[comIndex].houses = [];
   		
	   	HouseBizFactory.update( comId, house )
   		.success(function( data ) 
    	{
   			$scope.house = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
   				$scope.companys[comIndex].houses.push( data[i] );
  			} 
   			//$scope.all();
    	});
   	};
   
   	$scope.updateRoom = function ( husId, room, comIndex, husIndex ) 
   	{
   		$scope.companys[comIndex].houses[husIndex].rooms = [];
   		
	   	RoomBizFactory.update( husId, room )
   		.success(function( data ) 
    	{
   			$scope.house = {};
   			$scope.room = {};
   			
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
   			//$scope.all();
    	});
   	};
	
	// callback for ng-click 'updateUser':
	$scope.delHouse = function ( house ) 
	{
		var x  = window.confirm("삭제하시겠습니까?");
		if ( x )
		{
        	HouseBizFactory.del( $routeParams.comId, house )
    		.success(function(data) 
    		{
    			$scope.showDetail();
    			
    			$alert
    			({
    				title: '삭제!! ',
    				content: house.husName +' 삭제에 성공하셨습니다..^^',
    				animation: 'fadeZoomFadeDown',
    				type: 'material',
    				duration: 3
    			});
    		});	           	
		}		     
	};
	
	$scope.deleteRoom = function ( husId, roomId, comIndex, husIndex )  
   	{
   		$scope.companys[comIndex].houses[husIndex].rooms = [];
   		
   		RoomBizFactory.remove( husId, roomId )
   		.success(function( data )
   		{
   			$scope.house = {};
   			$scope.room = {};
   			
    	   	for(var i=0; i<data.length; i++)
 			{
 				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
 			} 
       		//$scope.all();
      	});
   	};
   
 	$scope.cancelCompany = function () 
   	{
   		$scope.company = {};
   		//$scope.all();
   	};
   	// callback for ng-click 'cancel':
   	$scope.cancelHouse = function () 
   	{
   		$scope.house = {};
   		//$scope.all();
   	};
   
   	// callback for ng-click 'cancel':
   	$scope.cancelRoom = function () 
   	{
   		$scope.house = {};
   		$scope.room = {};
   		//$scope.all();
   	};

   	$scope.zeroToggle = function( room, comIndex, husIndex )
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

		$scope.companys[comIndex].houses[husIndex].rooms = [];

   		RoomBizFactory.toggle( room )
   		.success(function( data ) 
    	{
   			$scope.company = {};
  			$scope.house = {};
  			$scope.room = {};
  			
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
    	});
	};
   
	$scope.ingToggle = function( house, comIndex )
	{

		$scope.companys[comIndex].houses = [];
		
		HouseBizFactory.toggle( house )
   		.success(function( data ) 
    	{
   			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses.push( data[i] );
  			}  
    	});
	};
	
	$scope.createClean = function( room, comIndex, husIndex )
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
				duration: 3
			});
     		
     		return;
		}
    	
		CleanBizFactory.create( room )
		.success(function( data )
		{			
			$alert
			({
				title: '접수!! ',
				content: '청소주문이 접수되었습니다. \n 주문확인은 청소관리 메뉴에서 확인해세요.',
				animation: 'fadeZoomFadeDown',
				type: 'material',
				duration: 10
			});   
			
			$scope.companys[comIndex].houses[husIndex].rooms = [];
			
			for(var i=0; i<data.length; i++)
  			{
  				$scope.companys[comIndex].houses[husIndex].rooms.push( data[i] );
  			}  
			
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
	
	$scope.showDetail = function ()
	{
	  	// 회사구분 공통코드 추출
        CodezFactory.detailCodes( 'A0001' )
        .success(function (data)
        {
        	$scope.coms = data;
        });
        
        // 회사구분 공통코드 추출
        CodezFactory.detailCodes( 'A0002' )
        .success(function (data)
        {
        	$scope.livings = data;
        });
	   
        // 지역구 공통코드 추출
        CodezFactory.detailCodes( 'A0005' )
        .success(function (data)
        {
        	$scope.locals = data;
        });
        
        /*
        // 지역구 공통코드 추출
        CodezFactory.detailCodes( 'A0006' )
        .success(function (data)
        {
        	$scope.reads = data;
        });
        
        var d = new Date();
		var n = d.getFullYear();
		
        $scope.years = 
        [			
          	{value : (n-1)},
          	{value : n},
          	{value : (n+1)},
          	{value : (n+2)}
        ];
        
        $scope.months = 
        [
          	{value : '01'},
          	{value : '02'},
          	{value : '03'},
          	{value : '04'},
          	{value : '05'},
          	{value : '06'},
          	{value : '07'},
          	{value : '08'},
          	{value : '09'},
          	{value : '10'},
          	{value : '11'},
          	{value : '12'}
        ];
        
        	   
	    $scope.roomToggleAddMode = function ( room )
	    {
	    	$scope.reader = {};
	    	room.addMode = !room.addMode;
	    };
    
        */
        CompanyBizFactory.all( $routeParams.comId )
		.success(function ( data )
		{
			$scope.companys = data;
			//$scope.cleans = data.cleans;
			//$scope.datas = data.houses;	// paging 관련	
		});
	}
  
    $scope.comToggleAddMode = function ( company )
    {
    	$scope.house = {};
    	company.addMode = !company.addMode;
    };

    $scope.houseToggleAddMode = function ( house )
    {
    	$scope.room = {};
    	house.addMode = !house.addMode;
    };

	$scope.showDetail();
	$scope.pageClass = 'fadeZoom';
}]);


web.controller('ComEmptyCtrl', ['$scope', '$location', 'CompanyFactory',
	function ($scope, $location, CompanyFactory) 
{

 	$scope.location = $location;

 	$scope.emptyAll = function ()
 	{

	    CompanyFactory.emptyAll( )
	    .success(function (data)
	    {
			$scope.companys = data;
 		});
 	}
	    	    
 	$scope.emptyAll();
 	$scope.pageClass = 'fadeZoom';
 	
 }]);

