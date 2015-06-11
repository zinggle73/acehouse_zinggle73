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

import com.ace.web.service.CompanyService;
import com.ace.web.vo.Company;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;
import com.service.common.util.StringUtil;

@Controller
@Validated
@RequestMapping ( value = "/admin/company" )
public class AdminCompanyController extends AbstractController
{

	@Autowired
	private CompanyService CompanyService;


	/**
	 * get list Companys table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Companys table
	 * @throws CommonException
	 */
	@RequestMapping ( method = RequestMethod.GET )
	public void listCompanys( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );
		List<Company> list = new ArrayList<Company>();
		Company object = new Company();
		
		try
		{
			//object.setSort( "01" );
			list = CompanyService.comList( CompanyService.getSelectList( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}

	/**
	 * get list Companys table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Companys table
	 * @throws CommonException
	 */
	@RequestMapping ( value="/empty", method = RequestMethod.GET )
	public void emptyRoomCompanys( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );
		List<Company> list = new ArrayList<Company>();
		Company object = new Company();
		
		try
		{
			object.setSort( "01" );
			list = CompanyService.comList( CompanyService.getEmptyRoomCompanys( object ) );

		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}

		setResponse( list, request, response );

	}
	
	/**
	 * get item Companys table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Companys table
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.GET )
	public void fetchCompany( @PathVariable ( "id" ) long id,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL : " + id);
		Company object = new Company();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "id", id );
			
			object = CompanyService.comOne( CompanyService.getItem( map ) );

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
	
	@RequestMapping ( value = "/biz/{comId}", method = RequestMethod.GET )
	public void fetchBizCompany( @PathVariable ( "comId" ) String comId,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET BIZ ITEM CALL : "+comId );
		Company object = new Company();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "comId", comId );
			
			object = CompanyService.comOne( CompanyService.getCheckItem( map ) );
			
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( object, request, response );
	}
	
	/**
	 * post save Companys table
	 * 
	 * @param request
	 *        body of CompanyObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( method = RequestMethod.POST )
	public void saveCompany( @RequestBody Company object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL : "+object.getComId() );
		List<Company> list = new ArrayList<Company>();
		Company company = new Company();
		
		try
		{
			if( object.getComId() == null )
			{
				object.setComId( StringUtil.strCompanyComId( CompanyService.getMaxComId() ) );
			}
			
			CompanyService.createCompany( object );
			
			list = CompanyService.comList( CompanyService.getSelectList( company ) );
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
	 * put update Companys table
	 * 
	 * @param request
	 *        body of CompanyObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT )
	public void updateCompany( @PathVariable ( "id" ) long id, @RequestBody Company object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL" );
		List<Company> list = new ArrayList<Company>();
		Company company = new Company();
		
		try
		{
			object.setId( id );

			CompanyService.updateCompany( object );

			list = CompanyService.comList( CompanyService.getSelectList( company ) );
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
	@RequestMapping ( value = "/{id}", method = RequestMethod.DELETE )
	public void destroyCompany( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{

		logger.error( "DELETE CALL" );
		List<Company> list = new ArrayList<Company>();
		Company company = new Company();
		
		try
		{
			Company object = new Company();
			object.setId( id );

			CompanyService.deleteCompany( object );
			
			list = CompanyService.comList( CompanyService.getSelectList( company ) );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( list, request, response );
	}

}
