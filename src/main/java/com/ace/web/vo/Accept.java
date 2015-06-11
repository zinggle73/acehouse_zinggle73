package com.ace.web.vo;

// 수리내역
public class Accept 
{
	private long id;					// 일련번호
	private String comId;				// house id
	private String husId;				// house id
	private String roomNum;				// room id
	private String year;				// 수납년
	private String month;				// 수납월
	private String isReceipt;			// 납부현황

	private Room room;			//외부키
	
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
	public String getYear()
	{
		return year;
	}
	public void setYear( String year )
	{
		this.year = year;
	}
	public String getMonth()
	{
		return month;
	}
	public void setMonth( String month )
	{
		this.month = month;
	}
	public String getIsReceipt()
	{
		return isReceipt;
	}
	public void setIsReceipt( String isReceipt )
	{
		this.isReceipt = isReceipt;
	}
	public Room getRoom()
	{
		return room;
	}
	public void setRoom( Room room )
	{
		this.room = room;
	}
	
	
	
}
