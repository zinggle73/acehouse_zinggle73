package com.ace.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.ReaderMapper;
import com.ace.web.vo.Reader;
import com.service.common.Constants;
import com.service.common.exception.CommonException;

@Service ( "ReaderService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class ReaderServiceImpl implements ReaderService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private ReaderMapper ReaderMapper;

	@Override
	public List<Reader> getList( Reader paramVo )
	{
		List<Reader> list = ReaderMapper.getList( paramVo );

		return list;
	}
	
	@Override
	public Reader getItem( HashMap<String, Object> map )
	{
		Reader readerVo = ReaderMapper.getItem( map );
	
		return readerVo;
	}
	
	@Override
	public Reader getCheckItem( Reader paramVo )
	{
		Reader readerVo = ReaderMapper.getCheckItem( paramVo );
	
		return readerVo;
	}
	
	@Override
	public void createReader( Reader paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Reader getData = ReaderMapper.getItem( paramMap );

		if ( getData == null )
		{
			ReaderMapper.insert( paramVo );
		}
		else
		{
			ReaderMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateReader( Reader paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		ReaderMapper.update( paramVo );
	}
	
	@Override
	public void deleteReader( Reader paramVo )
	{
		ReaderMapper.purge( paramVo );
	}
	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Reader> readerList( List<Reader> readerlist )
	{
		Reader reader = new Reader();
		List<Reader> readerArray = new ArrayList<Reader>();
		for ( Reader s : readerlist )
		{
			reader = readerOne( s );
			readerArray.add( reader );
		}
		return readerArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Reader readerOne( Reader s )
	{
		Reader reader = new Reader();
		if( s == null )
		{
			return reader;
		}

		reader.setId( s.getId() );					// 일련번호
		reader.setHusId( s.getHusId() );				// house id(system)
		reader.setRoomNum( s.getRoomNum() );			// room id(system)
		reader.setYear( s.getYear() );
		reader.setMonth( s.getMonth() );
		reader.setSort(  s.getSort() );
		reader.setSortKo(  Constants.stringCode( "READSORT", s.getSort() ) );
		reader.setValue( s.getValue() );
		reader.setUserId( s.getUserId() );
		
		return reader;
	}

}
