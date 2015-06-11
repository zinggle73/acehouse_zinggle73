package com.service.common.social.test;

import org.springframework.social.twitter.api.StatusDetails;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.service.common.exception.CommonException;


public class TwitterStatusUpdate 
{

	public static String TWITT_APP_ID 		=  "lPDXrKNO9Ck9j6wNrFp6P2FAQ"; 
	public static String TWITT_APP_SECRET 	=  "NnRv1sqVce1u0mDJX02rMUlWXaCmLjX2HI5Cdb2fKB3NM2L2sd"; 
	public static String TWITT_OAUTH_TOKEN 	= "2827545781-3xBI80lghzVaTymzsaXI58sMbvTfkBoeiQ3RzEb"; 
	public static String TWITT_OAUTH_SECRET   = "WZBsnfVnW1iduMujkAejxdX1FTbQRVo2CE4P12wkO2wji";
	
    private static Twitter twitter;
    
    public static void main(String[] args) throws CommonException 
    {
      
        processAnswerApproval();

    }
    
    public static void setTwitterInstance() 
    {
        
        twitter = new TwitterTemplate( TWITT_APP_ID, TWITT_APP_SECRET, TWITT_OAUTH_TOKEN, TWITT_OAUTH_SECRET );
    }
    
    public static void processAnswerApproval() throws CommonException
    {
        setTwitterInstance() ;

        String answer = "test twitter send message!!";

        if (!answer.startsWith("@")) 
        {
            String customerName = "ydyun21";
            answer = "@" + customerName + " " + answer;
        }
        
        System.out.println("answer : "+answer);
        
        
        try 
        {
        	StatusDetails statusDetails = new StatusDetails();
       
            System.out.println("statusDetails : "+statusDetails);
            
            Tweet tweet = twitter.timelineOperations().updateStatus( answer, statusDetails );

            System.out.println("tweet.getId() : "+tweet.getId());
        } 
        catch (Exception e) 
        {            
            e.getMessage();
        }

    }
    


}
