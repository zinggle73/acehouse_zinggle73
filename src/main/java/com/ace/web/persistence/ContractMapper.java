package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.Contract;

public interface ContractMapper
{
	
	public List<Contract> getList( Contract paramVo );
	
	public Contract getItem( HashMap<String, Object> map );
	
	public Contract getCheckItem( HashMap<String, Object> map );
	
	public Long getPayItem( Contract paramVo );
	
	public List<Contract> getCompays();
	
	public List<Contract> getYears( Contract paramVo );
	
	public List<Contract> getLocals( Contract paramVo );
	
	public List<Contract> getContracts( Contract paramVo );

	public int insert( Contract paramVo );

	public int update( Contract paramVo );

	public int purge( Contract paramVo );


}
