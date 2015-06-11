package com.ace.web.service;

import java.util.HashMap;

import com.ace.web.vo.User;
import com.service.common.exception.CommonException;

public interface LoginService
{
	
	public User getCheckItem( HashMap<String, Object> map );
	
	public void createUser( User paramVo ) throws CommonException;
	
	public String getUuidUser();
}
