package core;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpCorelogic {
	public void sendMessage(ArrayList<String> data,
			ArrayList<String> senderData, String recepientEmail) {
		// Get the session object
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
								senderData.get(0),
								// this is the password
								senderData.get(1));
					}
				});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderData.get(0)));// change
																	// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					recepientEmail));
			message.setSubject(data.get(0)); // Get 0 means we are getting the
												// subject
			message.setText(data.get(1)); // Get 1 means we are getting the
											// email
			Transport.send(message);

			System.out.println("Message sent successfully through Lynn");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
