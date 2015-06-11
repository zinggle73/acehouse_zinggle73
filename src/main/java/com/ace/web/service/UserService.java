package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.User;
import com.service.common.exception.CommonException;

public interface UserService
{
	public List<User> getAllList( );
	
	public List<User> getList( User paramVo );
	
	public User getItem( HashMap<String, Object> map );
	
	public User getCheckItem( HashMap<String, Object> map );
	
	public void createUser( User paramVo ) throws CommonException;
	
	public void updateUser( User paramVo ) throws CommonException;
	
	public void deleteUser( User paramVo );
	
	public String getUuidUser();
	
	public List<User> userList( List<User> userlist );
	
	public User userOne( User s );
	
	public User sessionOne( User s );

}
