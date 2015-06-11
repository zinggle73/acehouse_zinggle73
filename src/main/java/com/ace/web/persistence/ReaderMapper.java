package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Reader;

public interface ReaderMapper
{
	public List<Reader> getAllList( );
	
	public List<Reader> getList( Reader paramVo );
	
	public Reader getItem( HashMap<String, Object> map );
	
	public Reader getCheckItem( Reader paramVo );

	public int insert( Reader paramVo );

	public int update( Reader paramVo );

	public int purge( Reader paramVo );


}
