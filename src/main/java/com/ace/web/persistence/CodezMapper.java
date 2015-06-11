package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Codez;

public interface CodezMapper
{
	public List<Codez> getAllList( );
	
	public List<Codez> getList( Codez paramVo );
	
	public List<Codez> getDetailCodes( HashMap<String, Object> map );
	
	public List<Codez> getGroupCodezs();
	
	public Codez getItem( HashMap<String, Object> map );
	
	public int insert( Codez paramVo );

	public int update( Codez paramVo );

	public int purge( Codez paramVo );


}
