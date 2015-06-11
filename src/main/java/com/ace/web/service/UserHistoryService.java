package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.UserHistory;
import com.service.common.exception.CommonException;

public interface UserHistoryService
{

	public List<UserHistory> getList( UserHistory paramVo );
	
	public UserHistory getItem( HashMap<String, Object> map );
	
	public void createUser( UserHistory paramVo ) throws CommonException;
	
	public void updateUser( UserHistory paramVo ) throws CommonException;

}
