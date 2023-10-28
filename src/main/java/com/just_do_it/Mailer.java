package com.just_do_it;

import java.util.Properties;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

public class Mailer {
	
	public static void send_email(String email, String subject, String message) 
	
	{
		
		String username = "msrtest1999@gmail.com";
		
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "localhost");
		
		Session session = Session.getDefaultInstance(props);
		
		try {
			
			MimeMessage mimeMessage = new MimeMessage(session);
			
			mimeMessage.setFrom(new InternetAddress(email));
			
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
			
			mimeMessage.setSubject(subject);
			
			mimeMessage.setText(message);
			
			Transport.send(mimeMessage);
			
		} catch (MessagingException ex) {
			
			System.err.println(ex.getMessage());
			
		}
		
	}

}
