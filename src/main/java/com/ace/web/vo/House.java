package com.ace.web.vo;

import java.util.ArrayList;
import java.util.List;


// 건물내역
public class House 
{
	private long id;					// 일련번호
	private String comId;
	private String comName;
	private String husId;				// 건물ID
	private String husName;				// 건물명
	private String owner;				// 건물주명
	private String phone;				// 건물주전번
	private String local;				//우편번호
	private String localKo;				//우편번호
	private String address;				//주소
	private String gatePass;			// 출입문비번
	private String isIng;				// 
	private String zeroNum;				// 공실갯수
	private String registDate;			// 등록일자
	
	private List<Clean> cleans;		//청소
	private List<Room> rooms;
	
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
	public String getHusId()
	{
		return husId;
	}
	public void setHusId( String husId )
	{
		this.husId = husId;
	}
	public String getHusName()
	{
		return husName;
	}
	public void setHusName( String husName )
	{
		this.husName = husName;
	}
	public String getOwner()
	{
		return owner;
	}
	public void setOwner( String owner )
	{
		this.owner = owner;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
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
	public String getAddress()
	{
		return address;
	}
	public void setAddress( String address )
	{
		this.address = address;
	}
	public String getIsIng()
	{
		return isIng;
	}
	public void setIsIng( String isIng )
	{
		this.isIng = isIng;
	}
	public String getRegistDate()
	{
		return registDate;
	}
	public void setRegistDate( String registDate )
	{
		this.registDate = registDate;
	}
	public String getGatePass()
	{
		return gatePass;
	}
	public void setGatePass( String gatePass )
	{
		this.gatePass = gatePass;
	}
	public String getZeroNum()
	{
		return zeroNum;
	}
	public void setZeroNum( String zeroNum )
	{
		this.zeroNum = zeroNum;
	}
	public List<Room> getRooms()
	{
		return rooms;
	}
	public void setRooms( List<Room> rooms )
	{
		this.rooms = rooms;
	}
	public void addRooms(Room room)
	{
        if(rooms == null)
        {
        	rooms = new ArrayList<Room>();
        }
        
        //clean.setCompany(this);
        getRooms().add(room);
    }
	public List<Clean> getCleans()
	{
		return cleans;
	}
	public void setCleans( List<Clean> cleans )
	{
		this.cleans = cleans;
	}
	public void addCleans(Clean clean)
	{
        if(cleans == null)
        {
        	cleans = new ArrayList<Clean>();
        }
        
        getCleans().add(clean);
    }
}
