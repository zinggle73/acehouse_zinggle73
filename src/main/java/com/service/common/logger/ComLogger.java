package com.service.common.logger;

import org.slf4j.Logger;
import org.slf4j.ext.LoggerWrapper;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

public class ComLogger extends LoggerWrapper implements Logger
{

	private static final String BAD_PARAMETER = "Bad Parameter";

	private static final String FQCN = ComLogger.class.getName();

	public ComLogger( Logger logger )
	{
		super( logger, LoggerWrapper.class.getName() );
	}

	private String makeLog( int size, Object... vars )
	{
		StringBuffer buf = new StringBuffer();

		if ( size < 0 )
		{
			size = 0;
		}

		// default
		int len = 0;
		if ( vars != null )
		{
			len = vars.length;
		}

		int minSize = size < len ? size : len;
		for ( int i = 0; i < minSize; i++ )
		{
			buf.append( toString( vars[i] ) );
			buf.append( " | " );
		}

		// extension
		if ( vars == null )
		{
			return BAD_PARAMETER;
		}

		if ( size < vars.length )
		{
			buf.append( "\"" );
			boolean isAddEquals = true;
			for ( int i = size; i < vars.length - 1; i++ )
			{
				buf.append( toString( vars[i] ) );

				if ( isAddEquals )
				{
					buf.append( "=" );
				}
				else
				{
					buf.append( "," );
				}

				isAddEquals = !isAddEquals;
			}
			buf.append( toString( vars[vars.length - 1] ) );
			if ( ( ( vars.length - size ) & 0x1 ) == 1 )
			{
				buf.append( "=" );
			}
			buf.append( "\"" );
		}
		else if ( size > vars.length )
		{
			buf.append( BAD_PARAMETER );
		}

		return buf.toString();
	}

	private String toString( Object obj )
	{
		if ( obj == null )
		{
			return "null";
		}

		return obj.toString();
	}

	public void trace( int size, Object... vars )
	{
		if ( !logger.isTraceEnabled() )
			return;

		String formattedMessage = makeLog( size, vars );
		if ( instanceofLAL )
		{
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.trace( formattedMessage );
		}
	}

	public void debug( int size, Object... vars )
	{
		if ( !logger.isDebugEnabled() )
			return;

		String formattedMessage = makeLog( size, vars );
		if ( instanceofLAL )
		{
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.debug( formattedMessage );
		}
	}

	public void info( int size, Object... vars )
	{
		if ( !logger.isInfoEnabled() )
			return;

		String formattedMessage = makeLog( size, vars );
		if ( instanceofLAL )
		{
			( (LocationAwareLogger)logger )
					.log( null, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, vars, null );
		}
		else
		{
			logger.info( formattedMessage );
		}
	}

	public void warn( int size, Object... vars )
	{
		if ( !logger.isWarnEnabled() )
			return;

		String formattedMessage = makeLog( size, vars );
		if ( instanceofLAL )
		{
			( (LocationAwareLogger)logger )
					.log( null, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, vars, null );
		}
		else
		{
			logger.warn( formattedMessage );
		}
	}

	public void error( int size, Object... vars )
	{
		if ( !logger.isErrorEnabled() )
			return;

		String formattedMessage = makeLog( size, vars );
		if ( instanceofLAL )
		{
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.error( formattedMessage );
		}
	}

	public void trace( String format, Object... vars )
	{
		if ( !logger.isTraceEnabled() )
			return;

		if ( instanceofLAL )
		{
			String formattedMessage = MessageFormatter.arrayFormat( format, vars ).getMessage();
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.TRACE_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.trace( format, vars );
		}
	}

	public void debug( String format, Object... vars )
	{
		if ( !logger.isDebugEnabled() )
			return;

		if ( instanceofLAL )
		{
			String formattedMessage = MessageFormatter.arrayFormat( format, vars ).getMessage();
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.DEBUG_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.debug( format, vars );
		}
	}

	public void info( String format, Object... args )
	{
		if ( !logger.isInfoEnabled() )
			return;

		if ( instanceofLAL )
		{
			String formattedMessage = MessageFormatter.arrayFormat( format, args ).getMessage();
			( (LocationAwareLogger)logger )
					.log( null, FQCN, LocationAwareLogger.INFO_INT, formattedMessage, args, null );
		}
		else
		{
			logger.info( format, args );
		}
	}

	public void warn( String format, Object... vars )
	{
		if ( !logger.isWarnEnabled() )
			return;

		if ( instanceofLAL )
		{
			String formattedMessage = MessageFormatter.arrayFormat( format, vars ).getMessage();
			( (LocationAwareLogger)logger )
					.log( null, FQCN, LocationAwareLogger.WARN_INT, formattedMessage, vars, null );
		}
		else
		{
			logger.warn( format, vars );
		}
	}

	public void error( String format, Object... vars )
	{
		if ( !logger.isErrorEnabled() )
			return;

		if ( instanceofLAL )
		{
			String formattedMessage = MessageFormatter.arrayFormat( format, vars ).getMessage();
			( (LocationAwareLogger)logger ).log( null, FQCN, LocationAwareLogger.ERROR_INT, formattedMessage, vars,
					null );
		}
		else
		{
			logger.error( format, vars );
		}
	}

}
