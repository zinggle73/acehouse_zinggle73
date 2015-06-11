package com.ace.web.controller;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ace.web.service.CompanyService;
import com.ace.web.service.LoginService;
import com.ace.web.service.UserHistoryService;
import com.ace.web.service.UserService;
import com.ace.web.vo.Company;
import com.ace.web.vo.Mail;
import com.ace.web.vo.User;
import com.ace.web.vo.UserHistory;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.social.MailSender;
import com.service.common.util.BCrypt;
import com.service.common.util.DateUtil;
import com.service.common.util.SessionUtil;

@Controller
@RequestMapping ( value = "/auth" )
public class LoginController extends AbstractController
{

	@Autowired
	private LoginService LoginService;

	@Autowired
	private UserService UserService;
	
	@Autowired
	private CompanyService CompanyService;
	
	@Autowired
	private UserHistoryService UserHistoryService;
	
		

	@RequestMapping ( value = "/login", method = RequestMethod.POST )
	public void loginUser(@RequestBody User object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LOGIN CALL : "+ object.getUserId()+"   "+object.getPasswd());
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		HashMap<String, Object> result = new HashMap<String, Object>();
		User user = new User();
		
		try
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "userId", object.getUserId() );
						
			user = UserService.sessionOne( UserService.getCheckItem( paramMap ) );

			if ( user != null )
			{				
				if ( user.getToken() == null || user.getToken().equals( "" ) )
				{
					String csrf_token = LoginService.getUuidUser();

					user.setToken( csrf_token );
				}
				
				user.setConnCount( user.getConnCount()+1 );
				user.setLastLoginDate( user.getNowLoginDate() );
				user.setNowLoginDate( DateUtil.getCurrentDate() );
				UserService.updateUser( user );
				
				/* ******  Login 히스토리 저장  ****** */
				UserHistory history = new UserHistory();
				history.setComId( user.getComId() );
				history.setUserId( user.getUserId() );
				history.setConnCount( user.getConnCount() );
				history.setNowLoginDate( user.getNowLoginDate() );
				history.setLastLoginDate( user.getLastLoginDate() );
				history.setLogoutDate( user.getLogoutDate() );
				UserHistoryService.createUser( history );

				
				if( BCrypt.checkpw( object.getPasswd(), user.getPasswd() ) )
				{
					SessionUtil.setAttribute( request, "login_user", user );
					sessionUser = (User)SessionUtil.getLoginUser( request );
					result.put( "user", sessionUser );
				}
				else
				{
					result.put( "user", null );
				}
			}
			else
			{
				result.put( "user", null );
			}
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( result, request, response );

	}
		
	
	@RequestMapping ( value = "/session", method = RequestMethod.POST )
	public void loginToken(@RequestBody User object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET Session Token CALL : "+ object.getId()+"   "+object.getToken());
		User user = new User();
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		try
		{
			User sessionUser = (User)SessionUtil.getLoginUser( request );

			//String userId = object.getUserId();
			Long id = object.getId();
			String token = object.getToken();
			logger.error( "GET session Token length : "+ token.length()+" / sessionUser : "+sessionUser);
			
			if ( token.length() > 5 )
			{
				if ( sessionUser == null )
				{
					HashMap<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put( "id", id );
					paramMap.put( "token", token );

					user = UserService.sessionOne( UserService.getCheckItem( paramMap ) );
					logger.error( "GET session Token user : "+ user);
					if ( user != null )
					{
						SessionUtil.setAttribute( request, "login_user", user );
						sessionUser = (User)SessionUtil.getLoginUser( request );
						
						result.put( "user", sessionUser );
					}
					else
					{
						result.put( "user", null );
					}
				}	
				else
				{
					result.put( "user", sessionUser );
				}
			}
			else
			{
				// empty cookie
				result.put( "user", null );
			}
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( result, request, response );

	}
		
	@RequestMapping ( value = "/signup", method = RequestMethod.POST )
	public void signupTfspMember( @RequestBody User object, HttpServletRequest request,	HttpServletResponse response ) throws CommonException
	{
		try
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "comId", object.getComId() );			
			Company com = CompanyService.getCheckItem(paramMap);
			
			object.setPasswd( BCrypt.hashpw( object.getPasswd(), BCrypt.gensalt(10) ) );
			object.setWorkField( com.getSort() );
			object.setUserAuth( "5" );
			LoginService.createUser( object );
		}
		catch ( Exception e )
		{
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		HashMap<String, String> result = new HashMap<String, String>();
		result.put( "message", "OK" );
		setResponse( result, request, response );
	}	
	

	/**
	 * agent manager 濡쒓렇�꾩썐..
	 * 
	 * @Param
	 * 
	 * @ReturnType ModelAndView
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/logout", method = RequestMethod.GET )
	public void userLogout( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		logger.error( "GET LOGOUT CALL : "+sessionUser.getUserId() );
		try
		{
			if( sessionUser != null )
			{
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put( "userId", sessionUser.getUserId() );
							
				User user = UserService.getCheckItem( paramMap );
				user.setLogoutDate( DateUtil.getCurrentDate() );
				
				UserService.updateUser( user );
			}
			
			SessionUtil.removeAttribute( request, "login_user" );
			
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}
	
	
	@RequestMapping ( value = "/unique/{userId}", method = RequestMethod.GET )
	public void uniqueUser(@PathVariable ( "userId" ) String userId, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET UNIQUE USERID CALL : " + userId );
		User object = new User();
		
		try
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "userId", userId );
			object = LoginService.getCheckItem( paramMap );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		logger.error( "GET UNIQUE object.getUserId() CALL : " + object );
		setResponse( object , request, response );

	}
	
	@RequestMapping ( value = "/forgot", method = RequestMethod.POST )
	public void forgetPass( @RequestBody User object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET FORGET PASSWORD CALL : " + object.getEmail() +" / "+ object.getUserId() );
		User user = new User();	
		
		try
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "userId", object.getUserId() );
			paramMap.put( "email", object.getEmail() );
			user = LoginService.getCheckItem( paramMap );
			
			if( user != null )
			{
				/*****패스워드 난수생성 ******/
				Random generator = new Random();    
				String randomPass = String.valueOf( Math.abs( generator.nextInt() ) );
				//logger.error( "GET FORGET PASSWORD CALL 2 pass : " + pass +" / "+ user.getEmail());
				
				/*****패스워드 암호화해서 USER테이블 업데이트******/
				user.setPasswd( BCrypt.hashpw( randomPass, BCrypt.gensalt(10) ) );				
				UserService.updateUser( user );
								
				/*****패스워드 해당메일로 보내기******/ 
				Mail mail = new Mail();
				mail.setFromUser( "zinggle73@gmail.com" );
				mail.setToUser( user.getEmail() );
				mail.setSubject( "[ACE HOUSING] "+user.getUserName()+"님  요청메일입니다." );
				mail.setText( user.getUserName()+"님의 비밀번호 찾기요청에 의해 다음과 같이 임의변경되었습니다. <br>로그인하신후 비밀번호를 안전하게 변경하시기 바랍니다. <br><br>변경비밀번호 : <b>"+randomPass+"</b>" );
				MailSender.sendEmail( mail );
				
			}

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		//logger.error( "GET UNIQUE object.getUserId() CALL : " + user );
		setResponse( user , request, response );

	}
	
	@RequestMapping ( value = "/changePw", method = RequestMethod.POST )
	public void changePw( @RequestBody User object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET CHANGE PASSWORD CALL : "  );
		
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		HashMap<String, Object> result = new HashMap<String, Object>();
		User user = new User();

		try
		{
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put( "userId", sessionUser.getUserId() );
						
			user = UserService.userOne( UserService.getCheckItem( paramMap ) );

			if ( user != null )
			{								
				if( BCrypt.checkpw( object.getPasswd(), user.getPasswd() ) )
				{
					user.setPasswd( BCrypt.hashpw( object.getChangePw(), BCrypt.gensalt(10) ) );					
					UserService.updateUser( user );
					
					result.put("message", "OK");
				}
				else
				{
					result.put("message", "Fail");
				}
			}
			else
			{
				result.put("message", "Fail");
			}
		}
		catch ( Exception e )
		{
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( result, request, response );
	}

}
