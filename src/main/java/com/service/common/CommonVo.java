package com.service.common;


public class CommonVo
{

	// @JsonIgnore
	private int rownum;
	// @JsonIgnore
	private int from;
	// @JsonIgnore
	private int to;

	private String csrf_token;
	// @JsonIgnore
	private String passwdConfirm;

	public void setRownum( int rownum )
	{
		this.rownum = rownum;
	}
	public int getRownum()
	{
		return rownum;
	}
	public int getFrom()
	{
		return from;
	}
	public int getTo()
	{
		return to;
	}
	public void setFrom( int from )
	{
		this.from = from;
	}
	public void setTo( int to )
	{
		this.to = to;
	}
	public String getPasswdConfirm() 
	{
		return passwdConfirm;
	}
	public String getCsrf_token() 
	{
		return csrf_token;
	}
	public void setPasswdConfirm(String passwdConfirm) 
	{
		this.passwdConfirm = passwdConfirm;
	}
	
	public void setCsrf_token(String csrf_token)
	{
		this.csrf_token = csrf_token;
	}
}
