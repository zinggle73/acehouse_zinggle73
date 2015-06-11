package com.service.common.social;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ace.web.vo.Mail;

public class MailSender
{
	private static Properties props;
    
    public MailSender() 
    {
    	Properties gmailProps = new Properties();
        gmailProps.put("mail.smtp.starttls.enable", "true");
        gmailProps.put("mail.smtp.host", "smtp.gmail.com");
        gmailProps.put("mail.smtp.auth", "true");
        gmailProps.put("mail.smtp.port", "587");
        
        MailSender.props = gmailProps;
    }
     
    public static void sendEmail( Mail mail ) 
    {
 	        
        EmailAuthenticator authenticator = new EmailAuthenticator();
        Session session = Session.getInstance( props, authenticator );
         
        try 
        {
            Message msg = new MimeMessage( session );
             
            msg.setFrom( new InternetAddress(mail.getFromUser()) );
            msg.setRecipients( Message.RecipientType.TO, InternetAddress.parse( mail.getToUser() ) );
            msg.setSubject( mail.getSubject() );
            msg.setContent( mail.getText(), "text/html; charset=UTF-8" );
            msg.setSentDate( new Date() );
            Transport.send( msg );
        } 
        catch (MessagingException e)
        { 
            e.printStackTrace();
        }
 
    }
     
   
}
