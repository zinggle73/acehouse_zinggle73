package com.service.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateTime
{
	static final long DAY = 86400000; // 24 * 60 * 60 * 1000

	/**
	 * Don't let anyone instantiate this class
	 */
	private DateTime()
	{
	}

	/**
	 * check date string validation with the default format "yyyy-MM-dd".
	 * 
	 * @param s
	 *        date string you want to check with default format "yyyy-MM-dd".
	 */
	public static void check( String s ) throws Exception
	{
		DateTime.check( s, "yyyy-MM-dd" );
	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *        date string you want to check.
	 * @param format
	 *        string representation of the date format. For example,
	 *        "yyyy-MM-dd".
	 */
	public static void check( String s, String format ) throws java.text.ParseException
	{
		if ( s == null )
			throw new NullPointerException( "date string to check is null" );
		if ( format == null )
			throw new NullPointerException( "format string to check date is null" );

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( format, java.util.Locale.KOREA );
		java.util.Date date = null;
		try
		{
			date = formatter.parse( s );
		}
		catch ( java.text.ParseException e )
		{
			throw new java.text.ParseException( e.getMessage() + " with format \"" + format + "\"", e.getErrorOffset() );
		}

		if ( !formatter.format( date ).equals( s ) )
			throw new java.text.ParseException( "Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0 );
	}
	
	

	/**
	 * check date string validation with the default format "yyyyMMdd".
	 * 
	 * @param s
	 *        date string you want to check with default format "yyyyMMdd"
	 * @return boolean true
	 */
	public static boolean isValid( String s ) throws Exception
	{
		return DateTime.isValid( s, "yyyyMMdd" );
	}

	/**
	 * check date string validation with an user defined format.
	 * 
	 * @param s
	 *        date string you want to check.
	 * @param format
	 *        string representation of the date format. For example,
	 *        "yyyy-MM-dd".
	 * @return boolean true
	 */
	public static boolean isValid( String s, String format )
	{

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( format, java.util.Locale.KOREA );
		java.util.Date date = null;
		try
		{
			date = formatter.parse( s );
		}
		catch ( java.text.ParseException e )
		{
			return false;
		}

		if ( !formatter.format( date ).equals( s ) )
			return false;

		return true;
	}

	/**
	 * @return formatted string representation of current day with "yyyy-MM-dd".
	 */
	public static String getDateString()
	{
		return getDateString( "-" );
	}

	/**
	 * @return formatted string representation of current day with "yyyy-MM-dd".
	 */
	public static String getDateString( String delim )
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "yyyy" + delim + "MM" + delim + "dd",
				java.util.Locale.KOREA );
		return formatter.format( new java.util.Date() );
	}

	/**
	 * 
	 * For example, String time =
	 * DateTime.getFormatString("yyyy-MM-dd HH:mm:ss");
	 * 
	 * @param java
	 *        .lang.String pattern "yyyy, MM, dd, HH, mm, ss and more"
	 * @return formatted string representation of current day and time with your
	 *         pattern.
	 */
	public static String getFormatString( String pattern )
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( pattern, java.util.Locale.KOREA );
		String dateString = formatter.format( new java.util.Date() );
		return dateString;
	}

	/**
	 * @return formatted string representation of current day with "yyyyMMdd".
	 */
	public static String getShortDateString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "yyyyMMdd", java.util.Locale.KOREA );
		return formatter.format( new java.util.Date() );
	}

	/**
	 * @return formatted string representation of current time with "HHmmss".
	 */
	public static String getShortTimeString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "HHmmss", java.util.Locale.KOREA );
		return formatter.format( new java.util.Date() );
	}

	/**
	 * @return formatted string representation of current time with
	 *         "yyyy-MM-dd-HH:mm:ss".
	 */
	public static String getTimeStampString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS",
				java.util.Locale.KOREA );
		return formatter.format( new java.util.Date() );
	}

	/**
	 * @return formatted string representation of current time with "HH:mm:ss".
	 */
	public static String getTimeString()
	{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat( "HH:mm:ss.SSS", java.util.Locale.KOREA );
		return formatter.format( new java.util.Date() );
	}

	public static java.util.Date getBeforeDate( int howDay, String toDay )
	{
		java.util.Date currentDate = null;

		try
		{
			int year = Integer.parseInt( toDay.substring( 0, 4 ) );
			int month = Integer.parseInt( toDay.substring( 4, 6 ) );
			int day = Integer.parseInt( toDay.substring( 6, 8 ) );

			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			rightNow.set( year, month - 1, day );
			currentDate = rightNow.getTime();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}

		java.util.Date willBeforeDate = new java.util.Date();
		willBeforeDate.setTime( currentDate.getTime() - ( (long)howDay * DAY ) );

		return willBeforeDate;
	}

	public static java.util.Date getAfterDate( int howDay, String toDay )
	{
		java.util.Date currentDate = null;

		try
		{
			int year = Integer.parseInt( toDay.substring( 0, 4 ) );
			int month = Integer.parseInt( toDay.substring( 4, 6 ) );
			int day = Integer.parseInt( toDay.substring( 6, 8 ) );

			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			rightNow.set( year, month - 1, day );
			currentDate = rightNow.getTime();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return null;
		}

		java.util.Date willAfterDate = new java.util.Date();
		willAfterDate.setTime( currentDate.getTime() + ( (long)howDay * DAY ) );

		return willAfterDate;
	}

	public static String getDateFromDateStr( String toDay, String format )
	{
		java.util.Date currentDate = null;

		try
		{
			int year = Integer.parseInt( toDay.substring( 0, 4 ) );
			int month = Integer.parseInt( toDay.substring( 4, 6 ) );
			int day = Integer.parseInt( toDay.substring( 6, 8 ) );

			java.util.Calendar rightNow = java.util.Calendar.getInstance();
			rightNow.set( year, month - 1, day );
			currentDate = rightNow.getTime();

			return getFormattedDateString( currentDate, format );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return "";
		}
	}

	public static String getAfterStringDate( int howDay, String toDay, String seperator )
	{
		java.util.Date after = getAfterDate( howDay, toDay );
		java.util.Calendar calendar = new java.util.GregorianCalendar();
		calendar.setTime( after );

		int month = calendar.get( Calendar.MONTH ) + 1;
		String monthStr = "";
		if ( month < 10 )
		{
			monthStr = "0" + String.valueOf( month );
		}
		else
		{
			monthStr = String.valueOf( month );
		}

		int day = calendar.get( java.util.Calendar.DAY_OF_MONTH );
		String dayStr = "";
		if ( day < 10 )
		{
			dayStr = "0" + String.valueOf( day );
		}
		else
		{
			dayStr = String.valueOf( day );
		}

		return String.valueOf( calendar.get( java.util.Calendar.YEAR ) ) + seperator + monthStr + seperator + dayStr;
	}

	public static String getBeforeStringDate( int howDay, String toDay, String seperator )
	{
		Date before = getBeforeDate( howDay, toDay );
		Calendar calendar = new GregorianCalendar();
		calendar.setTime( before );

		int month = calendar.get( Calendar.MONTH ) + 1;
		String monthStr = "";
		if ( month < 10 )
		{
			monthStr = "0" + String.valueOf( month );
		}
		else
		{
			monthStr = String.valueOf( month );
		}

		int day = calendar.get( java.util.Calendar.DAY_OF_MONTH );
		String dayStr = "";
		if ( day < 10 )
		{
			dayStr = "0" + String.valueOf( day );
		}
		else
		{
			dayStr = String.valueOf( day );
		}

		return String.valueOf( calendar.get( java.util.Calendar.YEAR ) ) + seperator + monthStr + seperator + dayStr;
	}

	public static String getFormattedDateString( java.util.Date date, String format )
	{
		return ( new java.text.SimpleDateFormat( format ) ).format( date );
	}

	
	public static String getUTC( String formatDate )
	{
		if( formatDate == null )
		{
			return "";
		}
		
		TimeZone tz2;
        Date date = getStrDate( formatDate );
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss (z Z)");

        tz2 = TimeZone.getTimeZone("Asia/Seoul");
        df.setTimeZone(tz2);
        //System.out.format("%s%n%s%n%n", tz2.getDisplayName(), df.format(date));

        return  date.toString(); //df.format( date );
	}
	
	
	public static java.util.Date getStrDate( String textDate )
	{

	   java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

	   java.util.Date date = null;
	   
		try
		{
			date = format.parse(textDate);

		}
		catch ( ParseException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
		
	}
	
	
	/**
	 * 
	 * 
	 * @return the translated string.
	 * @param date
	 *        String
	 */
	public static String printDate( String date )
	{
		if ( date == null )
			return "";
		return date.substring( 0, 10 ).replace( '-', '/' );
	}

	/**
	 * 
	 * @return the translated string.
	 * @param date
	 *        String
	 * @param seperator
	 *        char delimiter
	 */
	public static String printDate( String date, char seperator )
	{
		if ( date == null )
			return "";
		return date.substring( 0, 10 ).replace( '-', seperator );
	}

	/**
	 * 
	 * @return the translated string.
	 * @param date
	 *        String
	 */
	public static String printDateTime( String date )
	{
		if ( date == null )
			return "";
		return date.substring( 0, 16 ).replace( '-', '/' );
	}

	public static String getCurrentDate()
	{
		return getCurrentDate( "yyyyMMdd" );
	}

	public static String getCurrentMonth()
	{
		return getCurrentDate( "yyyyMM" );
	}
	
	public static String getCurrentPrintDate()
	{
		return getCurrentDate( "yyyy-MM-dd" );
	}

	public static String getCurrentPrintMonth()
	{
		return getCurrentDate( "yyyy-MM" );
	}

	public static String getCurrentYear()
	{
		return getCurrentDate( "yyyy" );
	}
	
	public static String[] getCurrentDates()
	{
		String curDate = getCurrentDate( "yyyyMMdd" );
		return new String[] { curDate.substring( 0, 4 ), curDate.substring( 4, 6 ), curDate.substring( 6, 8 ) };
	}

	public static String getCurrentDate( String formatDate )
	{
		Calendar cal = Calendar.getInstance();
		java.text.DateFormat df = new java.text.SimpleDateFormat( formatDate );

		String str = df.format( cal.getTime() );
		return str;
	}

	public static String[] getDates( String date )
	{
		if ( date == null )
			return null;
		String[] str = null;
		boolean bDate = true;

		if ( date == null || date.length() != 8 )
			bDate = false;

		for ( int nCnt = 0; nCnt < date.length(); nCnt++ )
		{
			if ( !Character.isDigit( date.charAt( nCnt ) ) )
			{
				bDate = false;
				break;
			}
		}

		if ( bDate )
			str = new String[] { date.substring( 0, 4 ), date.substring( 4, 6 ), date.substring( 6, 8 ) };
		else
			str = getCurrentDates();

		return str;
	}

	public static String getOptionsDay()
	{
		return getOptionsDay( getCurrentDates()[2] );
	}

	public static String getOptionsDay( String def )
	{
		return getOptionsDay( def, getCurrentDates()[1] );
	}

	public static String getOptionsDay( String def, String month )
	{
		int nCurYear = 0;
		try
		{
			nCurYear = Integer.parseInt( getCurrentDates()[0] );
		}
		catch ( Exception e )
		{};
		return getOptionsDay( def, nCurYear, month );
	}

	public static String getOptionsDay( String def, String curYear, String month )
	{
		int nCurYear = 0;
		try
		{
			nCurYear = Integer.parseInt( curYear );
		}
		catch ( Exception e )
		{};
		return getOptionsDay( def, nCurYear, month );
	}

	public static String getOptionsDay( String def, int nCurYear, String month )
	{
		int nDays = 31;
		if ( month.equals( "02" ) )
		{
			nDays = 28;
			if ( nCurYear % 4 == 0 )
			{
				if ( nCurYear % 100 == 0 )
					if ( nCurYear % 400 == 0 )
						nDays = 29;
					else
						nDays = 29;
			}
		}
		else if ( "04,06,08,10".indexOf( month ) > -1 )
		{
			nDays = 30;
		}

		return getOptionsHtml( 1, nDays, ( def == null || def.equals( "" ) ? getCurrentDates()[2] : def ) );
	}

	public static String getOptionsMonth()
	{
		return getOptionsMonth( getCurrentDates()[1] );
	}

	public static String getOptionsMonth( String def )
	{
		return getOptionsHtml( 1, 12, ( def == null || def.equals( "" ) ? getCurrentDates()[1] : def ) );
	}

	public static String getOptionsYear( int nBeginYear )
	{
		int nCurYear = 0;
		try
		{
			nCurYear = Integer.parseInt( getCurrentDates()[0] );
		}
		catch ( Exception e )
		{};
		return getOptionsHtml( nBeginYear, nCurYear, nCurYear );
	}

	public static String getOptionsYear( int nBeginYear, String def )
	{
		int nCurYear = 0;
		try
		{
			nCurYear = Integer.parseInt( getCurrentDates()[0] );
		}
		catch ( Exception e )
		{};
		return getOptionsHtml( nBeginYear, nCurYear, ( def == null || def.equals( "" ) ? getCurrentDates()[0] : def ) );
	}

	public static String getOptionsYear( int nBegin, int nEnd )
	{
		return getOptionsHtml( nBegin, nEnd, getCurrentDates()[0] );
	}

	public static String getOptionsYear( int nBeginYear, int nEnd, String def )
	{
		return getOptionsHtml( nBeginYear, nEnd, ( def == null || def.equals( "" ) ? getCurrentDates()[0] : def ) );
	}

	public static String getOptionsHtml( int nBegin, int nEnd, String def )
	{
		int nDef = 0;
		try
		{
			nDef = Integer.parseInt( def );
		}
		catch ( Exception e )
		{}
		return getOptionsHtml( nBegin, nEnd, nDef );
	}

	public static String getOptionsHtml( int nBegin, int nEnd, int nDef )
	{
		StringBuffer sb = new StringBuffer( "" );
		String tmp = "";
		int nInc = nBegin > nEnd ? -1 : 1;
		for ( int nCnt = nBegin; nCnt * nInc <= nEnd * nInc; nCnt += nInc )
		{
			tmp = nCnt < 10 ? "0" : "";
			sb.append( "<option value=" ).append( tmp ).append( nCnt ).append( nCnt == nDef ? " selected>" : ">" )
					.append( tmp ).append( nCnt ).append( "</option>\n" );
		}
		return sb.toString();
	}
}
