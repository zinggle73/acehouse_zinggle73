package com.ace.web.persistence;

import java.util.HashMap;
import java.util.List;

import com.ace.web.vo.UserHistory;

public interface UserHistoryMapper
{
	
	public List<UserHistory> getList( UserHistory paramVo );
	
	public UserHistory getItem( HashMap<String, Object> map );
	
	public int insert( UserHistory paramVo );

	public int update( UserHistory paramVo );

}
