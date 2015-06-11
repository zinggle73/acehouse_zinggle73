package com.service.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session utility collection
 * 
 * 
 */
public class SessionUtil
{

	/**
	 * binds an object to a session
	 * 
	 * @param request
	 *        HttpServletRequest
	 * @param attrId
	 *        the name to which the object is bound
	 * @param attr
	 *        the object to be bound
	 */
	public static void setAttribute( HttpServletRequest request, String attrId, Object attr )
	{
		HttpSession session = request.getSession();
		if ( session == null )
			return;

		session.setAttribute( attrId, attr );
	}

	/**
	 * removes the object bound with the specified name from a session
	 * 
	 * @param request
	 *        HttpServletRequest
	 * @param attrId
	 *        the name of the object to remove from a session
	 */
	public static void removeAttribute( HttpServletRequest request, String attrId )
	{
		HttpSession session = request.getSession();
		if ( session == null )
			return;

		session.removeAttribute( attrId );
	}

	/**
	 * returns the login user value object in a session
	 * 
	 * @param request
	 *        HttpServletRequest
	 * @return user value object
	 */
	public static Object getLoginUser( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		if ( session == null )
			return null;

		Object loginUser = (Object)session.getAttribute( "login_user" );

		return loginUser;
	}

	/**
	 * returns the login user id in a session
	 * 
	 * @param request
	 *        HttpServletRequest
	 * @return user id
	 */
	public static Object getLoginUserId( HttpServletRequest request )
	{
		Object loginUser = getLoginUser( request );
		if ( loginUser == null )
			return null;

		return loginUser;
	}

}
