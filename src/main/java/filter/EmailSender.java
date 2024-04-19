package filter;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailSender {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.user", "testasyst1@gmail.com");
		properties.setProperty("mail.from", "testasyst1@gmail.com");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.setProperty("mail.session.mail.smtp.port", "465");
		
		properties.setProperty("mail.smtp.auth", "true");
	      try {
	    	 InternetAddress[] listeAdresse;
	    	 Session session = Session.getInstance(properties);	       
	         MimeMessage message = new MimeMessage(session);
	         message.setSubject("Msg");
	         message.setContent("N*V-Message !!", "text/html");
	         message.addRecipients(Message.RecipientType.TO, "nizigdiego@gmail.com");
	         Transport transport = null;
	         transport = session.getTransport("smtp");
			 transport.connect("testasyst1@gmail.com", "asyst@2018");
			 InternetAddress adresse = new InternetAddress("nizigdiego@gmail.com");
			 listeAdresse= new InternetAddress[1];
			 listeAdresse[0]=adresse;
	       
	         transport.sendMessage(message,listeAdresse);
	         if (transport != null) {
					transport.close();
				}
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	      
	
//		for(int i=0;i<10;i++)
//		System.out.println(generateRandomChars(
//	            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 15));
	}
	
	public static String generateRandomChars(String candidateChars, int length) {
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(candidateChars.charAt(random.nextInt(candidateChars
	                .length())));
	    }

	    return sb.toString();
	}

}
