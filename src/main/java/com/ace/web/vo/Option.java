package com.ace.web.vo;

// 건물옵션
public class Option 
{
	private long id;					// 일련번호
	private String husId;					// house id
	private String roomNum;				// room id
	private String opKey;				// option key
	private String opValue;				// option value

	private Room room;

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

	public String getOpKey()
	{
		return opKey;
	}

	public void setOpKey( String opKey )
	{
		this.opKey = opKey;
	}

	public String getOpValue()
	{
		return opValue;
	}

	public void setOpValue( String opValue )
	{
		this.opValue = opValue;
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
