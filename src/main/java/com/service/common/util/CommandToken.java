package com.service.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandToken
{
	public static String set(HttpServletRequest req)
	{
		HttpSession session = req.getSession(true);
		long systime = System.currentTimeMillis();
		byte[] time = new Long(systime).toString().getBytes();
		byte[] id = session.getId().getBytes();
		
		String token = "";

		try 
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(id);
			md5.update(time);

			token = toHex(md5.digest());
			//req.setAttribute("TOKEN",token);
			session.setAttribute("TOKEN",token);
		} 
		catch( Exception e)
		{
			System.err.println("Unable to calculate MD5 Diguests");
		}
		return token;
	}

	public static boolean isValid(HttpServletRequest req)
	{
		HttpSession session = req.getSession(true);
		String requestToken = req.getParameter("TOKEN");
		String sessionToken = (String)session.getAttribute("TOKEN");

		if(requestToken == null || sessionToken == null)
			return false;
		else 
			return requestToken.equals(sessionToken);
	}

	private static String toHex(byte[] digest)
	{
		StringBuffer buf = new StringBuffer();

		for(int i=0;i< digest.length;i++)
			buf.append(Integer.toHexString((int)digest[i] & 0x00ff));
		
		return buf.toString();
	}
	
	public static  String generateToken() 
	{
	    String token = null;
	    SecureRandom secureRandom;
	    
	    try
	    {
	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        secureRandom.setSeed(secureRandom.generateSeed(128));
	        token= new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
	    } 
	    catch (NoSuchAlgorithmException e) 
	    {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    return token;
	}
}

