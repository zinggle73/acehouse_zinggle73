package com.service.common.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringUtil
{
	public static long parseLong( String s )
	{
		if ( s == null )
			s = "0";
		if ( equals( s, "null" ) )
			s = "";
		if ( equals( s, "" ) )
			s = "0";
		if ( equals( s, "undefined" ) )
			s = "0";

		long ls = Long.parseLong( s );
		return ls;
	}

	public static boolean equals( String s, String s1 )
	{
		if ( s == null && s1 == null )
			return true;
		if ( s == null || s1 == null )
			return false;
		else
			return s.equals( s1 );
	}

	public static boolean equalsIgnoreCase( String s, String s1 )
	{
		if ( s == null && s1 == null )
			return true;
		if ( s == null || s1 == null )
			return false;
		else
			return s.equalsIgnoreCase( s1 );
	}

	public static boolean equals( String s, Object s1 )
	{
		if ( s == null && s1 == null )
			return true;
		if ( s == null || s1 == null )
			return false;
		else
			return s.equalsIgnoreCase( toString( s1 ) );
	}

	public static boolean equalsIgnoreCase( String s, Object s1 )
	{
		if ( s == null && toString( s1 ) == null )
			return true;
		if ( s == null || s1 == null )
			return false;
		else
			return s.equalsIgnoreCase( toString( s1 ) );
	}

	public static boolean equals( int i, int j )
	{
		return i == j;
	}

	public static String trim( String s )
	{
		if ( !isDefined( s ) )
			return s;
		return s.trim();
	}
	
	public static String nullCheck( String s )
	{
		if ( s == null )
			return "[미정]";
		
		return s.trim();
	}


	@SuppressWarnings ( "rawtypes" )
	public static String toString( Collection c )
	{
		if ( c == null )
			return "";
		StringBuffer sb = new StringBuffer();
		for ( Iterator i = c.iterator(); i.hasNext(); )
		{
			sb.append( i.next() );
			if ( i.hasNext() )
				sb.append( ", " );
		}
		return sb.toString();
	}

	public static String toString( Object object )
	{
		if ( object != null )
			return object.toString();
		return "";
	}

	public static String toString( int c )
	{
		return String.valueOf( c );
	}

	public static String toString( int[] c )
	{
		if ( c == null )
			return "";
		StringBuffer sb = new StringBuffer();
		for ( int i = 0; i < c.length; i++ )
		{
			sb.append( c[i] );
			if ( i < c.length - 1 )
				sb.append( ", " );
		}
		return sb.toString();
	}

	public static String toString( double d )
	{
		return Double.toString( d );
	}

	public static String toString( String as[] )
	{
		if ( as == null )
			return "";
		StringBuffer stringbuffer = new StringBuffer();
		for ( int i = 0; i < as.length; i++ )
		{
			stringbuffer.append( as[i] );
			if ( i < as.length - 1 )
				stringbuffer.append( "," );
		}

		return stringbuffer.toString();
	}

	@SuppressWarnings ( "rawtypes" )
	public static String toString( List list, List list1 )
	{
		if ( list == null || list1 == null )
			return "";
		int i = list.size();
		int j = list1.size();
		if ( i != j )
			return "";
		StringBuffer stringbuffer = new StringBuffer();
		for ( int k = 0; k < i; k++ )
		{
			stringbuffer.append( list.get( k ) ).append( "=" ).append( list1.get( k ) );
			if ( k < i - 1 )
				stringbuffer.append( ", " );
		}

		return stringbuffer.toString();
	}

	public static boolean isDefined( String s )
	{
		return !"null".equals( s ) && s != null && s.length() != 0;
	}

	public static boolean isDefined( String as[] )
	{
		return as != null && as.length != 0;
	}

	public static int parseInt( String s )
	{
		if ( s == null )
			s = "0";
		if ( equals( s, "null" ) )
			s = "";
		if ( equals( s, "" ) )
			s = "0";
		if ( equals( s, "undefined" ) )
			s = "0";

		int ls = Integer.parseInt( s );
		return ls;
	}
	
	public static String strSubString( String str, int limit )
	{
		
		String returnStr = "";
		if( str == null )
		{
			return "";
		}
		
		if( str.length() >= limit )
		{
			returnStr = str.substring(0, (limit+1))+"...";
		}
		else
		{
			returnStr = str;
		}
		
		return returnStr;
	}
	
	
	public static String strCompanyComId( String maxId )
	{
		String comId = "";
		
		if( maxId == null )
		{
			comId =  "C-101";
		}
		else
		{
			String[] max = maxId.split("-");
			int com = parseInt(max[1]);
			
			com = com + 1;
						
			comId = max[0]+"-"+com;
		}
		
		return comId;
	}
	
	public static String strHousehusId( String maxId )
	{
		String comId = "";
		
		if( maxId == null )
		{
			comId =  "H-100001";
		}
		else
		{
			String[] max = maxId.split("-");
			int com = parseInt(max[1]);
			
			com = com + 1;
						
			comId = max[0]+"-"+com;
		}
		
		return comId;
	}
}
