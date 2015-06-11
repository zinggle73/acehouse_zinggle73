package com.service.common.social.test;

import com.ace.web.vo.Mail;
import com.service.common.exception.CommonException;
import com.service.common.social.MailSender;

public class SendMailUpdate
{
	public static void main(String[] args) throws CommonException 
    {      
		 mailSendTest();
    }
	 
    public static void mailSendTest()
	{
		MailSender sender = new MailSender();

        Mail mail = new Mail();
        // 보내는이 메일주소
        mail.setFromUser( "ydyun21@naver.com" );
        // 받는이 이메일 주소는 반드시 ","로 구분해준다. String to = "받을 이메일 주소1,받을 이메일 주소2";
        mail.setToUser( "zinggle73@naver.com," );
        mail.setSubject( "이메일 발송 테스트4" );
        mail.setText( "안녕하세요 Java Email 발송 테스트입니다." );

        //MailSender emailSender = new MailSender(gmailProps);
        //MailSender emailSender = new MailSender(jamesProps);
        sender.sendEmail(mail);
        System.out.println("mail ok");
    }
}
