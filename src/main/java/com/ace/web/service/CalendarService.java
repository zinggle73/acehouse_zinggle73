package com.ace.web.service;

import java.util.List;

import com.ace.web.vo.Calendar;
import com.ace.web.vo.Clean;

public interface CalendarService
{
	
	public List<Calendar> calendarList( List<Clean> cleanlist );
	
	public Calendar calendarOne( Clean s );

}
