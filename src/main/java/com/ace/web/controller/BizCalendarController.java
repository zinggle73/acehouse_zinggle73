package com.ace.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ace.web.service.CalendarService;
import com.ace.web.service.CleanService;
import com.ace.web.vo.Calendar;
import com.ace.web.vo.Clean;
import com.ace.web.vo.User;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.util.DateTime;
import com.service.common.util.SessionUtil;

@Controller
@Validated
@RequestMapping ( value = "/biz/calendar" )
public class BizCalendarController extends AbstractController
{

	@Autowired
	private CleanService CleanService;
	
	@Autowired
	private CalendarService CalendarService;
	
	
	/**
	 * Admin List ALL Cleans 
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Cleans table
	 * @throws CommonException
	 */
	@RequestMapping ( value="/{id}", method = RequestMethod.GET )
	public void chargeCleans( @PathVariable ( "id" ) String id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET CALENDAR LIST CALL : " + DateTime.getCurrentYear() );
		
		Clean object = new Clean();
		List<Calendar> list = new ArrayList<Calendar>();
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		
		try
		{
			if( sessionUser.getWorkField().equals( "03" ) )
			{
				object.setCharge( id );
			}
			else if( sessionUser.getWorkField().equals( "01" ) )
			{
				object.setComId( id );
			}
			
			object.setConfirmDate( DateTime.getCurrentYear() );
			list = CalendarService.calendarList( CleanService.getList( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
	

}
