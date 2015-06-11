package com.ace.web.vo;

import java.util.ArrayList;
import java.util.List;

// 직원내역
public class User 
{
	private long id;					// 일련번호
	private String comId;				// company id
	private String comName;				// 회사명
	private String userId;				// 사용자 아이디
	private String passwd;				// 비밀번호
	private String changePw;
	private String email;
	private String token;				// token
	private String userName;			// 이름
	private String phone;				// 전화번호
	private String twitt;				// 카카오id
	private String workField;			// 업무분야
	private String workMsg;
	private String userAuth;			// 사용자권한
	private String authMsg;
	private String registDate;			// 가입일
	private long connCount;
	private String lastLoginDate;
	private String nowLoginDate;
	private String logoutDate;
	
	private List<UserHistory> historys;		//청소
	
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
	public String getUserId()
	{
		return userId;
	}
	public void setUserId( String userId )
	{
		this.userId = userId;
	}
	public String getPasswd()
	{
		return passwd;
	}
	public void setPasswd( String passwd )
	{
		this.passwd = passwd;
	}
	public String getChangePw()
	{
		return changePw;
	}
	public void setChangePw( String changePw )
	{
		this.changePw = changePw;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail( String email )
	{
		this.email = email;
	}
	public String getToken()
	{
		return token;
	}
	public void setToken( String token )
	{
		this.token = token;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName( String userName )
	{
		this.userName = userName;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone( String phone )
	{
		this.phone = phone;
	}
	public String getTwitt()
	{
		return twitt;
	}
	public void setTwitt( String twitt )
	{
		this.twitt = twitt;
	}
	public String getWorkField()
	{
		return workField;
	}
	public void setWorkField( String workField )
	{
		this.workField = workField;
	}
	public String getUserAuth()
	{
		return userAuth;
	}
	public void setUserAuth( String userAuth )
	{
		this.userAuth = userAuth;
	}
	public String getRegistDate()
	{
		return registDate;
	}
	public void setRegistDate( String registDate )
	{
		this.registDate = registDate;
	}
	public String getWorkMsg()
	{
		return workMsg;
	}
	public void setWorkMsg( String workMsg )
	{
		this.workMsg = workMsg;
	}
	public String getAuthMsg()
	{
		return authMsg;
	}
	public void setAuthMsg( String authMsg )
	{
		this.authMsg = authMsg;
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
	public List<UserHistory> getHistorys()
	{
		return historys;
	}
	public void setHistorys( List<UserHistory> historys )
	{
		this.historys = historys;
	}
	public void addHistorys(UserHistory history)
	{
        if(historys == null)
        {
        	historys = new ArrayList<UserHistory>();
        }
        
        getHistorys().add(history);
    }
}
