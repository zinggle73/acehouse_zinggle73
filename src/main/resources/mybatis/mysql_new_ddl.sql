CREATE TABLE `clean` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`comId` CHAR(5) NOT NULL DEFAULT '',
	`husId` CHAR(8) NOT NULL DEFAULT '',
	`roomNum` VARCHAR(5) NOT NULL DEFAULT '',
	`orderer` VARCHAR(15) NULL DEFAULT NULL,
	`phone` VARCHAR(15) NULL DEFAULT NULL,
	`pay` INT(11) NULL DEFAULT NULL,
	`addPay` INT(11) NULL DEFAULT NULL,
	`living` CHAR(2) NULL DEFAULT NULL,
	`charge` VARCHAR(15) NULL DEFAULT NULL,
	`deposit` CHAR(1) NULL DEFAULT NULL,
	`isIng` CHAR(3) NULL DEFAULT NULL,
	`address` VARCHAR(50) NULL DEFAULT NULL,
	`memo` VARCHAR(100) NULL DEFAULT NULL,
	`gatepass` VARCHAR(10) NULL DEFAULT NULL,
	`bepass` VARCHAR(10) NULL DEFAULT NULL,
	`afpass` VARCHAR(10) NULL DEFAULT NULL,
	`inputDate` DATETIME NULL DEFAULT NULL,
	`orderDate` DATETIME NULL DEFAULT NULL,
	`hopeDate` DATETIME NULL DEFAULT NULL,
	`confirmDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;

=================================================================

CREATE TABLE `codez` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`groupCode` CHAR(5) NULL DEFAULT NULL,
	`detailCode` CHAR(5) NULL DEFAULT NULL,
	`codeName` VARCHAR(20) NULL DEFAULT NULL,
	`codeExplan` VARCHAR(100) NULL DEFAULT NULL,
	`codeOr` CHAR(1) NULL DEFAULT 'Y',
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;


=================================================================

CREATE TABLE `company` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`sort` CHAR(2) NULL DEFAULT NULL,
	`comId` CHAR(5) NOT NULL DEFAULT '',
	`comName` VARCHAR(30) NOT NULL DEFAULT '',
	`comNum` VARCHAR(15) NULL DEFAULT NULL,
	`ceoName` VARCHAR(10) NULL DEFAULT NULL,
	`cell` VARCHAR(15) NULL DEFAULT NULL,
	`phone` VARCHAR(15) NULL DEFAULT NULL,
	`fax` VARCHAR(15) NULL DEFAULT NULL,
	`twitt` VARCHAR(15) NULL DEFAULT NULL,
	`local` CHAR(2) NULL DEFAULT NULL,
	`address` VARCHAR(50) NULL DEFAULT NULL,
	`bank` VARCHAR(20) NULL DEFAULT NULL,
	`account` VARCHAR(25) NULL DEFAULT NULL,
	`accName` VARCHAR(12) NULL DEFAULT NULL,
	`registDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   

=================================================================

insert into company ( sort, comId, comName, ceoName, cell, twitt, local, registDate )
values ('01', 'C-101', 'ACE HOUSING', '윤용도','010-2279-7519','zinggle73','01', SYSDATE());


=================================================================

CREATE TABLE `contract` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`comId` CHAR(5) NOT NULL DEFAULT '',
	`year` CHAR(4) NULL DEFAULT NULL,
	`local` CHAR(2) NULL DEFAULT NULL,
	`living` CHAR(2) NULL DEFAULT NULL,
	`pay` INT(11) NULL DEFAULT NULL,
	`isYn` CHAR(1) NULL DEFAULT NULL,
	`registDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;

=================================================================

CREATE TABLE `house` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`comId` CHAR(5) NOT NULL DEFAULT '',
	`husId` CHAR(8) NOT NULL DEFAULT '',
	`husName` VARCHAR(20) NOT NULL DEFAULT '',
	`owner` VARCHAR(10) NULL DEFAULT NULL,
	`phone` VARCHAR(15) NULL DEFAULT NULL,
	`local` CHAR(2) NULL DEFAULT NULL,
	`address` VARCHAR(50) NULL DEFAULT NULL,
	`mgrPrice` INT(11) NULL DEFAULT '0',
	`gatePass` VARCHAR(10) NULL DEFAULT NULL,
	`registDate` DATETIME NULL DEFAULT NULL,
	`isIng` CHAR(1) NOT NULL DEFAULT 'Y',
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;


=================================================================

CREATE TABLE `reader` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`husId` CHAR(8) NOT NULL DEFAULT '',
	`roomNum` VARCHAR(5) NULL DEFAULT NULL,
	`sort` CHAR(2) NOT NULL DEFAULT '',
	`year` INT(11) NOT NULL,
	`month` CHAR(2) NOT NULL DEFAULT '',
	`value` INT(11) NOT NULL DEFAULT '0',
	`userId` VARCHAR(12) NOT NULL DEFAULT '',
	`readDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;




=================================================================


CREATE TABLE `room` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`husId` CHAR(8) NOT NULL DEFAULT '',
	`roomNum` VARCHAR(5) NOT NULL DEFAULT '',
	`living` CHAR(2) NULL DEFAULT NULL,
	`amount` INT(11) NULL DEFAULT NULL,
	`beforePass` VARCHAR(10) NULL DEFAULT NULL,
	`afterPass` VARCHAR(10) NULL DEFAULT NULL,
	`isZero` CHAR(1) NULL DEFAULT 'Y',
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;



=================================================================


CREATE TABLE `user` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`comId` CHAR(5) NULL DEFAULT NULL,
	`userId` VARCHAR(12) NOT NULL DEFAULT '',
	`passwd` VARCHAR(100) NOT NULL DEFAULT '',
	`token` VARCHAR(50) NULL DEFAULT NULL,
	`userName` VARCHAR(20) NULL DEFAULT NULL,
	`email` VARCHAR(30) NULL DEFAULT NULL,
	`phone` VARCHAR(15) NULL DEFAULT NULL,
	`twitt` VARCHAR(15) NULL DEFAULT NULL,
	`workField` CHAR(2) NULL DEFAULT NULL,
	`userAuth` CHAR(1) NULL DEFAULT '3',
	`registDate` DATETIME NULL DEFAULT NULL,
	`connCount` INT(10) UNSIGNED NULL DEFAULT '0',
	`lastLoginDate` DATETIME NULL DEFAULT NULL,
	`nowLoginDate` DATETIME NULL DEFAULT NULL,
	`logoutDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;




=================================================================

drop  table  userHistory ; 

CREATE TABLE `userHistory` (
	`id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`comId` CHAR(5) NOT NULL DEFAULT '',
	`userId` VARCHAR(12) NOT NULL DEFAULT '',
	`connCount` INT(10) NULL DEFAULT '0',
	`lastLoginDate` DATETIME NULL DEFAULT NULL,
	`nowLoginDate` DATETIME NULL DEFAULT NULL,
	`logoutDate` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
ENGINE=MyISAM default CHARSET=utf8;   
;



=================================================================





=================================================================





