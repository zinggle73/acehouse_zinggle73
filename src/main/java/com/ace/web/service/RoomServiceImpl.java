package com.ace.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.RoomMapper;
import com.ace.web.vo.Room;
import com.service.common.Constants;
import com.service.common.exception.CommonException;

@Service ( "RoomService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class RoomServiceImpl implements RoomService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private RoomMapper RoomMapper;
	
	public List<Room> getAllList( )
	{
		return RoomMapper.getAllList( );		
	}
	
	@Override
	public List<Room> getList( Room paramVo )
	{
		List<Room> list = RoomMapper.getList( paramVo );
		Room rm = new Room();
		rm.setHusId( paramVo.getHusId() );
		
		for( Room room : list )
		{
			rm.setRoomNum( room.getRoomNum() );
			room.setIsIng( RoomMapper.getIsIng( rm ) );
		}
		
		return list;
	}
	
	@Override
	public Room getItem( HashMap<String, Object> map )
	{
		Room roomVo = RoomMapper.getItem( map );
	
		return roomVo;
	}
	
	@Override
	public Room getCheckItem( HashMap<String, Object> map )
	{
		Room roomVo = RoomMapper.getCheckItem( map );
	
		return roomVo;
	}
	
	@Override
	public void createRoom( Room paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Room getData = RoomMapper.getItem( paramMap );

		if ( getData == null )
		{
			RoomMapper.insert( paramVo );
		}
		else
		{
			RoomMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateRoom( Room paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		RoomMapper.update( paramVo );
	}
	
	@Override
	public void deleteRoom( Room paramVo )
	{
		RoomMapper.purge( paramVo );
	}
	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Room> roomList( List<Room> roomlist )
	{
		Room room = new Room();
		List<Room> roomArray = new ArrayList<Room>();
		for ( Room s : roomlist )
		{
			room = roomOne( s );
			roomArray.add( room );
		}
		return roomArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Room roomOne( Room s )
	{
		Room room = new Room();
		if( s == null )
		{
			return room;
		}

		room.setId( s.getId() );					// 일련번호
		room.setHusId( s.getHusId() );				// house id(system)
		room.setRoomNum( s.getRoomNum() );			// room id(system)
		room.setLiving( s.getLiving() );			// 주거형태(1룸,1.5룸,2룸, 3룸, 아파트(면적), ..)
		room.setLivingMsg( Constants.stringCode( "LIVING", s.getLiving() )  );
		room.setAmount( s.getAmount() ); 			// 청소금액(system)
		room.setBeforePass( s.getBeforePass() );	// 청소담당자(청소업체 선택)
		room.setAfterPass( s.getAfterPass() );		// 입금확인(청소업체 선택)
		room.setIsZero( s.getIsZero() ); 			// 공실현황
		room.setZeroMsg( Constants.stringCode( "ISZERO", s.getIsZero() ) ); 			// 공실현황
		room.setIsIng( s.getIsIng() );
		room.setIsIngMsg( Constants.stringCode( "ISING", s.getIsIng() ) );
		
		return room;
	}

}
