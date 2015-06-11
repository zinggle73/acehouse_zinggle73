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
import com.ace.web.persistence.HouseMapper;
import com.ace.web.persistence.RoomMapper;
import com.ace.web.persistence.UserMapper;
import com.ace.web.vo.Clean;
import com.ace.web.vo.Company;
import com.ace.web.vo.House;
import com.ace.web.vo.Reader;
import com.ace.web.vo.Room;
import com.service.common.Constants;
import com.service.common.exception.CommonException;
import com.service.common.util.CalendarUtil;

@Service ( "CompanyService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class CompanyServiceImpl implements CompanyService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private CompanyMapper CompanyMapper;
	
	@Autowired
	private UserService UserService;
	
	@Autowired
	private UserMapper UserMapper;
	
	@Autowired
	private HouseService HouseService;
	
	@Autowired
	private HouseMapper HouseMapper;
	
	@Autowired
	private CleanService CleanService;
	
	@Autowired
	private CleanMapper CleanMapper;
	
	@Autowired
	private RoomService RoomService;
	
	@Autowired
	private RoomMapper RoomMapper;
	
	@Autowired
	private ReaderService ReaderService;
	
	public List<Company> getAllList( )
	{
		return CompanyMapper.getAllList( );		
	}
	
	@Override
	public List<Company> getList( Company paramVo )
	{
		return CompanyMapper.getList( paramVo );
	}
	
	@Override
	public List<Company> getSelectList( Company paramVo )
	{
		List<Company> companys =  CompanyMapper.getList( paramVo );
		
		for( Company com : companys )
		{
			House houseObj = new House();
			houseObj.setComId( com.getComId() );
			List<House> houses = HouseService.houseList( HouseService.getList( houseObj ) );
			com.setHouses( houses );
			
			for( House house : houses )
			{
				Room roomObj = new Room();
				roomObj.setHusId( house.getHusId() );
				List<Room> rooms = RoomService.roomList( RoomService.getList( roomObj ) );
				house.setRooms( rooms );
				
				Clean cleanObj = new Clean();
				cleanObj.setHusId( house.getHusId() );
				List<Clean> cleans = CleanService.cleanList( CleanService.getList( cleanObj ) );
				house.setCleans( cleans );
			}
		}
		
		return companys;
	}
	
	@Override
	public List<Company> getEmptyRoomCompanys( Company paramVo )
	{
		List<Company> companys =  CompanyMapper.getList( paramVo );
		
		for( Company com : companys )
		{
			House house = new House();
			house.setComId( com.getComId() );
			com.setHouses( HouseService.houseList( HouseService.getEmptyHouses( house ) ) );
		}
		
		return companys;
	}
	
	@Override
	public Company getItem( HashMap<String, Object> map )
	{
		Company company = CompanyMapper.getItem( map );
					
		return company;
	}
	
	@Override
	public Company getCheckItem( HashMap<String, Object> map )
	{
		Company company = CompanyMapper.getCheckItem( map );
		/*
		User user = new User();
		user.setComId( company.getComId() );
		company.setUsers( UserService.userList( UserService.getList( user ) ) );
		
		House house = new House();
		house.setComId( company.getComId() );
		company.setHouses( HouseService.houseList( HouseService.getList( house ) ) );
		
		Clean clean = new Clean();			
		clean.setComId( company.getComId() );
		company.setCleans( CleanService.cleanList( CleanService.getList( clean ) ) );		
		*/
		
		House houseObj = new House();
		houseObj.setComId( company.getComId() );
		List<House> houses = HouseService.houseList( HouseService.getList( houseObj ) );
		company.setHouses( houses );
		
		for( House house : houses )
		{
			Room roomObj = new Room();
			roomObj.setHusId( house.getHusId() );
			List<Room> rooms = RoomService.roomList( RoomService.getList( roomObj ) );
			house.setRooms( rooms );
			
			Clean cleanObj = new Clean();
			cleanObj.setHusId( house.getHusId() );
			List<Clean> cleans = CleanService.cleanList( CleanService.getList( cleanObj ) );
			house.setCleans( cleans );
		}
			
		return company;
	}
	
	@Override
	public void createCompany( Company paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Company getData = CompanyMapper.getItem( paramMap );

		if ( getData == null )
		{
			CompanyMapper.insert( paramVo );
		}
		else
		{
			CompanyMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateCompany( Company paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		CompanyMapper.update( paramVo );
	}
	
	@Override
	public void deleteCompany( Company paramVo )
	{
		CompanyMapper.purge( paramVo );
	}
	
	@Override
	public String getMaxComId()
	{
		String maxId = CompanyMapper.getComId( );
		
		return maxId;
	}
	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Company> comList( List<Company> comlist )
	{
		Company com = new Company();
		List<Company> comArray = new ArrayList<Company>();
		for ( Company s : comlist )
		{
			com = comOne( s );
			comArray.add( com );
		}
		return comArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Company comOne( Company s )
	{
		Company com = new Company();
		if( s == null )
		{
			return com;
		}

		com.setId( s.getId() );					// 일련번호
		com.setSort( s.getSort() );
		com.setSortKo( Constants.stringCode( "SORT", s.getSort() ) );				// 구분(1.주택관리,2.부동산,3.청소,4.기타)
		com.setComId( s.getComId() );			// com id(system)
		com.setComName( s.getComName() );		// 회사명
		com.setCeoName( s.getCeoName() ); 		// 대표자명
		com.setTwitt( s.getTwitt() );			// 카카오아이디
		com.setCell( s.getCell() );				// 대표자모바일
		com.setPhone( s.getPhone() ); 			// 전화번호
		com.setFax( s.getFax() );				// 팩스
		com.setLocal( s.getLocal() );		// 우편번호
		com.setLocalKo(  Constants.stringCode("LOCAL", s.getLocal() ) );		// 우편번호
		com.setAddress( s.getAddress() );		// 주소
		com.setBank( s.getBank() );				// 은행명
		com.setAccount( s.getAccount() );		// 계좌번호
		com.setAccName( s.getAccName() );		// 계좌명
		com.setRegistDate( s.getRegistDate() );	// 등록일자
		com.setHouses( s.getHouses() );
		com.setCleans( s.getCleans() );
		com.setUsers( s.getUsers() );
		
		return com;
	}
}
