package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Room;
import com.service.common.exception.CommonException;

public interface RoomService
{
	public List<Room> getAllList( );
	
	public List<Room> getList( Room paramVo );
	
	public Room getItem( HashMap<String, Object> map );
	
	public Room getCheckItem( HashMap<String, Object> map );
	
	public void createRoom( Room paramVo ) throws CommonException;
	
	public void updateRoom( Room paramVo ) throws CommonException;
	
	public void deleteRoom( Room paramVo );

	public List<Room> roomList( List<Room> roomlist );
	
	public Room roomOne( Room s );
}
