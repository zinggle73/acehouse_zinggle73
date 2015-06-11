package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Contract;
import com.service.common.exception.CommonException;

public interface ContractService
{
	
	public List<Contract> getList( Contract paramVo );
	
	public Contract getItem( HashMap<String, Object> map );
	
	public Contract getCheckItem( HashMap<String, Object> map );
	
	public Long getPayItem( Contract paramVo );
	
	public List<Contract> getCompays();
	
	public void createContract( Contract paramVo ) throws CommonException;
	
	public void updateContract( Contract paramVo ) throws CommonException;
	
	public void deleteContract( Contract paramVo );

	public List<Contract> contractList( List<Contract> roomlist );
	
	public Contract contractOne( Contract s );
}
