drop  table  user ; 

CREATE TABLE user 
(
	id 				INT unsigned not null auto_increment,
	comId 			char(5) not null  default "",
	userId 			varchar(12) not null default "",
	passwd 			varchar(50) not null default "",
	token 			varchar(50),
	userName		varchar(20),
	email			varchar(30),
	phone			varchar(15),
	cacao			varchar(15),
	workField		char(2),
	userAuth 		char(1),
	registDate  	DATETIME,
	connCount		INT default 0,
	lastLoginDate	DATETIME,
	nowLoginDate	DATETIME,
	logoutDate		DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8;   


drop  table  userHistory ; 

CREATE TABLE userHistory 
(
	id 				INT unsigned not null auto_increment,
	comId 			char(5) not null  default "",
	userId 			varchar(12) not null default "",
	connCount		INT default 0,
	lastLoginDate	DATETIME,
	nowLoginDate	DATETIME,
	logoutDate		DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8;   


drop  table  company ; 

CREATE TABLE company 
(
	id 			INT unsigned not null auto_increment,
	sort 		char(2),
	comId 		char(5) not null  default "",
	comName 	varchar(30) not null default "",
	comNum 		varchar(15),
	ceoName 	varchar(10),
	cell 		varchar(15),
	phone		varchar(15),
	fax  		varchar(15),
	cacao		varchar(15),
	local 		char(2),
	address 	varchar(50),
	bank		varchar(20),
	account		varchar(25),
	accName		varchar(12),
	registDate  DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 



drop  table  house ; 

CREATE TABLE house 
(
	id 			INT unsigned not null auto_increment,
	comId 		char(5) not null  default "",
	husId 		char(8) not null  default "",
	husName 	varchar(20) not null default "",
	owner 		varchar(10),
	phone 		varchar(15),
	local 		char(2),
	address 	varchar(50),
	mgrPrice    INT 	default 0,
	gatePass	varchar(10),
	registDate  DATETIME,
	isIng 		char(1) not null default "Y",
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  room ; 

CREATE TABLE room 
(
	id 			INT unsigned not null auto_increment,
	husId 		char(8) not null  default "",
	roomNum 	varchar(5) not null default "",
	living 		char(1),
	amount  	INT,
	beforePass 	varchar(10),
	afterPass 	varchar(10),
	isZero 		char(1),
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 



drop  table  clean ; 

CREATE TABLE clean 
(
	id 			INT unsigned not null auto_increment,
	comId 		char(5) not null  default "",
	husId 		char(8) not null  default "",
	roomNum 	varchar(5) not null default "",
	orderer 	varchar(15),
	phone		varchar(15),
	pay  		INT,
	addPay		INT,
	living 		char(2),
	charge 		varchar(15),
	deposit 	char(1),
	isIng  		char(3),
	address		varchar(50),
	memo		varchar(100),
	bepass	varchar(10),
	afpass	varchar(10),
	orderDate  	DATETIME,
	confirmDate	DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  contract ; 

CREATE TABLE contract 
(
	id 			INT unsigned not null auto_increment,
	comId 		char(5) not null  default "",
	year 		char(4),
	local 		char(2),
	living		char(2),
	pay  		INT,
	isYn  		char(1),
	registDate 	DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  schedule ; 

CREATE TABLE schedule 
(
	id 				INT unsigned not null auto_increment,
	comId 			char(5) not null  default "",
	husId 			char(5) not null  default "",
	roomNum 		varchar(5) not null default "",
	addr 			varchar(50),
	charge 			varchar(50),
	phone			varchar(15),
	memo 			varchar(200),
	scheduleDate  	DATETIME,
	registDate  	DATETIME,
	modifyDate  	DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  accept ; 

CREATE TABLE accept 
(
	id 				INT unsigned not null auto_increment,
	comId			char(5) not null  default "",	
	husId 			char(5) not null  default "",			
	roomNum 		varchar(5) not null default "",
	year 			char(4),
	month 			char(2),
	isReceipt		char(1) default "N",
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  arbeit ; 

CREATE TABLE arbeit 
(
	id 				INT unsigned not null auto_increment,
	comId			char(5) not null  default "",	
	name 			varchar(10),
	phone			varchar(15),
	zipcode 		varchar(7),
	addr 			varchar(50),
	addrDetail		varchar(50),
	gender			char(1),
	grade			char(1),
	registDate		DATETIME,
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  codez ; 

CREATE TABLE codez 
(
	id 				INT unsigned not null auto_increment,
	groupCode		char(5),	
	detailCode		char(5),	
	codeName		varchar(20),
	codeExplan 		varchar(100),
	codeOr 			char(1) default "Y",
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 


drop  table  options ; 

CREATE TABLE options 
(
	id 				INT unsigned not null auto_increment,
	husId			char(5) not null  default "",	
	roomNum 		varchar(5) not null default "",
	opKey			char(2),
	opValue 		varchar(20),
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 



drop  table  receipt ; 

CREATE TABLE receipt 
(
	id 				INT unsigned not null auto_increment,
	comId			char(5) not null  default "",	
	husId 			char(5) not null  default "",			
	roomNum 		varchar(5) not null default "",
	year 			char(4),
	month 			char(2),
	isReceipt		char(1) default "N",
	primary key (id)
) ENGINE=MyISAM default CHARSET=utf8; 

// 수도검침
drop  table  reader ;

CREATE TABLE reader 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	husId CHAR(8) NOT NULL DEFAULT '',
	roomNum VARCHAR(5) NULL DEFAULT '',
	sort CHAR(2) NOT NULL DEFAULT '',
	year INT NOT NULL,
	month CHAR(2) NOT NULL DEFAULT '',
	value INT NOT NULL DEFAULT 0,
	userId VARCHAR(12) NOT NULL DEFAULT '',
	readDate DATETIME,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 

// 상품
drop table goods;

CREATE TABLE goods 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	goodsId 	CHAR(10) NOT NULL DEFAULT '',
	category 	CHAR(2) NOT NULL DEFAULT '',
	shopId 		CHAR(5) NOT NULL DEFAULT '',
	goodsName 	VARCHAR(50) NOT NULL DEFAULT '',
	maker 		VARCHAR(30),
	standard 	VARCHAR(30),
	goodsInfo 	VARCHAR(200),
	qty 		INT NOT NULL DEFAULT 0,
	orderDate 	DATETIME,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


// 상품가격
drop table goodsPay;

CREATE TABLE goodsPay 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	goodsId 	CHAR(10) NOT NULL DEFAULT '',		
	applyDate	DATATIME,
	finalDate 	DATETIME,
	pay		INT,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


//  재고현황
drop table stock;

CREATE TABLE stock 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	shopId 		CHAR(5) NULL DEFAULT NULL,
	goodsId 	CHAR(10) NOT NULL DEFAULT '',	
	sort		CHAR(2) NOT NULL DEFAULT '',	
	qty 		INT NOT NULL DEFAULT 0,
	totalQty	INT NOT NULL DEFAULT 0,
	inDate 		DATETIME NULL DEFAULT NULL,
	outDate 	DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


// 장바구니
drop table cart;

CREATE TABLE cart 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	goodsId 	CHAR(10) NOT NULL DEFAULT '',
	shopId 		CHAR(5) NOT NULL DEFAULT '',
	comId		CHAR(5) NOT NULL DEFAULT '',
	userId 		VARCHAR(12) NOT NULL DEFAULT '',
	qty 		INT NOT NULL DEFAULT 0,
	price		INT,
	inputDate 	DATETIME,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


// 주문
drop table order;

CREATE TABLE order 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	orderId		CHAR(14) NOT NULL DEFAULT '',
	shopId 		CHAR(5) NOT NULL DEFAULT '',
	comId		CHAR(5) NOT NULL DEFAULT '',
	userId 		VARCHAR(12) NOT NULL DEFAULT '',
	totalPay	INT,
	payment		CHAR(2),
	status		CHAR(2) NOT NULL DEFAULT '',
	supplyDate	DATETIME,
	orderDate 	DATETIME,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


// 주문상세
drop table orderItem;

CREATE TABLE orderItem 
(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	orderId		CHAR(14) NOT NULL DEFAULT '',
	goodsId 	CHAR(10) NOT NULL DEFAULT '',
	qty 		INT NOT NULL DEFAULT 0,
	pay			INT,
	PRIMARY KEY (id)
) ENGINE=MyISAM default CHARSET=utf8; 


select
		'당월' info,
		max(if(month='01',value,0)) m01,
		max(if(month='02',value,0)) m02,
		max(if(month='03',value,0)) m03,
		max(if(month='04',value,0)) m04,
		max(if(month='05',value,0)) m05,
		max(if(month='06',value,0)) m06,
		max(if(month='07',value,0)) m07,
		max(if(month='08',value,0)) m08,
		max(if(month='09',value,0)) m09,
		max(if(month='10',value,0)) m10,
		max(if(month='11',value,0)) m11,
		max(if(month='12',value,0)) m12
from reader
where  husId ='H-100001'
and roomNum = '101'
and year = 2015
union
select
		'전월' info,
		max(if(year=2015-1 && month='12',value,0)) m01,
		max(if(month='01',value,0)) m02,
		max(if(month='02',value,0)) m03,
		max(if(month='03',value,0)) m04,
		max(if(month='04',value,0)) m05,
		max(if(month='05',value,0)) m06,
		max(if(month='06',value,0)) m07,
		max(if(month='07',value,0)) m08,
		max(if(month='08',value,0)) m09,
		max(if(month='09',value,0)) m10,
		max(if(month='10',value,0)) m11,
		max(if(month='11',value,0)) m12
from reader
where husId ='H-100001'
and roomNum = '101'
	


select
ifnull(count(r.husId), '0') zeroNum
from 
room r, housing hi
where 
r.husId = hi.husId
and hi.isIng = 'Y'
and hi.endDate is null
and r.isZero = 'N'
and hi.comId = 'C-101'
and hi.husId = 'H-100002'
group by hi.comId, r.husId
;


//통계
select
CONCAT(ci.comName,'-','전체') info, c.comId, sum(pay+addPay) pay, count(c.comId) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, company ci
where c.comId = ci.comId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng not in ('101', '501')
group by c.comId, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;
select
CONCAT(ci.comName,'-','완료') info, c.comId , sum(pay+addPay) pay, count(c.comId) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, company ci
where c.comId = ci.comId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng = '500'
group by c.comId, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;
select
CONCAT(ci.comName,'-','진행중') info, c.comId , sum(pay+addPay) pay, count(c.comId) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, company ci
where c.comId = ci.comId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng not in ('101', '501', '500')
group by c.comId, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;


select
CONCAT(u.userName,'-','전체') info, c.charge , sum(pay+addPay) pay, count(c.charge) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, user u
where c.charge = u.userId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng not in ('101', '501')
group by c.charge, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;
select
CONCAT(u.userName,'-','완료') info, c.charge , sum(pay+addPay) pay, count(c.charge) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, user u
where c.charge = u.userId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng = '500'
group by c.charge, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;
select
CONCAT(u.userName,'-','진행중') info, c.charge , sum(pay+addPay) pay, count(c.charge) total, EXTRACT(YEAR_MONTH FROM confirmDate) month
from clean c, user u
where c.charge = u.userId
#and cast(c.confirmDate as char)  like '2015%'
and c.isIng not in ('101', '501','500')
group by c.charge, EXTRACT(YEAR_MONTH FROM confirmDate)
with rollup
;