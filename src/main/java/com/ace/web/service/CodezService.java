package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Codez;
import com.service.common.exception.CommonException;

public interface CodezService
{
	public List<Codez> getAllList( );
	
	public List<Codez> getList( Codez paramVo );
	
	public List<Codez> getDetailCodes( HashMap<String, Object> map );
	
	public List<Codez> getGroupCodezs( );
	
	public Codez getItem( HashMap<String, Object> map );
	
	public void createCodez( Codez paramVo ) throws CommonException;
	
	public void updateCodez( Codez paramVo ) throws CommonException;
	
	public void deleteCodez( Codez paramVo );

}
