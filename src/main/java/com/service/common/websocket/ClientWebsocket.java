package com.service.common.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ClientWebsocket
{
	Session session = null;
	
	public ClientWebsocket( String sns_id ) throws URISyntaxException, DeploymentException, IOException
	{
		URI uri = new URI("ws://localhost:5080/echo/"+sns_id);
		ContainerProvider.getWebSocketContainer().connectToServer( this, uri );
	}

	@OnOpen
	public void processOpen( Session session )
	{
		this.session = session;
	}
	
	@OnMessage
	public void processMessage( String message )
	{	
		//System.out.println(Json.createReader( new StringReader(message) ).readObject().getString("message"));
		//Json.createReader( new StringReader(message) ).readObject().getString("message");
		//session.getBasicRemote().sendText( message );
	}
	
	@OnClose
	public void processClose( )
	{
		try
		{
			this.session.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void sendMessage( String message ) throws IOException
	{
		session.getBasicRemote().sendText( message );
	}
}
