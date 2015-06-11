package com.service.common.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint ( "/echo/{sns_id}" )
public class ServerWebsocket
{
	static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
	/**
	 * @OnOpen allows us to intercept the creation of a new session. The session
	 *         class allows us to send data to the user. In the method onOpen,
	 *         we'll let the user know that the handshake was successful.
	 */
	@OnOpen
	public void onOpen( Session session, @PathParam("sns_id") String sns_id )
	{
		System.out.println( sns_id + " has opened a connection open? : "+session.getMaxIdleTimeout() );
		
		session.getUserProperties().put( "agent_id", sns_id );
		users.add( session );
		//session.getBasicRemote().sendText( buildJsonData(sns_id, "Connection Established") );	
	}
	
	
	/**
	 * When a user sends a message to the server, this method will intercept the
	 * message and allow us to react to it. For now the message is read as a
	 * String.
	 */
	@OnMessage
	public void onMessage( String message, Session session )
	{
		
		try
		{						
			String sns_id = (String)session.getUserProperties().get("agent_id");
			Iterator<Session> iterator = users.iterator();
			System.out.println( "session size : "+users.size() +"!!!!!! : "+message);
			while (iterator.hasNext()) 
			{			
				Session itersession = iterator.next();
				String agent_id = (String)itersession.getUserProperties().get( "agent_id" );
				String[] sns = sns_id.split( "@" );
				String[] agent = agent_id.split( "@" );
				System.out.println( "session size sns_id : "+sns_id +"  / agent_id "+agent_id );
				if( agent[0].equals(sns[0]) )
				{		
					if( agent[1].equals( "BC" ) )
					{						
						//itersession.getBasicRemote().sendText( buildJsonData(agent[0], message) );
						itersession.getBasicRemote().sendText(  message );
					}
				}
			}
	
		}
		catch ( IOException ex )
		{
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * The user closes the connection.
	 * 
	 * Note: you can't send messages to the client from this method
	 */
	@OnClose
	public void onClose( Session session )
	{
		System.out.println( (String)session.getUserProperties().get("agent_id") + " has connection closed" );
		users.remove( session );	
	}
	
	/*
	private String buildJsonData( String sns_id, String message )
	{
		JsonObject jsonObj = Json.createObjectBuilder().add("message", sns_id+" : "+message).build();
		StringWriter sWriter = new StringWriter();
		try (JsonWriter jWriter = Json.createWriter( sWriter )) {jWriter.write( jsonObj );}
		return sWriter.toString();
	}
*/

}
