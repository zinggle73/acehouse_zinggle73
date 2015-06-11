package com.service.common.social;

import java.util.List;

import org.springframework.social.twitter.api.StatusDetails;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.ace.web.vo.User;
import com.service.common.Constants;


public class TwitterSendMessage 
{

    private static Twitter twitter;
    
    public static void processAnswer( String answer, List<User> twitterUser ) 
    {
    	twitter = new TwitterTemplate( Constants.TWITT_APP_ID, Constants.TWITT_APP_SECRET, Constants.TWITT_OAUTH_TOKEN, Constants.TWITT_OAUTH_SECRET );

        try 
        {
        	for( User twitt : twitterUser )
        	{
        		String twittAnswer = "";
        		
        		if ( !twitt.getTwitt().startsWith("@") ) 
                {
        			twittAnswer = "@" + twitt.getTwitt() + " " + answer;
                }
            	else
            	{
            		twittAnswer = twitt.getTwitt() + " " + answer;
            	}
            	
            	StatusDetails statusDetails = new StatusDetails();

            	Tweet tweet = twitter.timelineOperations().updateStatus( twittAnswer, statusDetails );
            	//System.out.println("tweet id : "+tweet.getId() + " / "+twitt.getTwitt());
        	}        	

        } 
        catch (Exception e) 
        {            
            e.getMessage();
        }


    }
    
}
