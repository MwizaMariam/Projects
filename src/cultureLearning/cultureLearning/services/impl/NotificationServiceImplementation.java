package cultureLearning.cultureLearning.services.impl;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service("NotificationService")
public class NotificationServiceImplementation implements NotificationService {

	public void sendEmail(String email, String subject, String text) throws Exception {
		final String username = System.getenv("SMTP_USERNAME");
        final String password = System.getenv("SMTP_PASSWORD");

        Properties prop = new Properties();
		prop.put("mail.smtp.host", System.getenv("SMTP_SERVER"));
        prop.put("mail.smtp.port", System.getenv("SMTP_PORT"));
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mwizagatera97@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }  
	}
	
	public void sendEmail(String email, String subject, String text, String attachmentPath) throws Exception {
		final String username = "mwizagatera97@gmail.com";
        final String password = "mamu1997";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); 

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mwizagatera97@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
//            message.setText(text);
            
            MimeBodyPart body = new MimeBodyPart();
            body.setContent(text, "text/html");
            
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(attachmentPath);
            
            Multipart multiPart = new MimeMultipart();
            multiPart.addBodyPart(attachment);
            multiPart.addBodyPart(body);
            
            message.setContent(multiPart);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }  
	}

}
