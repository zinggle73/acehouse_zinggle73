package com.service.common.logger;

import org.slf4j.LoggerFactory;

public class ComLoggerFactory
{

	public static ComLogger getLogger( String name )
	{
		return new ComLogger( LoggerFactory.getLogger( name ) );
	}

	public static ComLogger getLogger( @SuppressWarnings ( "rawtypes" ) Class clazz )
	{
		return getLogger( clazz.getName() );
	}

}
