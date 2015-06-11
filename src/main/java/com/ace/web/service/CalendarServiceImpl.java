package com.ace.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ace.web.vo.Calendar;
import com.ace.web.vo.Clean;
import com.service.common.Constants;
import com.service.common.util.StringUtil;

@Service ( "CalendarService" )
@Transactional ( propagation = Propagation.NESTED, isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class )
public class CalendarServiceImpl implements CalendarService
{
	//private static final ComLogger logger = ComLoggerFactory.getLogger( UserServiceImpl.class );

	
	/* convert snsMsgList to bizMsgList */
	@Override
	public List<Calendar> calendarList( List<Clean> cleanlist )
	{
		Calendar cal = new Calendar();
		List<Calendar> calArray = new ArrayList<Calendar>();
		for ( Clean s : cleanlist )
		{
			cal = calendarOne( s );
			calArray.add( cal );
		}
		return calArray;
	}
	

	/* convert snsMsgVo to bizMsgVo */
	@Override
	public Calendar calendarOne( Clean s )
	{
		Calendar calendar = new Calendar();
		
		if( s == null )
		{
			return calendar;
		}

		calendar.setId( s.getId() );
		calendar.setTitle( "[ "+Constants.stringCode( "ISING", s.getIsIng() )+" ] "+s.getComName()+" / "+s.getHusName()+" / "+s.getRoomNum()+" 호실 / "+ Constants.stringCode( "LIVING", s.getLiving() )+" / 담당 : "+StringUtil.nullCheck( s.getChargeName() ) );
		
		if( s.getIsIng().equals( "100" ) )
		{
			calendar.setStart( s.getHopeDate() );
		}	
		else if( !s.getIsIng().equals( "100" ) )
		{
			calendar.setStart( s.getConfirmDate() );
		}
		//System.out.println(calendar.getStart());
		calendar.setAllDay( true );
		
		return calendar;
	}
	
	
}
