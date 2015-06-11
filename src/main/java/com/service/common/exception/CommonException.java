package com.service.common.exception;

public class CommonException extends Exception
{

	private static final long serialVersionUID = 926844258853959059L;

	private CommonError error;
	private Exception exception;

	public CommonException( CommonError error )
	{
		this( error, null );
	}

	public CommonException( CommonError error, Exception exception )
	{
		super( error.getDescription(), exception );
		this.error = error;
		this.exception = exception;
	}

	public CommonError getError()
	{
		return error;
	}

	public Exception getException()
	{
		return exception;
	}
}
