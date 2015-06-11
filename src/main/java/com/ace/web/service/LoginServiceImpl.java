package com.ace.web.service;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.UserMapper;
import com.ace.web.vo.User;
import com.service.common.exception.CommonException;

@Service ( "LoginService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class LoginServiceImpl implements LoginService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private UserMapper UserMapper;

	
	@Override
	public User getCheckItem( HashMap<String, Object> map )
	{
		User member = UserMapper.getCheckItem( map );

		return member;
	}
	
	@Override
	public void createUser( User paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		User getData = UserMapper.getItem( paramMap );

		if ( getData == null )
		{
			UserMapper.insert( paramVo );
		}
		else
		{
			UserMapper.update( paramVo );
		}
	}
	
	@Override
	public String getUuidUser()
	{
		UUID token = UUID.randomUUID();
		//String token = CommandToken.generateToken();
		return token.toString();
		
		
	}
}
