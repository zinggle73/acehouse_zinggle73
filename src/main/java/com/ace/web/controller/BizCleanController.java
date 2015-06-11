package com.ace.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ace.web.service.CleanService;
import com.ace.web.service.CompanyService;
import com.ace.web.service.ContractService;
import com.ace.web.service.HouseService;
import com.ace.web.service.RoomService;
import com.ace.web.service.UserService;
import com.ace.web.vo.Clean;
import com.ace.web.vo.Contract;
import com.ace.web.vo.House;
import com.ace.web.vo.Room;
import com.ace.web.vo.User;
import com.service.common.AbstractController;
import com.service.common.Constants;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.social.TwitterSendMessage;
import com.service.common.util.DateTime;
import com.service.common.util.SessionUtil;

@Controller
@Validated
@RequestMapping ( value = "/biz/clean" )
public class BizCleanController extends AbstractController
{

	@Autowired
	private CleanService CleanService;
	
	@Autowired
	private CompanyService CompanyService;
	
	@Autowired
	private ContractService ContractService;
	
	@Autowired
	private HouseService HouseService;
	
	@Autowired
	private UserService UserService;
	
	@Autowired
	private RoomService RoomService;
	/**
	 * Admin List ALL Cleans 
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Cleans table
	 * @throws CommonException
	 */
	@RequestMapping ( value="/{id}", method = RequestMethod.GET )
	public void bizCleans(  @PathVariable ( "id" ) String id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET CLEAN LIST CALL : "  );
		
		Clean object = new Clean();
		List<Clean> list = new ArrayList<Clean>();
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		
		try
		{
			if( sessionUser.getWorkField().equals( "03" ) )
			{
				object.setCharge( id );
			}
			else if( sessionUser.getWorkField().equals( "01" ) )
			{
				object.setComId( id );
			}
			
			list = CleanService.cleanList( CleanService.getList( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
		

	/**
	 * post save Cleans table
	 * 
	 * @param request
	 *        body of CleanObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( method = RequestMethod.POST )
	public void saveClean( @RequestBody Room object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL : "+object.getHusId()  );

		Clean clean = new Clean();
		HashMap<String, Object> map = new HashMap<String, Object>();
		User sessionUser = (User)SessionUtil.getLoginUser( request );
		//System.out.println("sessionUser.getUserId() : "+sessionUser.getUserId()+" / sessionUser.getComId() : "+sessionUser.getComId() + " object.getHusId() : " +object.getHusId());
		Room room = new Room();
		List<Room> list = new ArrayList<Room>();
		
		try
		{
			map.put( "comId", sessionUser.getComId() );
			map.put( "husId", object.getHusId() );
			House house = HouseService.getCheckItem( map );

			Contract con = new Contract();
			con.setComId( house.getComId());
			con.setYear( DateTime.getCurrentYear() );
			con.setLocal( house.getLocal() );
			con.setLiving( object.getLiving() );

			clean.setPhone( sessionUser.getPhone() );
			clean.setComId( sessionUser.getComId() );
			clean.setHusId( house.getHusId() );
			clean.setRoomNum( object.getRoomNum() );
			clean.setOrderer( sessionUser.getUserId() );			
			clean.setPay(  ContractService.getPayItem( con ) );
			clean.setLiving( object.getLiving() );
			clean.setIsIng( "100" );
			clean.setAddress( Constants.stringCode("LOCAL", house.getLocal() ) +" "+house.getAddress() );
			clean.setGatepass( house.getGatePass() );
			clean.setBepass( object.getBeforePass() );
			clean.setAfpass( object.getAfterPass() );
			clean.setHopeDate( object.getHopeDate() );
			
			CleanService.createClean( clean );
			

			/*
			 *  Twitter Sending Message  ==> UserAuth Admin			
			 */
			StringBuffer answer = new StringBuffer();
			answer.append( sessionUser.getComName()+" "+house.getHusName()+" "+clean.getRoomNum()+"호실 \n" );
			answer.append( Constants.stringCode( "LIVING", clean.getLiving() ) +" " );
			answer.append( Constants.stringCode( "ISING", clean.getIsIng() )+"입니다." );

			User user = new User();
			user.setUserAuth( "1" );
			List<User> Twitters = UserService.getList( user );

			TwitterSendMessage.processAnswer( answer.toString(), Twitters );
			
			room.setHusId( object.getHusId() );
			list = RoomService.roomList( RoomService.getList( room ) );
		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( list, request, response );
	}

	/**
	 * put update Cleans table
	 * 
	 * @param request
	 *        body of CleanObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT )
	public void updateClean( @PathVariable ( "id" ) long id, @RequestBody Clean object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		
		try
		{
			object.setId( id );

			CleanService.updateClean( object );
	
		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

	/**
	 * put update Cleans table
	 * 
	 * @param request
	 *        body of CleanObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}/{code}", method = RequestMethod.PUT )
	public void updateIngCode( @PathVariable ( "id" ) long id, @PathVariable ( "code" ) String code, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		Clean object = new Clean();
		
		try
		{
			object.setId( id );
			object.setIsIng( code );
			
			CleanService.updateClean( object );
	
		}
		catch ( CommonException e )
		{
			throw e;
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

	
	/**
	 * delete Cleans table
	 * 
	 * @param pk
	 *        value of Cleans table
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.DELETE )
	public void destroyClean( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{

		logger.error( "DELETE CALL" );
		try
		{
			Clean object = new Clean();
			object.setId( id );

			CleanService.deleteClean( object );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

}
