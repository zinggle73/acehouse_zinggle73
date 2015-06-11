package com.ace.web.vo;

import java.util.List;

public class Contract
{
	private long id;
	private String comId;
	private String comName;
	private String year;
	private String local;
	private String localKo;
	private String living;
	private String livingKo;
	private long pay;
	private String isYn;
	private String registDate;
	
	private List<Contract> coms;
	private List<Contract> years;
	private List<Contract> locals;
	private List<Contract> conts; 
	
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
	public String getComName()
	{
		return comName;
	}
	public void setComName( String comName )
	{
		this.comName = comName;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear( String year )
	{
		this.year = year;
	}
	public String getLocal()
	{
		return local;
	}
	public void setLocal( String local )
	{
		this.local = local;
	}
	public String getLocalKo()
	{
		return localKo;
	}
	public void setLocalKo( String localKo )
	{
		this.localKo = localKo;
	}
	public String getLiving()
	{
		return living;
	}
	public void setLiving( String living )
	{
		this.living = living;
	}
	public String getLivingKo()
	{
		return livingKo;
	}
	public void setLivingKo( String livingKo )
	{
		this.livingKo = livingKo;
	}
	public long getPay()
	{
		return pay;
	}
	public void setPay( long pay )
	{
		this.pay = pay;
	}
	public String getIsYn()
	{
		return isYn;
	}
	public void setIsYn( String isYn )
	{
		this.isYn = isYn;
	}
	public String getRegistDate()
	{
		return registDate;
	}
	public void setRegistDate( String registDate )
	{
		this.registDate = registDate;
	}
	public List<Contract> getComs()
	{
		return coms;
	}
	public void setComs( List<Contract> coms )
	{
		this.coms = coms;
	}
	public List<Contract> getYears()
	{
		return years;
	}
	public void setYears( List<Contract> years )
	{
		this.years = years;
	}
	public List<Contract> getLocals()
	{
		return locals;
	}
	public void setLocals( List<Contract> locals )
	{
		this.locals = locals;
	}
	public List<Contract> getConts()
	{
		return conts;
	}
	public void setConts( List<Contract> conts )
	{
		this.conts = conts;
	}
	
		
}
