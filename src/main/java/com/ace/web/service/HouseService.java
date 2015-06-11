package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.House;
import com.service.common.exception.CommonException;

public interface HouseService
{
	public List<House> getAllList( );
	
	public List<House> getList( House paramVo );
	
	public List<House> getEmptyHouses( House paramVo );
	
	public House getItem( HashMap<String, Object> map );
	
	public House getCheckItem( HashMap<String, Object> map );
	
	public void createHouse( House paramVo ) throws CommonException;
	
	public void updateHouse( House paramVo ) throws CommonException;
	
	public void deleteHouse( House paramVo );
	
	public String getMaxHouseId();
	
	public List<House> houseList( List<House> huslist );
	
	public House houseOne( House s );

}
