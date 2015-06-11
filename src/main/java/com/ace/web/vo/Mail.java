package com.ace.web.vo;

public class Mail
{
	private String toUser;
	private String fromUser;
	private String subject;
	private String text;
	
	public String getToUser()
	{
		return toUser;
	}
	public void setToUser( String toUser )
	{
		this.toUser = toUser;
	}
	public String getFromUser()
	{
		return fromUser;
	}
	public void setFromUser( String fromUser )
	{
		this.fromUser = fromUser;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject( String subject )
	{
		this.subject = subject;
	}
	public String getText()
	{
		return text;
	}
	public void setText( String text )
	{
		this.text = text;
	}
	
	
}
