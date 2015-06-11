package com.ace.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ace.web.service.CodezService;
import com.ace.web.vo.Codez;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;

@Controller
@Validated
@RequestMapping ( value = "/admin/codez" )
public class AdminCodezController extends AbstractController
{

	@Autowired
	private CodezService CodezService;


	@RequestMapping ( method = RequestMethod.GET )
	public void listCodezs( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );

		List<Codez> list = new ArrayList<Codez>();

		try
		{
			list = CodezService.getAllList();

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
	
	@RequestMapping ( value="/group", method = RequestMethod.GET )
	public void groupCodezs( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET GROUPCODE CALL" );

		List<Codez> list = new ArrayList<Codez>();
		try
		{
			list = CodezService.getGroupCodezs( );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
	
	@RequestMapping ( value="/detailCodes/{groupCode}", method = RequestMethod.GET )
	public void detailCodes( @PathVariable ( "groupCode" ) String  groupCode, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET CODE GROUPLIST CALL : " + groupCode );

		List<Codez> list = new ArrayList<Codez>();
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "groupCode", groupCode );
			map.put( "codeOr", "Y" );
			
			list = CodezService.getDetailCodes( map );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
	
	
	@RequestMapping ( value = "/{id}", method = RequestMethod.GET )
	public void fetchCodez( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL" );
		Codez object = new Codez();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "id", id );
			object = CodezService.getItem( map );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}


	@RequestMapping ( method = RequestMethod.POST )
	public void saveCodez( @RequestBody Codez object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL" );
				
		try
		{
			CodezService.createCodez( object );
			/*
			if( object.getGroupCode().equals( "00000" ) )
			{
				if( !object.getCodeExplan().equals( "" ) )
				{
					String[] codes = object.getCodeExplan().split( "," );
					Codez obj = null;
					
					for( String code : codes )
					{
						obj = new Codez();
						String[] detail = code.split( "." );
						obj.setGroupCode( object.getDetailCode() );
						obj.setDetailCode( detail[0] );
						obj.setCodeName( detail[1] );
						obj.setCodeOr( "Y" );
						
						CodezService.createCodez( obj );
					}
				}
			}
			*/
		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}


	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT )
	public void updateCodez( @PathVariable ( "id" ) long id, @RequestBody Codez object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		
		try
		{
			object.setId( id );

			CodezService.updateCodez( object );

		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

	/**
	 * put update Rooms table
	 * 
	 * @param request
	 *        body of RoomObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/YN/{codeOr}", method = RequestMethod.PUT )
	public void updateCodeOr(  @PathVariable ( "codeOr" ) String codeOr, @RequestBody Codez object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL : "+codeOr );
		
		try
		{
			String or = "";
			if( codeOr.equals( "Y" ) )
			{
				or = "N";
			}
			else  or = "Y";
			
			object.setCodeOr( or );
			CodezService.updateCodez( object );

		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}
	

	@RequestMapping ( value = "/{id}", method = RequestMethod.DELETE )
	public void destroyCodez( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{

		logger.error( "DELETE CALL" );
		try
		{
			Codez object = new Codez();
			object.setId( id );

			CodezService.deleteCodez( object );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

	
}
