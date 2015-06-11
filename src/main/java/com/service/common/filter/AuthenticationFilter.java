package com.service.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.web.vo.User;
import com.service.common.logger.ComLogger;
import com.service.common.logger.ComLoggerFactory;
import com.service.common.util.SessionUtil;


/**
 * @Project siusys-sns-agent
 * @author DragonDo
 * @Date 2014. 10. 25.
 * @Version
 * @Description  
 */
@WebFilter ( "/AuthenticationFilter" )
public class AuthenticationFilter implements Filter
{
	private static final ComLogger logger = ComLoggerFactory.getLogger( AuthenticationFilter.class );
	private ServletContext context;

	public void init( FilterConfig fConfig ) throws ServletException
	{
		this.context = fConfig.getServletContext();
		logger.error( "AuthenticationFilter initialized" );
	}


	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
	{
		boolean authorized = false;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		User sessionUser = (User)SessionUtil.getLoginUser( req );
		StringBuffer requestURL = ( (HttpServletRequest)request ).getRequestURL();
		String queryString = ( (HttpServletRequest)request ).getQueryString();
		logger.error( "AuthenticationFilter initialized" + requestURL.toString() +"/"+queryString +"   /  "+sessionUser);
		
		if ( sessionUser != null )
		{
			String token = sessionUser.getToken();

			if ( token != null )
			{
				authorized = true;
			}
		}

		if ( authorized )
		{
			chain.doFilter( request, response );
		}
		else
		{
			res.sendRedirect( requestURL.toString()+"/"+queryString );
		}

	}

	public void destroy()
	{
		// close any resources here
		logger.error( "AuthenticationFilter destroy" );
	}

}
