package com.service.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 날짜와 관련된 처리를 하는 Util 클래스
 * 
 * @author yonghyun@siusys.com
 */
public class DateUtil
{

	/**
	 * 현재 날짜와 시간을 입력된 format에 맞게 반환한다.
	 * 
	 * @param format
	 *        날짜 format
	 * @return 변환된 오늘 날짜와 시간
	 */
	public static String getCurrentDate( String format )
	{
		SimpleDateFormat dFormat = new SimpleDateFormat( format );
		return dFormat.format( new Date() );
	}
	
	public static String getCurrentDate( )
	{
		SimpleDateFormat dFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		return dFormat.format( new Date() );
	}
	
	public static String getCurrentDay( String format )
	{
		return getCurrentDate( format );
	}

	public static String getCurrentMonth( String format )
	{
		return getCurrentDate(  format  );
	}

	public static String getCurrentYear( String format )
	{
		return getCurrentDate( format );
	}

	/**
	 * Date object를 입력된 format으로 변환 후 String으로 반환한다.
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate( Date date, String format )
	{
		SimpleDateFormat dFormat = new SimpleDateFormat( format );
		return dFormat.format( date );
	}

	/**
	 * 입력된 date에서 field의 값을 amount 만큼 더하거나 빼서 String으로 반환한다.
	 * 
	 * @param date
	 *        'yyyy-MM-dd' 형식이거나 'yyyyMMdd' 형식의 date
	 * @param field
	 *        Calendar의 field 값
	 * @param amount
	 *        더하거나 뺄 값
	 * @return
	 */
	public static String getDate( String date, int field, int amount )
	{
		date = date.replace( "-", "" );
		date = date.substring( 0, 8 );

		int year = Integer.parseInt( date.substring( 0, 4 ) );
		int month = Integer.parseInt( date.substring( 4, 6 ) );
		int day = Integer.parseInt( date.substring( 6, 8 ) );

		Calendar calendar = Calendar.getInstance();
		calendar.set( year, month - 1, day );
		calendar.add( field, amount );

		Date amountDate = calendar.getTime();

		return formatDate( amountDate, "yyyy-MM-dd" );
	}

	/**
	 * date1이 date2보다 큰 지 확인한다.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compare( String date1, String date2 )
	{
		date1 = date1.replace( " ", "" );
		date1 = date1.replace( "-", "" );
		date1 = date1.replace( ":", "" );
		date1 = date1.substring( 0, 12 );

		long date1L = Long.parseLong( date1 );

		date2 = date2.replace( " ", "" );
		date2 = date2.replace( "-", "" );
		date2 = date2.replace( ":", "" );
		date2 = date2.substring( 0, 12 );

		long date2L = Long.parseLong( date2 );

		return date1L > date2L;
	}

	/**
	 * String("yyyy-MM-dd HH:mm:ss")형의 특정일자를 long형으로 변환후 현재일자와 비교하여 지난 시간날아내기
	 * 
	 * @Param dateTime
	 * 
	 * @ReturnType String
	 * @throws
	 */
	public static String timeAgo( String dateTime )
	{
		if ( dateTime == null || dateTime.equals( "" ) )
		{
			return "일자없음";
		}

		SimpleDateFormat transFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		long cha = 0;
		Date to = new Date();
		String ago = "";

		try
		{
			to = transFormat.parse( dateTime );
			long date = to.getTime();
			cha = ( System.currentTimeMillis() - date ) / 1000;

			if ( cha < 60 )
			{
				ago = "1분전";
			}
			else if ( cha < 3600 )
			{
				ago = ( cha / 60 ) + "분전";
			}
			else if ( cha < 86400 )
			{
				ago = ( cha / 60 / 60 ) + "시간전";
			}
			else if ( cha < 2592000 )
			{
				ago = ( cha / 60 / 60 / 24 ) + "일전";
			}
			else
			{
				ago = DateTime.getFormattedDateString( to, "yyyy-MM-dd" );
			}
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
		}

		return ago;
	}
	
	/**
	 * String("yyyy-MM-dd HH:mm:ss")형의 특정일자를 오전 오후 변환
	 *
	 * @Param dateTime
	 *
	 * @ReturnType String
	 * @throws
	 */
	public static String ampmAgo( String dateTime )
	{
		if ( dateTime == null || dateTime.equals( "" ) )
		{
			return "일자없음";
		}
		
		SimpleDateFormat transFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date to = new Date();
		String ago = "";
		long cha = 0;
		
		try
		{
			to = transFormat.parse( dateTime );
			long date = to.getTime();
			cha = ( System.currentTimeMillis() - date ) / 1000;
			
			if ( cha < 86400 )
			{
				SimpleDateFormat sdf = new SimpleDateFormat("a HH:mm"); 
				ago = sdf.format(to).toString(); 
			}
			else if ( cha < 2592000 )
			{				
				ago = DateTime.getFormattedDateString( to, "MM-dd" );
			}
			else 
			{ 
				ago = DateTime.getFormattedDateString( to, "yyyy-MM-dd" );
			}
		}
		catch ( ParseException e )
		{
			e.printStackTrace();
		}

		return ago;
	}
}
