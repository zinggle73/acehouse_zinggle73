package com.service.common.social;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticator extends Authenticator 
{
     
    private String id; 
    private String pw; 

    public EmailAuthenticator()
    {
        this.id = "zinggle73";
        this.pw = "mdvqxesdeatcnpyk";
    }
     
    public EmailAuthenticator(String id, String pw) 
    {
        this.id = id;
        this.pw = pw;
    }

    protected PasswordAuthentication getPasswordAuthentication() 
    {
        return new PasswordAuthentication(id, pw);
    }

}
