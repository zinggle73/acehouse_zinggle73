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

import com.ace.web.service.UserService;
import com.ace.web.vo.User;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.util.BCrypt;

@Controller
@Validated
@RequestMapping ( value = "/admin" )
public class AdminController extends AbstractController
{

	@Autowired
	private UserService UserService;

	
	/**
	 * get list Users table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Users table
	 * @throws CommonException
	 */

	@RequestMapping ( method = RequestMethod.GET  )
	public void listUsers( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.info( "GET ALL LIST CALL" );
		
		User object = new User();
		List<User> list = new ArrayList<User>();
		try
		{
			list = UserService.userList( UserService.getList( object ) );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		

		setResponse( list, request, response );

	}
	
	/**
	 * get list Users table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Users table
	 * @throws CommonException
	 */

	@RequestMapping ( value="/workfield/{field}", method = RequestMethod.GET  )
	public void workUsers( @PathVariable ( "field" ) String field, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.info( "GET WORKFIELD LIST CALL : " + field );
		
		User object = new User();
		List<User> list = new ArrayList<User>();
		try
		{
			object.setWorkField( field );
			list = UserService.getList( object );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		

		setResponse( list, request, response );

	}


	/**
	 * get item Users table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Users table
	 * @throws CommonException
	 */
	
	@RequestMapping ( value = "/{id}", method = RequestMethod.GET )
	public void fetchUser( @PathVariable ( "id" ) long id,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL" );
		User object = new User();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "id", id );
			
			object = UserService.userOne( UserService.getItem( map ) );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}

	/**
	 * get item Users table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Users table
	 * @throws CommonException
	 */
	
	@RequestMapping ( value = "/biz/{userId}", method = RequestMethod.GET )
	public void fetchBizUser( @PathVariable ( "userId" ) String userId,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET BIZ ITEM CALL : "+userId );
		User object = new User();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "userId", userId );
			
			object = UserService.userOne( UserService.getCheckItem( map ) );
			
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}
	 
	/**
	 * post save Users table
	 * 
	 * @param request
	 *        body of UserObject
	 * @return OK message
	 * @throws CommonException
	 */

	@RequestMapping (  method = RequestMethod.POST )
	public void saveUser( @RequestBody User object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL" );

		try
		{
			object.setPasswd( BCrypt.hashpw( object.getPasswd(), BCrypt.gensalt(10) ) );
			UserService.createUser( object );
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

	
   

	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT )
	public void updateUser( @PathVariable ( "id" ) long id, @RequestBody User object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		
		try
		{
			object.setId( id );

			UserService.updateUser( object );

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
	 * delete Users table
	 * 
	 * @param pk
	 *        value of Users table
	 * @return OK message
	 * @throws CommonException
	 */
  
	@RequestMapping ( value = "/{id}", method = RequestMethod.DELETE )
	//@Produces( MediaType.APPLICATION_JSON )
	public void destroyUser( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{

		logger.error( "DELETE CALL" );
		try
		{
			User object = new User();
			object.setId( id );

			UserService.deleteUser( object );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

}
