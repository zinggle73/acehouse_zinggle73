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

import com.ace.web.service.ReaderService;
import com.ace.web.service.RoomService;
import com.ace.web.vo.Reader;
import com.ace.web.vo.Room;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;

@Controller
@Validated
@RequestMapping ( value = "/biz/reader" )
public class BizReaderController extends AbstractController
{

	@Autowired
	private ReaderService ReaderService;

	
	/**
	 * get list Rooms table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Rooms table
	 * @throws CommonException
	 */
	@RequestMapping ( value="/{husId}", method = RequestMethod.GET )
	public void listReaders( @PathVariable ( "husId" ) String husId, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET READER LIST CALL : " + husId);
		
		Reader object = new Reader();
		List<Reader> list = new ArrayList<Reader>();
		try
		{
			object.setHusId( husId );
			list = ReaderService.readerList( ReaderService.getList( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}

	/**
	 * get item Rooms table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Rooms table
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{husId}/{id}", method = RequestMethod.GET )
	public void fetchReader(  @PathVariable ( "husId" ) String husId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL" );
		Reader object = new Reader();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "husId", husId );
			map.put( "id", id );
			
			object = ReaderService.getItem( map );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}
	
	/**
	 * post save Rooms table
	 * 
	 * @param request
	 *        body of RoomObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value="/{husId}", method = RequestMethod.POST )
	public void saveReader(  @PathVariable ( "husId" ) String husId, @RequestBody Reader object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL husId : "+husId );
		List<Reader> list = new ArrayList<Reader>();
		Reader readerObj = new Reader();
		
		try
		{
			object.setHusId( husId );
			ReaderService.createReader( object );
			
			readerObj.setHusId( husId );
			list = ReaderService.readerList( ReaderService.getList( readerObj ) );
			
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
				
		setResponse( list, request, response );
	}

	/**
	 * put update Rooms table
	 * 
	 * @param request
	 *        body of RoomObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{husId}/{id}", method = RequestMethod.PUT )
	public void updateReader(  @PathVariable ( "husId" ) String husId,  @PathVariable ( "id" ) long id, @RequestBody Reader object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL husId : " +husId+" / id : "+id);
		Reader reader = new Reader();
		List<Reader> list = new ArrayList<Reader>();
		
		try
		{
			object.setId( id );
			object.setHusId( husId );
			
			ReaderService.updateReader( object );
			
			reader.setHusId( object.getHusId() );
			list = ReaderService.readerList( ReaderService.getList( reader ) );


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
		
		setResponse( list, request, response );
	}

	
	/**
	 * delete Rooms table
	 * 
	 * @param pk
	 *        value of Rooms table
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{husId}/{id}", method = RequestMethod.DELETE )
	public void destroyReader(  @PathVariable ( "husId" ) String husId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "DELETE CALL" +husId+" / "+id);
		Reader reader = new Reader();
		List<Reader> list = new ArrayList<Reader>();
		
		try
		{
			Reader object = new Reader();
			object.setId( id );
			object.setHusId( husId );
			
			ReaderService.deleteReader( object );
			
			reader.setHusId( husId );
			list = ReaderService.readerList( ReaderService.getList( reader ) );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( list, request, response );
	}

	
}
