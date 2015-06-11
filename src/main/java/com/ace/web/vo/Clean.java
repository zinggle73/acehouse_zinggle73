package com.ace.web.vo;

// 청소접수
public class Clean 
{
	private long id;					// 일련번호
	private String comId;				// company id(발주처 session)
	private String comName;				// 회사명
	private String husId;				// house id(system)
	private String husName;				// 빌라명
	private String roomNum;				// room id(system)
	private String orderer;				// 주문담당자(발주처 session)
	private String oderName;
	private String phone;				// 전화번호(발주처 session)
	private long pay;					// 청소금액(system)
	private long addPay;				// 추가금액
	private String living;				// 주거형태(원룸,투룸...)(system)
	private String livingMsg;			// 주거형태명
	private String charge;				// 청소담당자(청소업체 선택)
	private String chargeName;			//
	private String deposit;				// 입금확인(청소업체 선택)
	private String isIng;				// 진행과정 Constants
	private String isIngMsg;
	private String address;				// 건물주소(system)
	private String memo;				// 메모
	private String gatepass;
	private String bepass;
	private String afpass; 
	private String orderDate;			// 주문일자(system)
	private String hopeDate;
	private String confirmDate;			// 결정일자(청소업체)
	
	
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
	public String getRoomNum()
	{
		return roomNum;
	}
	public void setRoomNum( String roomNum )
	{
		this.roomNum = roomNum;
	}
	public String getOrderer()
	{
		return orderer;
	}
	public void setOrderer( String orderer )
	{
		this.orderer = orderer;
	}
	public String getOderName()
	{
		return oderName;
	}
	public void setOderName( String oderName )
	{
		this.oderName = oderName;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone( String phone )
	{
		this.phone = phone;
	}
	public long getPay()
	{
		return pay;
	}
	public void setPay( long pay )
	{
		this.pay = pay;
	}
	public long getAddPay()
	{
		return addPay;
	}
	public void setAddPay( long addPay )
	{
		this.addPay = addPay;
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
	public String getCharge()
	{
		return charge;
	}
	public void setCharge( String charge )
	{
		this.charge = charge;
	}
	public String getChargeName()
	{
		return chargeName;
	}
	public void setChargeName( String chargeName )
	{
		this.chargeName = chargeName;
	}
	public String getDeposit()
	{
		return deposit;
	}
	public void setDeposit( String deposit )
	{
		this.deposit = deposit;
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
	public String getAddress()
	{
		return address;
	}
	public void setAddress( String address )
	{
		this.address = address;
	}
	public String getMemo()
	{
		return memo;
	}
	public void setMemo( String memo )
	{
		this.memo = memo;
	}
	public String getGatepass()
	{
		return gatepass;
	}
	public void setGatepass( String gatepass )
	{
		this.gatepass = gatepass;
	}
	public String getBepass()
	{
		return bepass;
	}
	public void setBepass( String bepass )
	{
		this.bepass = bepass;
	}
	public String getAfpass()
	{
		return afpass;
	}
	public void setAfpass( String afpass )
	{
		this.afpass = afpass;
	}
	public String getOrderDate()
	{
		return orderDate;
	}
	public void setOrderDate( String orderDate )
	{
		this.orderDate = orderDate;
	}
	public String getHopeDate()
	{
		return hopeDate;
	}
	public void setHopeDate( String hopeDate )
	{
		this.hopeDate = hopeDate;
	}
	public String getConfirmDate()
	{
		return confirmDate;
	}
	public void setConfirmDate( String confirmDate )
	{
		this.confirmDate = confirmDate;
	}
	
	
}
