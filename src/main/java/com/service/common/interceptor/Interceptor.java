package com.service.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.service.common.logger.ComLogger;
import com.service.common.logger.ComLoggerFactory;

public class Interceptor extends HandlerInterceptorAdapter
{
	private static final ComLogger logger = ComLoggerFactory.getLogger( Interceptor.class );

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception
	{
		logger.info( "------preHandle------" );

		return true;
	}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception
	{
		logger.debug( "------afterCompletion------" );
	}
}
