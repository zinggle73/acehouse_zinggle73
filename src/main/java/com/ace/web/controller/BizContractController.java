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

import com.ace.web.service.CodezService;
import com.ace.web.service.ContractService;
import com.ace.web.vo.Codez;
import com.ace.web.vo.Contract;
import com.service.common.AbstractController;
import com.service.common.exception.CommonError;
import com.service.common.exception.CommonException;

@Controller
@Validated
@RequestMapping ( value = "/biz/contract" )
public class BizContractController extends AbstractController
{

	@Autowired
	private ContractService ContractService;

	@Autowired
	private CodezService CodezService;

	/**
	 * get list Companys table
	 * 
	 * @param request
	 *        parameter
	 * @return list that is searched from Companys table
	 * @throws CommonException
	 */
	@RequestMapping ( method = RequestMethod.GET )
	public void listContracts( HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET LIST CALL" );
		List<Contract> list = new ArrayList<Contract>();
		Contract object = new Contract();
		
		try
		{
			list = ContractService.contractList( ContractService.getList( object ) );

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
	public void fetchContract( @PathVariable ( "id" ) long id,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET ITEM CALL : " + id);
		Contract object = new Contract();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "id", id );
			
			object = ContractService.contractOne( ContractService.getItem( map ) );

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
	public void fetchBizContract( @PathVariable ( "comId" ) String comId,	HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "GET BIZ ITEM CALL : "+comId );
		Contract object = new Contract();
		
		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "comId", comId );
			
			object = ContractService.contractOne( ContractService.getItem( map ) );
			
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
	public void saveContract( @RequestBody Contract object, HttpServletRequest request, HttpServletResponse response )	throws CommonException
	{
		logger.error( "POST SAVE CALL : "+object.getComId() );
		


		try
		{
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put( "groupCode", "A0005" );
			map.put( "codeOr", "Y" );
			
			List<Codez> list = CodezService.getDetailCodes( map );
			
			if( object.getLocal().equals( "00" ) )
			{
				for( Codez code	: list )
				{
					if( !code.getDetailCode().equals( "00" ) )
					{
						object.setLocal( code.getDetailCode() );
						object.setIsYn( "Y" );
						ContractService.createContract( object );
					}
				}
			}
			else
			{
				object.setIsYn( "Y" );
				ContractService.createContract( object );
			}
			
			
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
	 * put update Companys table
	 * 
	 * @param request
	 *        body of CompanyObject
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.PUT )
	public void updateContract( @PathVariable ( "id" ) long id, @RequestBody Contract object, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{
		logger.error( "PUT UPDATE CALL : "+id );
		
		try
		{
			object.setId( id );

			ContractService.updateContract( object );

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
	 * delete Companys table
	 * 
	 * @param pk
	 *        value of Companys table
	 * @return OK message
	 * @throws CommonException
	 */
	@RequestMapping ( value = "/{id}", method = RequestMethod.DELETE )
	public void destroyContract( @PathVariable ( "id" ) long id, HttpServletRequest request, HttpServletResponse response ) throws CommonException
	{

		logger.error( "DELETE CALL" );
		try
		{
			Contract object = new Contract();
			object.setId( id );

			ContractService.deleteContract( object );
		}
		catch ( Exception e )
		{
			logger.warn( e.getMessage() );
			throw new CommonException( CommonError.UNEXPECTED, e );
		}
		
		setResponse( "OK", request, response );
	}

}
