package com.service.common.util;

/**
 * processing of special characters in json string
 * 
 * 
 */
public class Base64Util
{

	/**
	 * encode string by Base64
	 * 
	 * @param data
	 *        data
	 * @return string that was encoded by Base64
	 */
	public static String encode( byte[] data )
	{
		return org.apache.commons.codec.binary.Base64.encodeBase64String( data );
	}

	/**
	 * decode string by Base64
	 * 
	 * @param base64
	 *        encoded string
	 * @return string that was decoded by Base64
	 */
	public static String decode( String base64 )
	{
		return new String( org.apache.commons.codec.binary.Base64.decodeBase64( base64 ) );
	}

}
