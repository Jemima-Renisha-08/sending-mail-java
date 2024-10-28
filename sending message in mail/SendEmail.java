import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    public static void main(String[] args) {

        // Sender's email ID and password
        final String senderEmail = "your-email@gmail.com";
        final String senderPassword = "your-email-password";

        // Receiver's email ID
        String receiverEmail = "receiver-email@example.com";

        // SMTP server information
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        // Set up mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", String.valueOf(smtpPort));

        // Get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(senderEmail));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

            // Set Subject
            message.setSubject("Test Email from Java Program");

            // Set the content of the email
            message.setText("Hello,\n\nThis is a test email sent from a Java program!\n\nBest regards,\nYour Name");

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
