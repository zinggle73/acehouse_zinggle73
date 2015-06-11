package com.ace.web.vo;

import java.util.List;


// 건물호실 내역
public class Room 
{
	private long id;					// 일련번호
	private String husId;				// 건물ID
	private String roomNum;				// 호실
	private String living;				// 주거형태(1룸,1.5룸,2룸, 3룸, 아파트(면적), ..)
	private String livingMsg;
	private long amount;				// 청소금액
	private String beforePass;			// 전비번
	private String afterPass;			// 후비번
	private String isZero;				// 공실현황
	private String zeroMsg;				// 
	private String roomNums;			// 
	private String isIng;
	private String isIngMsg;
	private String hopeDate;
	
	private Reader reader;
	private List<Reader> readers;
	
	public long getId()
	{
		return id;
	}
	public void setId( long id )
	{
		this.id = id;
	}
	public String getHusId()
	{
		return husId;
	}
	public void setHusId( String husId )
	{
		this.husId = husId;
	}
	public String getRoomNum()
	{
		return roomNum;
	}
	public void setRoomNum( String roomNum )
	{
		this.roomNum = roomNum;
	}
	public String getLiving()
	{
		return living;
	}
	public void setLiving( String living )
	{
		this.living = living;
	}
	public String getLivingMsg()
	{
		return livingMsg;
	}
	public void setLivingMsg( String livingMsg )
	{
		this.livingMsg = livingMsg;
	}
	public long getAmount()
	{
		return amount;
	}
	public void setAmount( long amount )
	{
		this.amount = amount;
	}
	public String getBeforePass()
	{
		return beforePass;
	}
	public void setBeforePass( String beforePass )
	{
		this.beforePass = beforePass;
	}
	public String getAfterPass()
	{
		return afterPass;
	}
	public void setAfterPass( String afterPass )
	{
		this.afterPass = afterPass;
	}
	public String getIsZero()
	{
		return isZero;
	}
	public void setIsZero( String isZero )
	{
		this.isZero = isZero;
	}
	public void setZero( String isZero )
	{
		this.isZero = isZero;
	}
	public String getZeroMsg() {
		return zeroMsg;
	}
	public void setZeroMsg(String zeroMsg) {
		this.zeroMsg = zeroMsg;
	}
	public String getIsIng()
	{
		return isIng;
	}
	public void setIsIng( String isIng )
	{
		this.isIng = isIng;
	}
	public String getIsIngMsg()
	{
		return isIngMsg;
	}
	public void setIsIngMsg( String isIngMsg )
	{
		this.isIngMsg = isIngMsg;
	}	
	public String getHopeDate()
	{
		return hopeDate;
	}
	public void setHopeDate( String hopeDate )
	{
		this.hopeDate = hopeDate;
	}
	public String getRoomNums() 
	{
		return roomNums;
	}
	public void setRoomNums(String roomNums) 
	{
		this.roomNums = roomNums;
	}
	public List<Reader> getReaders()
	{
		return readers;
	}
	public void setReaders( List<Reader> readers )
	{
		this.readers = readers;
	}
	public Reader getReader()
	{
		return reader;
	}
	public void setReader( Reader reader )
	{
		this.reader = reader;
	}
}
