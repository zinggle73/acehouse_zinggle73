package com.ace.web.vo;


// 청소접수
public class Calendar 
{
	private long id;
	private String title;				// company id(발주처 session)
	private String start;				// 회사명
	private String end;
	private String url;
	private boolean allDay;
	
	public long getId()
	{
		return id;
	}
	public void setId( long id )
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle( String title )
	{
		this.title = title;
	}
	public String getStart()
	{
		return start;
	}
	public void setStart( String start )
	{
		this.start = start;
	}
	public String getEnd()
	{
		return end;
	}
	public void setEnd( String end )
	{
		this.end = end;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl( String url )
	{
		this.url = url;
	}
	public boolean isAllDay()
	{
		return allDay;
	}
	public void setAllDay( boolean allDay )
	{
		this.allDay = allDay;
	}

		
}
