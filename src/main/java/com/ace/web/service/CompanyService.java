package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Company;
import com.service.common.exception.CommonException;

public interface CompanyService
{
	public List<Company> getAllList( );
	
	public List<Company> getList( Company paramVo );
	
	public List<Company> getSelectList( Company paramVo );
	
	public List<Company> getEmptyRoomCompanys( Company paramVo );
	
	public Company getItem( HashMap<String, Object> map );
	
	public Company getCheckItem( HashMap<String, Object> map );	
	
	public void createCompany( Company paramVo ) throws CommonException;
	
	public void updateCompany( Company paramVo ) throws CommonException;
	
	public void deleteCompany( Company paramVo );

	public String getMaxComId();
	
	public List<Company> comList( List<Company> comlist );
	
	public Company comOne( Company s );
}
