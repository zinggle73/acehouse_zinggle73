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

import com.ace.web.service.HouseService;
import com.ace.web.vo.House;
import com.service.common.AbstractController;
import com.service.common.Constants;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.util.StringUtil;

@Controller
@Validated
@RequestMapping ( value = "/admin/house" )
public class AdminHouseController extends AbstractController
{

	@Autowired
	private HouseService HouseService;

	
	/**
	 * get list Houses table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Houses table
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{comId}", method = RequestMethod.GET )
	public void listHouses( @PathVariable ( "comId" ) String comId, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );
		House object = new House();
		List<House> list = new ArrayList<House>();
		
		try
		{
			object.setComId( comId );
			list = HouseService.houseList( HouseService.getList( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}

	

	/**
	 * get item Houses table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Houses table
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{comId}/{id}", method = RequestMethod.GET )
	public void fetchHouse( @PathVariable ( "comId" ) String comId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL : "+id );
		House object = new House();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "comId", comId );
			map.put( "id", id );
			
			object = HouseService.houseOne( HouseService.getItem( map ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}

		
	
	/**
	 * post save Houses table
	 * 
	 * @param request
	 *        body of HouseObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{comId}", method = RequestMethod.POST )
	public void saveHouse( @PathVariable ( "comId" ) String comId, @RequestBody House object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL" );
		List<House> list = new ArrayList<House>();
		House husObj = new House();
		
		try
		{
			object.setComId( comId );
			object.setHusId( StringUtil.strHousehusId( HouseService.getMaxHouseId() ) );			
			object.setIsIng( "Y" );
			
			HouseService.createHouse( object );
			
			husObj.setComId( comId );
			list = HouseService.houseList( HouseService.getList( husObj ) );

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
	 * put update Houses table
	 * 
	 * @param request
	 *        body of HouseObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{comId}/{id}", method = RequestMethod.PUT )
	public void updateHouse( @PathVariable ( "comId" ) String comId, @PathVariable ( "id" ) long id, @RequestBody House object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		List<House> list = new ArrayList<House>();
		House husObj = new House();
		
		try
		{		
			object.setComId( comId );
			object.setId( id );			
			
			HouseService.updateHouse( object );
			
			husObj.setComId( comId );
			list = HouseService.houseList( HouseService.getList( husObj ) );

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

	@RequestMapping ( value = "/{isIng}", method = RequestMethod.PUT )
	public void updateIsIng(  @PathVariable ( "isIng" ) String isIng, @RequestBody House object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL : "+isIng );
		House husObj = new House();
		List<House> list = new ArrayList<House>();
		
		try
		{
			String is = "";
			if( isIng.equals( "Y" ) )
			{
				is = "N";
			}
			else  is = "Y";
			
			object.setIsIng( is );
			HouseService.updateHouse( object );
			
			husObj.setComId( object.getComId() );
			list = HouseService.houseList( HouseService.getList( husObj ) );

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
	 * delete Companys table
	 * 
	 * @param pk
	 *        value of Companys table
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping (  value = "/{comId}/{id}", method = RequestMethod.DELETE )
	public void destroyHouse( @PathVariable ( "comId" ) String comId, @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "DELETE CALL" );
		List<House> list = new ArrayList<House>();
		House husObj = new House();
		
		try
		{
			House object = new House();
			object.setComId( comId );
			object.setId( id );
			object.setIsIng( "N" );

			HouseService.updateHouse( object );
			
			husObj.setComId( comId );
			list = HouseService.houseList( HouseService.getList( husObj ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( list, request, response );
	}
}
