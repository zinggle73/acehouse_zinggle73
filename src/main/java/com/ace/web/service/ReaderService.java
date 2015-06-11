package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Reader;
import com.service.common.exception.CommonException;

public interface ReaderService
{
	
	public List<Reader> getList( Reader paramVo );
	
	public Reader getItem( HashMap<String, Object> map );
	
	public Reader getCheckItem( Reader paramVo );
	
	public void createReader( Reader paramVo ) throws CommonException;
	
	public void updateReader( Reader paramVo ) throws CommonException;
	
	public void deleteReader( Reader paramVo );

	public List<Reader> readerList( List<Reader> readerlist );
	
	public Reader readerOne( Reader s );
}
