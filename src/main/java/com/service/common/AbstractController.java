package com.service.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.logger.ComLogger;
import com.service.common.logger.ComLoggerFactory;

public class AbstractController
{

	protected static final ComLogger logger = ComLoggerFactory.getLogger( AbstractController.class );

	protected ObjectMapper objMapper = new ObjectMapper();

	protected void setResponse( Object result, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		/*
		response.setHeader("Content-Type", "application/json; charset=UTF-8" );
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
		*/
		byte[] body = null;
		try
		{
			body = objMapper.writerWithDefaultPrettyPrinter().writeValueAsBytes( result );
			response.getOutputStream().write( body );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.JSON_SERIALIZE, e );
		}

		request.setAttribute( "payload", body.length );

	}
}
