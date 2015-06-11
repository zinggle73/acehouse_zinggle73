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

@Controller
@Validated
@RequestMapping ( value = "/biz/company" )
public class BizCompanyController extends AbstractController
{

	@Autowired
	private CompanyService CompanyService;

	@RequestMapping ( value = "/{comId}",  method = RequestMethod.GET )
	public void listCompanys( @PathVariable ( "comId" ) String comId, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );
		List<Company> list = new ArrayList<Company>();
		Company object = new Company();
		
		try
		{
			object.setComId( comId );
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
	 * get item Users table
	 * 
	 * @param request
	 *        parameter
	 * @return get item from Users table
	 * @throws CommonException
	 */

	@RequestMapping ( value = "/fetch/{id}", method = RequestMethod.GET )
	public void fetchBizCompany( @PathVariable ( "id" ) String id,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET BIZ ITEM CALL : "+id );
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
			
			company.setComId( object.getComId() );
			list = CompanyService.comList( CompanyService.getSelectList( object ) );

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

	
}
