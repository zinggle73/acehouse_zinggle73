package com.ace.web.vo;


// 직원내역
public class UserHistory 
{
	private long id;					// 일련번호
	private String comId;				// company id
	private String userId;				// 사용자 아이디
	private long connCount;
	private String lastLoginDate;
	private String nowLoginDate;
	private String logoutDate;
	
	public long getId()
	{
		return id;
	}
	public void setId( long id )
	{
		this.id = id;
	}
	public String getComId()
	{
		return comId;
	}
	public void setComId( String comId )
	{
		this.comId = comId;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId( String userId )
	{
		this.userId = userId;
	}
	public long getConnCount()
	{
		return connCount;
	}
	public void setConnCount( long connCount )
	{
		this.connCount = connCount;
	}
	public String getLastLoginDate()
	{
		return lastLoginDate;
	}
	public void setLastLoginDate( String lastLoginDate )
	{
		this.lastLoginDate = lastLoginDate;
	}
	public String getNowLoginDate()
	{
		return nowLoginDate;
	}
	public void setNowLoginDate( String nowLoginDate )
	{
		this.nowLoginDate = nowLoginDate;
	}
	public String getLogoutDate()
	{
		return logoutDate;
	}
	public void setLogoutDate( String logoutDate )
	{
		this.logoutDate = logoutDate;
	}
	
	
}
