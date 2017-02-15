package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpCorelogic {
	public static void sendMessage(ArrayList<String> data,
			HashMap<String, String> senderData,
			HashMap<String, String> recepientPacket) {
		// Get the session object
		InternetAddress[] ccrecepientAddress = null;
		InternetAddress[] bccrecepientAddress = null;
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
						return new PasswordAuthentication(
						// this is the email id
								senderData.get("email"),
								// this is the password
								senderData.get("password"));
					}
				});
		if (recepientPacket.get("CcRecepients") != null) {
			String ccrecep[] = stringtoArray(recepientPacket
					.get("CcRecepients"));
			String bccrecep[] = stringtoArray(recepientPacket
					.get("BCcRecepients"));
			if (ccrecep != null) {
				ccrecepientAddress = new InternetAddress[ccrecep.length];
			}
			if (bccrecep != null) {
				bccrecepientAddress = new InternetAddress[bccrecep.length];
			}
			int counter = 0;
			try {
			for (String recipient : ccrecep) {
				
					ccrecepientAddress[counter] = new InternetAddress(
							recipient.trim());
				
				counter++;
			}
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e2){
				e2.printStackTrace();
			}
			int counterbcc = 0;
			try {
			for (String recipient : bccrecep) {
				
					bccrecepientAddress[counterbcc] = new InternetAddress(
							recipient.trim());
				counterbcc++;
			}
			}catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			
		}

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(senderData.get("email")));// change
			// accordingly
			if(recepientPacket.get("recepientEmail")!= ""){
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recepientPacket.get("recepientEmail")));
			}
			message.setRecipients(Message.RecipientType.CC, ccrecepientAddress);
			message.setRecipients(Message.RecipientType.BCC,
					bccrecepientAddress);	
			// message.addRecipient(Message.RecipientType.CC, new
			// InternetAddress(
			// "yourOther@email.com"));
			message.setSubject(data.get(0)); // Get 0 means we are getting the
												// // subject
			message.setText(data.get(1)); // Get 1 means we are getting the
											// email
			Transport.send(message);

			System.out.println("Message sent successfully through Lynn");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String[] stringtoArray(String recepients) {
		if (recepients != null) {
			String[] recep = recepients.split(";");
			return recep;
		} else {
			return null;
		}
	}
}
