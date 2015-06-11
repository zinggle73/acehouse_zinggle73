package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.UserHistoryMapper;
import com.ace.web.vo.UserHistory;
import com.service.common.exception.CommonException;

@Service ( "UserHistoryService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class UserHistoryServiceImpl implements UserHistoryService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserHistoryServiceImpl.class );

	@Autowired
	private UserHistoryMapper UserHistoryMapper;

	@Override
	public List<UserHistory> getList( UserHistory paramVo )
	{
		return UserHistoryMapper.getList( paramVo );
	}

	@Override
	public UserHistory getItem( HashMap<String, Object> map )
	{
		return UserHistoryMapper.getItem( map );
	}
	
	
	@Override
	public void createUser( UserHistory paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		UserHistory getData = UserHistoryMapper.getItem( paramMap );

		if ( getData == null )
		{
			UserHistoryMapper.insert( paramVo );
		}
		else
		{
			UserHistoryMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateUser( UserHistory paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		UserHistoryMapper.update( paramVo );
	}
		
}
