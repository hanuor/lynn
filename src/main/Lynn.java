package main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Lynn {

	public void sendMessage(String msg){
		 String to="shanjohridev@gmail.com";//change accordingly  
		  
		  //Get the session object  
		  Properties props = new Properties();  
		  props.put("mail.smtp.host", "smtp.gmail.com");  
		  props.put("mail.smtp.socketFactory.port", "465");  
		  props.put("mail.smtp.socketFactory.class",  
		            "javax.net.ssl.SSLSocketFactory");  
		  props.put("mail.smtp.auth", "true");  
		  props.put("mail.smtp.port", "465");  
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication("shantanu@internshala.com","hanuor11");//change accordingly  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress("shantanu@internshala.com"));//change accordingly  
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		   message.setSubject("Hello");  
		   message.setText(msg);  
		     
		   //send message  
		   Transport.send(message); 
		  
		  
		   System.out.println("message sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   

		
	}
	public static void main(String[] args) {  
		  
				 } 
}
