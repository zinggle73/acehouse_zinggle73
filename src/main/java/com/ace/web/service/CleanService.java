package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Clean;
import com.service.common.exception.CommonException;

public interface CleanService
{
	public List<Clean> getAllList( );
	
	public List<Clean> getList( Clean paramVo );
	
	public Clean getItem( HashMap<String, Object> map );
	
	public void createClean( Clean paramVo ) throws CommonException;
	
	public void updateClean( Clean paramVo ) throws CommonException;
	
	public void deleteClean( Clean paramVo );
	
	public List<Clean> cleanList( List<Clean> cleanlist );
	
	public Clean cleanOne( Clean s );

}
