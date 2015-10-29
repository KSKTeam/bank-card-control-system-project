package org.ksk_team.projects.CorporateCardControlProject.service.mail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

	private final static String DEFAULT_CONFIG_PATH = "src/main/resources/mail/mail.properties";

	private Properties mailConfig = new Properties();

	private static MailService instance = null;
	
	public static MailService getInstance() throws FileNotFoundException, IOException{
		if(instance == null){
			return instance = new MailService();
		}else{
			return instance;
		}
	}
	
	private MailService() throws FileNotFoundException, IOException {
		this(DEFAULT_CONFIG_PATH);
	}

	private MailService(String mailConfigPath) throws FileNotFoundException, IOException {
		mailConfig.load(new FileInputStream(mailConfigPath));
	}

	public void sendMessage(String to, String from, String subject, String body) {
		// Session session = Session.getInstance(mail_config);
		// Get the default Session object.
		Session session = Session.getInstance(mailConfig, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailConfig.getProperty("mail.smtp.login"),
						mailConfig.getProperty("mail.smtp.password"));
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(body);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void sendHTMLMessage(String to, String from, String subject, String body) {

		// Session session = Session.getInstance(mail_config);
		// Get the default Session object.
		Session session = Session.getInstance(mailConfig, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailConfig.getProperty("mail.smtp.login"),
						mailConfig.getProperty("mail.smtp.password"));
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Send the actual HTML message, as big as you like
			message.setContent(body, "text/html");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MailService mailService = null;
		try {
			mailService = new MailService();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailService.sendHTMLMessage("karichkovsky@yandex.com", "jack.karichkovskiy@gmail.com", "This is the Subject Line!",
				"<h1>This is actual message</h1>");
	}
}
