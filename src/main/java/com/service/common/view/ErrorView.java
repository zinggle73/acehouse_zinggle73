package com.service.common.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize ( include = Inclusion.NON_NULL )
public class ErrorView
{

	private long rcode;
	private String rmsg;

	public void setRcode( long rcode )
	{
		this.rcode = rcode;
	}

	public long getRcode()
	{
		return rcode;
	}

	public void setRmsg( String rmsg )
	{
		this.rmsg = rmsg;
	}

	public String getRmsg()
	{
		return rmsg;
	}
}
