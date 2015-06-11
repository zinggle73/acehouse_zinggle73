package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.House;

public interface HouseMapper
{
	public List<House> getAllList( );
	
	public List<House> getList( House paramVo );
	
	public List<House> getHouseList( House paramVo );
	
	public String getZeroCount( House paramVo );
	
	public House getItem( HashMap<String, Object> map );
	
	public House getCheckItem( HashMap<String, Object> map );
	
	public int insert( House paramVo );

	public int update( House paramVo );

	public int purge( House paramVo );

	public String getHouseId();
}
