package com.service.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.service.common.logger.ComLogger;
import com.service.common.logger.ComLoggerFactory;

public final class Constants
{

	private static final ComLogger logger = ComLoggerFactory.getLogger( Constants.class );
	private static String ip = "127.0.0.1";
	static
	{
		try
		{
			ip = InetAddress.getLocalHost().getHostAddress();
		}
		catch ( UnknownHostException e )
		{
			logger.error( "Could not obtain IP Address", e );
		}
	}
	
	//ace housing app
	public static final String TWITT_APP_ID 		=  	"lPDXrKNO9Ck9j6wNrFp6P2FAQ"; 
	public static final String TWITT_APP_SECRET 	=  	"NnRv1sqVce1u0mDJX02rMUlWXaCmLjX2HI5Cdb2fKB3NM2L2sd"; 
	public static final String TWITT_OAUTH_TOKEN 	= 	"2827545781-3xBI80lghzVaTymzsaXI58sMbvTfkBoeiQ3RzEb"; 
	public static final String TWITT_OAUTH_SECRET   = 	"WZBsnfVnW1iduMujkAejxdX1FTbQRVo2CE4P12wkO2wji";

	// 주거형태
	public static final String STATUS_1_0_ROOM			= "01";		// 1룸
	public static final String STATUS_1_5_ROOM 			= "02";		// 1.5품
	public static final String STATUS_2_0_ROOM 			= "03";		// 2룸
	public static final String STATUS_3_0_ROOM 			= "04";		// 3룸
	

	// 청소 진행과정
	public static final String STATUS_ACCEPT_NEW		= "100";	// 접수
	public static final String STATUS_ACCEPT_CANCLE 	= "101";	// 접수취소
	public static final String STATUS_ACCEPT_READY 		= "102";	// 접수대기
	public static final String STATUS_CHARGE_ASSIGN 	= "200";	// 담당자 할당
	public static final String STATUS_CLEAN_FINISH 		= "300";  	// 청소완료
	public static final String STATUS_CLEAN_RECALL 		= "301";  	// 청소결함보상
	public static final String STATUS_DEPOSIT_FINISH 	= "400";	// 입금완료
	public static final String STATUS_FINAL_FINISH 		= "500";	// 완료
	public static final String STATUS_FINAL_CANCLE 		= "501";	// 완료
		
	// 공실현황
	public static final String STATUS_IS_ZERO_Y			= "Y";		// 입실
	public static final String STATUS_IS_ZERO_N			= "N";		// 공실
	
	// 발주처구분
	public static final String STATUS_SORT_HOUSE		= "01";		// 주택관리
	public static final String STATUS_SORT_REALTY		= "02";		// 부동산
	public static final String STATUS_SORT_CLEAN		= "03";		// 청소
	public static final String STATUS_SORT_OTHER		= "04";		// 기타
	
	// 권한등급
	public static final long STATUS_AUTH_LEVEL_1		= 1;		// 주택관리
	public static final long STATUS_AUTH_LEVEL_2		= 2;		// 주택관리
	public static final long STATUS_AUTH_LEVEL_3		= 3;		// 주택관리
	
	// 업무분야
	public static final String STATUS_WORK_ADMIN		= "11";		// 주택관리
	public static final String STATUS_WORK_HOUSE		= "01";		// 주택관리
	public static final String STATUS_WORK_REALTY		= "02";		// 주택관리
	public static final String STATUS_WORK_CLEAN		= "03";		// 주택관리
	public static final String STATUS_WORK_SHOP			= "04";		// 주택관리
	public static final String STATUS_WORK_ARBEIT		= "05";		// 주택관리
	
	// 지역구
	public static final String STATUS_LOCAL_ALL			= "00";		// 주택관리
	public static final String STATUS_LOCAL_DONG		= "01";		// 주택관리
	public static final String STATUS_LOCAL_SEO			= "02";		// 주택관리
	public static final String STATUS_LOCAL_JUNG		= "03";		// 주택관리
	public static final String STATUS_LOCAL_YOU			= "04";		// 주택관리
	public static final String STATUS_LOCAL_DAE			= "05";		// 주택관리

	
	public static String stringCode (String type, String code )
	{
		String cname = "";
		
		if( code == null )
		{
			code = "";
		}

		if( type.equals( "LOCAL" ))
		{
			switch ( code )
			{
				case "00" : cname = "대전시"; 		break;
				case "01" : cname = "동구"; 		break;
				case "02" : cname = "서구"; 		break;
				case "03" : cname = "중구"; 		break;
				case "04" : cname = "유성구"; 		break;
				case "05" : cname = "대덕구"; 		break;
				default   :	cname = "";
			}
		}
		else if ( type.equals( "LIVING" ) )
		{
			// 주거형태
			switch ( code )
			{
				case "01" : cname = "1룸"; 		break;
				case "02" : cname = "1.5룸"; 	break;
				case "03" : cname = "2룸"; 		break;
				case "04" : cname = "m2룸"; 		break;
				case "05" : cname = "3룸"; 		break;
				case "06" : cname = "아파트(소형)"; 		break;
				case "07" : cname = "아파트(중형)"; 		break;
				case "08" : cname = "아파트(대형)"; 		break;
				default   :	cname = "";
			}
		}
		else if ( type.equals( "ISING" ) )
		{  	
			// 진행과정
			switch ( code )
			{
				case "100" :	cname = "접수중";		break;
				case "101" :	cname = "접수취소";	break;
				case "102" :	cname = "접수보퓨";	break;
				case "103" :	cname = "접수대기";	break;
				case "200" :	cname = "담당할당";	break;
				case "300" :	cname = "청소완료";	break;
				case "400" :	cname = "입금완료";	break;
				case "500" :	cname = "청소종료";	break;
				case "501" :	cname = "취소완료";	break;
				default    :	cname = "";
			}
		}
		else if ( type.equals( "ISZERO" ) )
		{  	
			// 공실현황
			switch ( code )
			{
				case "Y" :	cname = "입실";			break;
				case "N" :	cname = "공실";			break;
				default  :	cname = "";
			}
		}
		else if ( type.equals( "SORT" ) )
		{  	
			// 발주처구분
			switch ( code )
			{
				case "01" :	cname = "주택관리";		break;
				case "02" :	cname = "부동산";			break;
				case "03" :	cname = "입주청소";		break;
				case "04" :	cname = "SHOP";			break;		
				case "05" :	cname = "기타(알바)";		break;		
				default   :	cname = "";
			}
		}	
		else if ( type.equals( "READSORT" ) )
		{  	
			// 발주처구분
			switch ( code )
			{
				case "01" :	cname = "수도검침";		break;
				case "02" :	cname = "관리비";			break;
	
				default   :	cname = "";
			}
		}	
		else if ( type.equals( "USERAUTH" ) )
		{  	
			// 권한등급
			switch ( code )
			{
				case "1" :	cname = "Admin";			break;
				case "2" :	cname = "Active";			break;
				case "3" :	cname = "Staff";			break;
				default  :	cname = "";
			}
		}
		else if ( type.equals( "WORKFIELD" ) )
		{  	
			// 업무분야
			switch ( code )
			{
				case "11" :	cname = "관리(admin)";	break;
				case "01" :	cname = "주택관리";		break;
				case "02" :	cname = "부동산";			break;
				case "03" :	cname = "입주청소";		break;	
				case "04" :	cname = "SHOP";			break;	
				case "05" :	cname = "기타(알바)";		break;	
				default   :	cname = "";
			}
		}
		
		return cname;
	}
	
	public static String longCode (String type, long code )
	{
		String cname = "";
		int i = (int)code;
		
		if ( type.equals( "USERAUTH" ) )
		{  	
			// 권한등급
			switch ( i )
			{
				case 1 :	cname = "1급";			break;
				case 2 :	cname = "2급";			break;
				case 3 :	cname = "3급";			break;
				case 4 :	cname = "4급";			break;	
				case 5 :	cname = "5급";			break;	
				default  :	cname = "";
			}
		}
		
		
		return cname;
	}
	
	// Common
	public static final String EX_KEY_API = "uri";
	public static final String IP_ADDRESS = ip;

	public static final String REQ = "REQ"; // request
	public static final String RES = "RES"; // response

	public static final String EX_PAYLOAD = "payload";
	public static final String EX_STATUS = "status";
	public static final String EX_RCODE = "rcode";
	public static final String EX_ELAPSED = "elapsed";

	// PARAM
	public static final String PARAM_UUID = "uid";

	// PARAM SIZE
	public static final long SIZE_UUID_MIN = 1;
	public static final long SIZE_UUID_MAX = 10;

	// For encrypt/decrypt parameter
	public static final String BASE_KEY = "com.test.project"; // 16*8 = 128bit
	public static final String ALGORITHM = "AES";

}
