package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.User;

public interface UserMapper
{
	public List<User> getAllList( );
	
	public List<User> getList( User paramVo );
	
	public User getItem( HashMap<String, Object> map );
	
	public User getCheckItem( HashMap<String, Object> map );
	
	public int insert( User paramVo );

	public int update( User paramVo );

	public int purge( User paramVo );


}
