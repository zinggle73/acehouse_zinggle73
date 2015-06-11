package com.service.common.util;

import java.util.Calendar;

public class CalendarUtil
{
	static Calendar today = Calendar.getInstance();    
	
	// 이 해의 년도
	public static String getToYear( )
	{
		int year = today.get(Calendar.YEAR);
		return String.valueOf( year );
	}
	
	public static String getYestYear( )
	{
		int year = (today.get(Calendar.YEAR)-1);
		return String.valueOf( year );
	}
	
	// 전월
	public static String getYestMonth( )
	{
		String monthStr = "";
		
		int month = today.get(Calendar.MONTH);
		
		if( month < 10 )
		{
			monthStr = "0"+month;
		}
		else
		{
			monthStr = ""+month;
		}
		
		return  monthStr;
	}
	
	public static String getYestYearMonth( )
	{
		String monthStr = "";
		
		int month = today.get(Calendar.MONTH);
		
		if( month < 10 )
		{
			monthStr = "0"+month;
		}
		else
		{
			monthStr = ""+month;
		}
		
		if( month == 0 )
		{
			monthStr = getYestYear()+"-12";
		}
		else
		{
			monthStr = getToYear()+"-"+monthStr;
		}
		
		return  monthStr;
	}
	
	// 당월
	public static String getToMonth( )
	{
		String monthStr = "";
		
		int month = (today.get(Calendar.MONTH)+1);
		
		if( month < 10 )
		{
			monthStr = "0"+month;
		}
		else
		{
			monthStr = ""+month;
		}
		
		return  monthStr;
	}
	
	public static String getToYearMonth( )
	{
		
		return  getToYear()+"-"+getToMonth();
	}

	// 이 달의 몇 일
	public static String getDate( )
	{
		String dayStr = "";
		
		int day = today.get(Calendar.DATE);
		
		if( day < 10 )
		{
			dayStr = "0"+day;
		}
		else
		{
			dayStr = ""+day;
		}
		
		return  dayStr;
	}
	
	public static String getToYearMonthDay( )
	{
		
		return  getToYear()+"-"+getToMonth()+"-"+getDate();
	}
	
	// 이 해의 몇 째 주
	public static String getWeekOfYear( )
	{
		int woy = today.get(Calendar.WEEK_OF_YEAR);
		return String.valueOf( woy );
	}
	
	// 이 달의 몇 째 주
	public static String getWeekOfMonth( )
	{
		int wom = today.get(Calendar.WEEK_OF_MONTH);
		return String.valueOf( wom );
	}

	// 이 달의 몇 일
	public static String getDayOfMonth( )
	{
		int dom = today.get(Calendar.DAY_OF_MONTH);
		return String.valueOf( dom );
	}
	
	// 이 해의 몇 일
	public static String getDayOfYear( )
	{
		int doy = today.get(Calendar.DAY_OF_YEAR);
		return String.valueOf( doy );
	}
		
	// 요일(1~7, 1:일요일)
	public static String getDayOfWeek( )
	{
		int dow = today.get(Calendar.DAY_OF_WEEK);
		return String.valueOf( dow );
	}
	
	// 이 달의 몇 째 요일
	public static String getDayOfWeekInMonth( )
	{
		int dowim = today.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		return String.valueOf( dowim );
	}
	
	// 오전_오후(0:오전, 1:오후)
	public static String getAmPm( )
	{
		int ap = today.get(Calendar.AM_PM);
		return String.valueOf( ap );
	}
	
	// 시간(0~11)
	public static String getHour( )
	{
		int ho = today.get(Calendar.HOUR);
		return String.valueOf( ho );
	}
	
	// 시간(0~23)
	public static String getHourOfDay( )
	{
		int hod = today.get(Calendar.HOUR_OF_DAY);
		return String.valueOf( hod );
	}
	
	// 분(0~59)
	public static String getMinute( )
	{
		int m = today.get(Calendar.MINUTE);
		return String.valueOf( m );
	}

	// 이 달의 마지막 날
	public static String getActualMaximum( )
	{
		int amd = today.getActualMaximum(Calendar.DATE);
		return String.valueOf( amd );
	}	
     
}
