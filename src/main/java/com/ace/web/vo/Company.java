package com.ace.web.vo;

import java.util.ArrayList;
import java.util.List;

// 주택관리회사 내역
public class Company 
{
	private long id;					//일련번호
	private String sort;				//구분(1.주택관리,2.부동산,3.청소,4.기타)
	private String sortKo;
	private String comId;				//회사ID
	private String comName;				//회사명
	private String comNum;    			//사업자번호
	private String ceoName;				//대표자
	private String twitt;				//카카오아이디
	private String cell;				//대표자모바일
	private String phone;				//전화번호
	private String fax;					//팩스
	private String local;				//우편번호
	private String localKo;				//우편번호
	private String address;				//주소
	private String bank;				// 은행명
	private String account;				// 계좌번호
	private String accName;				// 계좌명
	private String registDate;			// 등록일자

	private List<House> houses;		//건물
	private List<User> users;			//직원
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

	public String getSort()
	{
		return sort;
	}
	public void setSort( String sort )
	{
		this.sort = sort;
	}

	public String getSortKo()
	{
		return sortKo;
	}
	public void setSortKo( String sortKo )
	{
		this.sortKo = sortKo;
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
	public String getComNum()
	{
		return comNum;
	}
	public void setComNum( String comNum )
	{
		this.comNum = comNum;
	}
	public String getCeoName()
	{
		return ceoName;
	}
	public void setCeoName( String ceoName )
	{
		this.ceoName = ceoName;
	}
	public String getTwitt()
	{
		return twitt;
	}
	public void setTwitt( String twitt )
	{
		this.twitt = twitt;
	}
	public String getCell()
	{
		return cell;
	}
	public void setCell( String cell )
	{
		this.cell = cell;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone( String phone )
	{
		this.phone = phone;
	}
	public String getFax()
	{
		return fax;
	}
	public void setFax( String fax )
	{
		this.fax = fax;
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
	public String getRegistDate()
	{
		return registDate;
	}
	public void setRegistDate( String registDate )
	{
		this.registDate = registDate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public List<House> getHouses()
	{
		return houses;
	}
	public void setHouses( List<House> houses )
	{
		this.houses = houses;
	}
	public void addHouses(House house)
	{
        if(houses == null)
        {
        	houses = new ArrayList<House>();
        }
        
        //house.setCompany(this);
        getHouses().add(house);
    }
	
	public List<User> getUsers()
	{
		return users;
	}
	public void setUsers( List<User> users )
	{
		this.users = users;
	}
	public void addUsers(User user)
	{
        if(users == null)
        {
        	users = new ArrayList<User>();
        }
        
        //user.setCompany(this);
        getUsers().add(user);
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
        
        //clean.setCompany(this);
        getCleans().add(clean);
    }
	public List<Room> getRooms()
	{
		return rooms;
	}
	public void setRooms( List<Room> rooms )
	{
		this.rooms = rooms;
	}
	
}
