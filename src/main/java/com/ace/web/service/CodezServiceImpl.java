package com.ace.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.CodezMapper;
import com.ace.web.vo.Codez;
import com.service.common.exception.CommonException;

@Service ( "CodezService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class CodezServiceImpl implements CodezService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private CodezMapper CodezMapper;

	
	public List<Codez> getAllList( )
	{
		return CodezMapper.getAllList();		
	}
	
	@Override
	public List<Codez> getList( Codez paramVo )
	{
		return CodezMapper.getList( paramVo );
	}
	
	@Override
	public List<Codez> getDetailCodes( HashMap<String, Object> map )
	{
		return CodezMapper.getDetailCodes( map );
	}
	
	@Override
	public List<Codez> getGroupCodezs( )
	{
		List<Codez> groups = CodezMapper.getGroupCodezs( );

		for( Codez group : groups )
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "groupCode", group.getDetailCode() );
			
			group.setCodezs( CodezMapper.getDetailCodes( paramMap ) );
		}
		
		return groups;
	}
	
	@Override
	public Codez getItem( HashMap<String, Object> map )
	{
		Codez codezVo = CodezMapper.getItem( map );
	
		return codezVo;
	}
	
	@Override
	public void createCodez( Codez paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Codez getData = CodezMapper.getItem( paramMap );

		if ( getData == null )
		{
			CodezMapper.insert( paramVo );
		}
		else
		{
			CodezMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateCodez( Codez paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		CodezMapper.update( paramVo );
	}
	
	@Override
	public void deleteCodez( Codez paramVo )
	{
		CodezMapper.purge( paramVo );
	}

}
