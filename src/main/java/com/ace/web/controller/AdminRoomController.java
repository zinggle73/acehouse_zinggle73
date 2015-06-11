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

import com.ace.web.service.RoomService;
import com.ace.web.vo.House;
import com.ace.web.vo.Room;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;

@Controller
@Validated
@RequestMapping ( value = "/admin/room" )
public class AdminRoomController extends AbstractController
{

	@Autowired
	private RoomService RoomService;

	
	/**
	 * get list Rooms table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Rooms table
	 * @throws CommonException
	 */
	@RequestMapping ( value="/{husId}", method = RequestMethod.GET )
	public void listRooms( @PathVariable ( "husId" ) String husId, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ROOM LIST CALL : " + husId);
		
		Room object = new Room();
		List<Room> list = new ArrayList<Room>();
		try
		{
			object.setHusId( husId );
			list = RoomService.roomList( RoomService.getList( object ) );

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
	public void fetchRoom(  @PathVariable ( "husId" ) String husId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL" );
		Room object = new Room();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "husId", husId );
			map.put( "id", id );
			
			object = RoomService.getItem( map );
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
	public void saveRoom(  @PathVariable ( "husId" ) String husId, @RequestBody Room object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL husId : "+husId+" / roomNum : "+object.getRoomNum() );
		List<Room> list = new ArrayList<Room>();
		Room roomObj = new Room();
		
		try
		{
			if( object.getRoomNums() != null && !object.getRoomNums().equals("") )
			{
				String[] rooms = object.getRoomNums().split(",");
				
				for( String str : rooms )
				{
					object.setHusId( husId );
					object.setRoomNum( str );		
					RoomService.createRoom( object );
				}
				
			}
			else if( object.getRoomNum() != null && !object.getRoomNum().equals("") )
			{
				object.setHusId( husId );
				RoomService.createRoom( object );

			}
			
			roomObj.setHusId( husId );
			list = RoomService.roomList( RoomService.getList( roomObj ) );
			
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
	public void updateRoom(  @PathVariable ( "husId" ) String husId,  @PathVariable ( "id" ) long id, @RequestBody Room object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL husId : " +husId+" / id : "+id);
		Room room = new Room();
		List<Room> list = new ArrayList<Room>();
		
		try
		{
			object.setId( id );
			object.setHusId( husId );
			
			RoomService.updateRoom( object );
			
			room.setHusId( object.getHusId() );
			list = RoomService.roomList( RoomService.getList( room ) );


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
	@RequestMapping ( value = "/{isZero}", method = RequestMethod.PUT )
	public void updateIsZero(  @PathVariable ( "isZero" ) String isZero, @RequestBody Room object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL isZero : "+isZero );
		Room room = new Room();
		List<Room> list = new ArrayList<Room>();
		
		try
		{
			String is = "";
			if( isZero.equals( "Y" ) )
			{
				is = "N";
			}
			else  is = "Y";
			
			object.setIsZero( is );
			RoomService.updateRoom( object );
			
			room.setHusId( object.getHusId() );
			list = RoomService.roomList( RoomService.getList( room ) );


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
	public void destroyRoom(  @PathVariable ( "husId" ) String husId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "DELETE CALL" +husId+" / "+id);
		Room room = new Room();
		List<Room> list = new ArrayList<Room>();
		
		try
		{
			Room object = new Room();
			object.setId( id );
			object.setHusId( husId );
			
			RoomService.deleteRoom( object );
			
			room.setHusId( husId );
			list = RoomService.roomList( RoomService.getList( room ) );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( list, request, response );
	}

	
}
