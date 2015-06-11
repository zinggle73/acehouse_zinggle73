package com.ace.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.persistence.CompanyMapper;
import com.ace.web.persistence.HouseMapper;
import com.ace.web.persistence.RoomMapper;
import com.ace.web.vo.Clean;
import com.ace.web.vo.House;
import com.ace.web.vo.Room;
import com.service.common.Constants;
import com.service.common.exception.CommonException;

@Service ( "HouseService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class HouseServiceImpl implements HouseService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private HouseMapper HouseMapper;
	
	@Autowired
	private CompanyMapper CompanyMapper;

	@Autowired
	private RoomService RoomService;
	
	@Autowired
	private RoomMapper RoomMapper;
	
	@Autowired
	private CleanService CleanService;
	
	public List<House> getAllList( )
	{
		return HouseMapper.getAllList( );		
	}
	
	@Override
	public List<House> getList( House paramVo )
	{
		List<House> list = HouseMapper.getList( paramVo );
		//House hus = new House();
		//hus.setComId( paramVo.getComId() );
		
		for( House house : list )
		{			
			//hus.setHusId( house.getHusId() );
			//house.setZeroNum( HouseMapper.getZeroCount( hus ) );		

			Room roomObj = new Room();
			roomObj.setHusId( house.getHusId() );
			List<Room> rooms = RoomService.roomList( RoomService.getList( roomObj ) );
			house.setRooms( rooms );
			
			Clean cleanObj = new Clean();
			cleanObj.setHusId( house.getHusId() );
			List<Clean> cleans = CleanService.cleanList( CleanService.getList( cleanObj ) );
			house.setCleans( cleans );

		}
		
		return list;
	}
	
	
	@Override
	public List<House> getEmptyHouses( House paramVo )
	{
		List<House> list = HouseMapper.getList( paramVo );
		House hus = new House();
		hus.setComId( paramVo.getComId() );
		
		for( House house : list )
		{			
			hus.setHusId( house.getHusId() );					
			house.setZeroNum( HouseMapper.getZeroCount( hus ) );
			
			Room room = new Room();			
			room.setHusId( house.getHusId() );
			room.setIsZero( "N" );
			house.setRooms( RoomService.roomList( RoomService.getList( room ) ) );		
		}
		
		return list;
	}
	
	
	@Override
	public House getItem( HashMap<String, Object> map )
	{
		House house = HouseMapper.getItem( map );
		
		Room room = new Room();			
		room.setHusId( house.getHusId() );
		house.setRooms( RoomService.roomList( RoomService.getList( room ) ) );		
	
		return house;
	}
	
	@Override
	public House getCheckItem( HashMap<String, Object> map )
	{
		House house = HouseMapper.getCheckItem( map );
		
		Room room = new Room();			
		room.setHusId( house.getHusId() );
		house.setRooms( RoomService.roomList( RoomService.getList( room ) ) );	
		
		return house;
	}
	
	@Override
	public void createHouse( House paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		House getData = HouseMapper.getItem( paramMap );

		if ( getData == null )
		{
			HouseMapper.insert( paramVo );
		}
		else
		{
			HouseMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateHouse( House paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		HouseMapper.update( paramVo );
	}
	
	@Override
	public void deleteHouse( House paramVo )
	{
		HouseMapper.purge( paramVo );
	}

	@Override
	public String getMaxHouseId()
	{
		String maxId = HouseMapper.getHouseId( );
		
		return maxId;
	}
	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<House> houseList( List<House> huslist )
	{
		House hus = new House();
		List<House> husArray = new ArrayList<House>();
		for ( House s : huslist )
		{
			hus = houseOne( s );
			husArray.add( hus );
		}
		return husArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public House houseOne( House s )
	{
		House hus = new House();
		if( s == null )
		{
			return hus;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "comId", s.getComId() );
		
		hus.setId( s.getId() );					// 일련번호
		hus.setComId( s.getComId() );			// com id(system)
		hus.setComName(  CompanyMapper.getCheckItem( map ).getComName()  );		// 회사명
		hus.setHusId( s.getHusId() ); 		// 대표자명
		hus.setHusName( s.getHusName() );			// 카카오아이디
		hus.setOwner( s.getOwner() );				// 대표자모바일
		hus.setPhone( s.getPhone() ); 			// 전화번호
		hus.setLocal( s.getLocal() );		// 우편번호
		hus.setLocalKo(  Constants.stringCode("LOCAL", s.getLocal() ) );		// 우편번호
		hus.setAddress( s.getAddress() );		// 주소
		hus.setGatePass( s.getGatePass() );				// 은행명
		hus.setIsIng( s.getIsIng() );
		hus.setZeroNum( s.getZeroNum() );
		hus.setRegistDate( s.getRegistDate() );	// 등록일자
		hus.setRooms( s.getRooms() );
		hus.setCleans( s.getCleans() );
		
		return hus;
	}
}
