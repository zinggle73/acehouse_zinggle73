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
import com.ace.web.persistence.ContractMapper;
import com.ace.web.vo.Contract;
import com.service.common.Constants;
import com.service.common.exception.CommonException;

@Service ( "ContractService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class ContractServiceImpl implements ContractService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	@Autowired
	private ContractMapper ContractMapper;
	
	@Autowired
	private CompanyMapper CompanyMapper;
	
	@Override
	public List<Contract> getList( Contract paramVo )
	{
		List<Contract> list = ContractMapper.getList( paramVo );
		
		return list;
	}
	
	@Override
	public Contract getItem( HashMap<String, Object> map )
	{
		Contract roomVo = ContractMapper.getItem( map );
	
		return roomVo;
	}
	
	@Override
	public Contract getCheckItem( HashMap<String, Object> map )
	{
		Contract roomVo = ContractMapper.getCheckItem( map );
	
		return roomVo;
	}
	
	@Override
	public Long getPayItem( Contract paramVo )
	{
		Long pay = ContractMapper.getPayItem( paramVo );
		
		return pay;
	}
	
	@Override
	public List<Contract> getCompays()
	{
		List<Contract> coms = ContractMapper.getCompays();
		
		for( Contract com : coms )
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "comId", com.getComId() );
			com.setComName( CompanyMapper.getCheckItem( map ).getComName() );
			
			Contract object = new Contract();
			object.setComId( com.getComId() );
			List<Contract> years = ContractMapper.getYears( object );
			com.setYears( years );
			
			for( Contract year : years)
			{
				object.setYear( year.getYear() );
				List<Contract> locals = ContractMapper.getLocals( object );
				year.setLocals( locals );
				
				for( Contract local : locals)
				{
					local.setLocalKo( Constants.stringCode( "LOCAL", local.getLocal() ) );
					
					object.setLocal( local.getLocal() );
					List<Contract> conts = ContractMapper.getContracts( object );
					local.setConts( conts );
					
					for( Contract cont : conts )
					{
						cont.setLivingKo( Constants.stringCode( "LIVING", cont.getLiving() ) );
					}
				}
			}
		}
		
		return coms;
	}
	
	@Override
	public void createContract( Contract paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		Contract getData = ContractMapper.getItem( paramMap );

		if ( getData == null )
		{
			ContractMapper.insert( paramVo );
		}
		else
		{
			ContractMapper.update( paramVo );
		}
	}
	
	@Override
	public void updateContract( Contract paramVo ) throws CommonException
	{
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put( "id", paramVo.getId() );

		ContractMapper.update( paramVo );
	}
	
	@Override
	public void deleteContract( Contract paramVo )
	{
		ContractMapper.purge( paramVo );
	}
	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Contract> contractList( List<Contract> conlist )
	{
		Contract con = new Contract();
		List<Contract> conArray = new ArrayList<Contract>();
		for ( Contract s : conlist )
		{
			con = contractOne( s );
			conArray.add( con );
		}
		return conArray;
	}

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Contract contractOne( Contract s )
	{
		Contract con = new Contract();
		if( s == null )
		{
			return con;
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "comId", s.getComId() );
		
		con.setId( s.getId() );					// 일련번호
		con.setComId( s.getComId() );				// house id(system)
		con.setComName( CompanyMapper.getCheckItem( map ).getComName() );
		con.setYear( s.getYear() );			// con id(system)
		con.setLocal( s.getLocal() );
		con.setLocalKo( Constants.stringCode( "LOCAL", s.getLocal() ) );
		con.setLiving( s.getLiving() );			// 주거형태(1룸,1.5룸,2룸, 3룸, 아파트(면적), ..)
		con.setLivingKo( Constants.stringCode( "LIVING", s.getLiving() )  );
		con.setPay( s.getPay() ); 			// 청소금액(system)
		con.setIsYn( s.getIsYn() );	// 청소담당자(청소업체 선택)
		con.setRegistDate( s.getRegistDate() );

		return con;
	}

}
