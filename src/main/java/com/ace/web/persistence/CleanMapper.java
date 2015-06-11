package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Clean;

public interface CleanMapper
{
	public List<Clean> getAllList( );
	
	public List<Clean> getList( Clean paramVo );
	
	public Clean getItem( HashMap<String, Object> map );

	public int insert( Clean paramVo );

	public int update( Clean paramVo );

	public int purge( Clean paramVo );


}
