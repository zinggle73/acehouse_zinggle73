package com.service.common.exception;

public enum CommonError
{

	// Param Error Code
	PARAMETER_UUID_REQUIRED( 19000, "Parameter uuid - required." ), PARAMETER_UUID_INVALID( 19001,
			"Parameter uuid - bad format. It must be 10 length string." ),

	PAYLOAD_JSON_EMPTY( 59027, "Payload json data is empty." ), PAYLOAD_JSON_PARSING_ERROR( 59028,
			"Payload json parsing error." ), PAYLOAD_TYPE_UNSUPPORTED( 59029, "Payload type unsupported" ),

	// HTTP Error Code 500
	UNEXPECTED( 19900, "Unexpected error" ), JSON_SERIALIZE( 19901, "Could not serialize object to JSON format." ),

	;

	private final int code;
	private final String description;

	private CommonError( int code, String description )
	{
		this.code = code;
		this.description = description;
	}

	public int getCode()
	{
		return code;
	}

	public String getDescription()
	{
		return description;
	}

	@Override
	public String toString()
	{
		return code + ": " + description;
	}
}
