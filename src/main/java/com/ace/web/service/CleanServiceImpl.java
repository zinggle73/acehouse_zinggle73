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
import com.ace.web.vo.Clean;
import com.service.common.Constants;
import com.service.common.exception.CommonException;
import com.service.common.util.DateTime;

@Service ( "CleanService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class CleanServiceImpl implements CleanService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private CleanMapper CleanMapper;

	
	public List<Clean> getAllList( )
	{
		return CleanMapper.getAllList( );		
	}
	
	@Override
	public List<Clean> getList( Clean paramVo )
	{
		return CleanMapper.getList( paramVo );
	}

	@Override
	public Clean getItem( HashMap<String, Object> map )
	{
		Clean cleanVo = CleanMapper.getItem( map );
	
		return cleanVo;
	}
	
	@Override
	public void createClean( Clean paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Clean getData = CleanMapper.getItem( paramMap );

		if ( getData == null )
		{
			CleanMapper.insert( paramVo );
		}
		else
		{
			CleanMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateClean( Clean paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		CleanMapper.update( paramVo );
	}
	
	@Override
	public void deleteClean( Clean paramVo )
	{
		CleanMapper.purge( paramVo );
	}

	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Clean> cleanList( List<Clean> cleanlist )
	{
		Clean clean = new Clean();
		List<Clean> cleanArray = new ArrayList<Clean>();
		for ( Clean s : cleanlist )
		{
			clean = cleanOne( s );
			cleanArray.add( clean );
		}
		return cleanArray;
	}
	

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Clean cleanOne( Clean s )
	{
		Clean clean = new Clean();
		if( s == null )
		{
			return clean;
		}
		
		clean.setId( s.getId() );
		clean.setComId( s.getComId() );				// company id(발주처 session)
		clean.setComName( s.getComName() );
		clean.setHusId( s.getHusId() );				// house id(system)
		clean.setHusName( s.getHusName() );
		clean.setRoomNum( s.getRoomNum() );			// room id(system)
		clean.setOrderer( s.getOrderer() );			// 주문담당자(발주처 session)	
		clean.setOderName( s.getOderName() );
		clean.setPhone( s.getPhone() );				// 전화번호(발주처 session)
		clean.setPay( s.getPay() );
		clean.setAddPay( s.getAddPay() );			// 청소금액(system)
		clean.setLiving( s.getLiving() );
		clean.setLivingMsg( Constants.stringCode( "LIVING", s.getLiving() ) );			// 주거형태(원룸,투룸...)(system)
		clean.setCharge( s.getCharge() );
		clean.setChargeName( s.getChargeName() ); 	// 청소담당자(청소업체 선택)
		clean.setDeposit( s.getDeposit() );			// 입금확인(청소업체 선택)
		clean.setIsIng( s.getIsIng() );
		clean.setIsIngMsg( Constants.stringCode( "ISING", s.getIsIng() ) );				// 진행과정 Constants
		clean.setAddress( s.getAddress() );			// 건물주소(system)
		clean.setMemo( s.getMemo() );
		clean.setGatepass( s.getGatepass() );
		clean.setBepass( s.getBepass() );
		clean.setAfpass( s.getAfpass() );
		clean.setOrderDate( DateTime.printDate( s.getOrderDate() ) );		// 주문일자(system)
		clean.setHopeDate( DateTime.printDate( s.getHopeDate() ) );
		clean.setConfirmDate( DateTime.printDate( s.getConfirmDate() ) ); // 결정일자(청소업체)

		return clean;
	}
	
	
}
