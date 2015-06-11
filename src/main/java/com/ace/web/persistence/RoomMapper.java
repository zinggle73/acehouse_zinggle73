package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Room;

public interface RoomMapper
{
	public List<Room> getAllList( );
	
	public List<Room> getList( Room paramVo );
	
	public String getIsIng( Room paramVo );
	
	public Room getItem( HashMap<String, Object> map );
	
	public Room getCheckItem( HashMap<String, Object> map );

	public int insert( Room paramVo );

	public int update( Room paramVo );

	public int purge( Room paramVo );


}
