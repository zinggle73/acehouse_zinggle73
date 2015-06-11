package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Company;

public interface CompanyMapper
{
	public List<Company> getAllList( );
	
	public List<Company> getList( Company paramVo );
	
	public Company getItem( HashMap<String, Object> map );
	
	public Company getCheckItem( HashMap<String, Object> map );

	public int insert( Company paramVo );

	public int update( Company paramVo );

	public int purge( Company paramVo );

	public String getComId();
	
}
