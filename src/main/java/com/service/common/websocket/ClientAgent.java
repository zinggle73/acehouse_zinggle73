package com.service.common.websocket;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.websocket.DeploymentException;

public class ClientAgent
{
	public static void main( String[] args )
	{
		ClientWebsocket socket = null;
		try
		{
			socket = new ClientWebsocket("agent1@SC");
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String message = "102";
			//System.out.println("demo enter your names");
			//while(true)
			//{
				//message = br.readLine();
				socket.sendMessage( message );
				socket.processClose();
			//}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		catch ( URISyntaxException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( DeploymentException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
