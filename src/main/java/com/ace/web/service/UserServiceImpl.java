package com.ace.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.CleanMapper;
import com.ace.web.persistence.CompanyMapper;
import com.ace.web.persistence.UserMapper;
import com.ace.web.vo.User;
import com.ace.web.vo.UserHistory;
import com.service.common.Constants;
import com.service.common.exception.CommonException;
import com.service.common.util.CommandToken;
import com.service.common.util.DateTime;

@Service ( "UserService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class UserServiceImpl implements UserService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private UserMapper UserMapper;

	@Autowired
	private CompanyMapper CompanyMapper;
	
	@Autowired
	private UserHistoryService UserHistoryService;
	
	@Autowired
	private CleanMapper CleanMapper;
	
	public List<User> getAllList( )
	{
		return UserMapper.getAllList( );		
	}
	
	@Override
	public List<User> getList( User paramVo )
	{
		return UserMapper.getList( paramVo );
	}

	@Override
	public User getItem( HashMap<String, Object> map )
	{
		User userVo = UserMapper.getItem( map );
		UserHistory history = new UserHistory();
		history.setUserId( userVo.getUserId() );

		userVo.setHistorys( UserHistoryService.getList( history ) );

		return userVo;
	}
	
	@Override
	public User getCheckItem( HashMap<String, Object> map )
	{
		User userVo = UserMapper.getCheckItem( map );
		UserHistory history = new UserHistory();
		history.setUserId( userVo.getUserId() );

		userVo.setHistorys( UserHistoryService.getList( history ) );

		return userVo;
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
	public void updateUser( User paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		UserMapper.update( paramVo );
	}
	
	@Override
	public void deleteUser( User paramVo )
	{
		UserMapper.purge( paramVo );
	}
	
	
	@Override
	public String getUuidUser()
	{
		//UUID randomUUID = UUID.randomUUID();
		String token = CommandToken.generateToken();
		return token;
		
		
	}

	/* convert snsMsgList to bizMsgList */
	@Override
	public List<User> userList( List<User> userlist )
	{
		User user = new User();
		List<User> userArray = new ArrayList<User>();
		for ( User s : userlist )
		{
			user = userOne( s );
			userArray.add( user );
		}
		return userArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public User userOne( User s )
	{
		User user = new User();
		if( s == null )
		{
			return user;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "comId", s.getComId() );
		
		user.setId( s.getId() );					// 일련번호
		user.setComId( s.getComId() );				// house id(system)
		user.setComName( CompanyMapper.getCheckItem( map ).getComName() );				// house id(system)
		user.setUserId( s.getUserId() );			// room id(system)
		user.setUserName( s.getUserName() );			// 주거형태(1룸,1.5룸,2룸, 3룸, 아파트(면적), ..)
		user.setToken( s.getToken() ); 			// 청소금액(system)
		user.setPasswd( s.getPasswd() );
		user.setEmail( s.getEmail() );
		user.setPhone( s.getPhone() );	// 청소담당자(청소업체 선택)
		user.setTwitt( s.getTwitt() );		// 입금확인(청소업체 선택)
		user.setWorkField( s.getWorkField() ); 			// 공실현황
		user.setWorkMsg( Constants.stringCode( "WORKFIELD", s.getWorkField() ) ); 			// 공실현황
		user.setUserAuth( s.getUserAuth() ); 			// 공실현황
		user.setAuthMsg( Constants.stringCode( "USERAUTH", s.getUserAuth() ) ); 			// 공실현황
		user.setRegistDate( DateTime.printDate( s.getRegistDate() ) );
		user.setConnCount( s.getConnCount() );
		user.setLastLoginDate( s.getLastLoginDate() );
		user.setNowLoginDate( s.getNowLoginDate() );
		user.setLogoutDate( s.getLogoutDate());		
		user.setHistorys( s.getHistorys() );
		
		return user;
	}
	
	/* convert snsMsgVo to bizMsgVo */
	@Override
	public User sessionOne( User s )
	{
		User user = new User();
		if( s == null )
		{
			return user;
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "comId", s.getComId() );
		
		user.setId( s.getId() );					// 일련번호
		user.setComId( s.getComId() );				// house id(system)
		user.setComName( CompanyMapper.getCheckItem( map ).getComName() );				// house id(system)
		user.setUserId( s.getUserId() );			// room id(system)
		user.setUserName( s.getUserName() );			// 주거형태(1룸,1.5룸,2룸, 3룸, 아파트(면적), ..)
		user.setPasswd( s.getPasswd() );
		user.setEmail( s.getEmail() );
		user.setToken( s.getToken() ); 			// 청소금액(system)
		user.setPhone( s.getPhone() );	// 청소담당자(청소업체 선택)
		user.setTwitt( s.getTwitt() );		// 입금확인(청소업체 선택)
		user.setWorkField( s.getWorkField() ); 			// 공실현황
		user.setUserAuth( s.getUserAuth() ); 			// 공실현황
		user.setConnCount( s.getConnCount() );
		user.setLastLoginDate( s.getLastLoginDate() );
		user.setNowLoginDate( s.getNowLoginDate() );
		user.setLogoutDate( s.getLogoutDate());		
		
		return user;
	}
	
	
}
