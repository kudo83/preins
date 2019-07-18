/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.utility;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

/**
 *
 * @author kudo
 */
public class SendMail {

    public static void send() {
        String to = "ai.izimi@gmail.com";//change accordingly  
        String from = "preins.encg@uiz.ac.ma";//change accordingly  
        String host = "smtp.gmail.com";//or IP address  

        //Get the session object  
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("preins.encg@uiz.ac.ma", "ENCG@2019/");
            }
        });

        //compose the message  
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Ping (No replay)");
            //  message.setText("Hello, this is example of sending email  ");
            message.setContent("<h1>Hello, this is example of sending email  </h1>", "text/html");

            // Send message  
            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendConfirmationMail(String email, Long id) {
        String to = email;
        String from = "preins.encg@uiz.ac.ma";
        String host = "smtp.gmail.com";//or IP address  

        //Get the session object  
        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("preins.encg@uiz.ac.ma", "ENCG@2019/");
            }
        });

        //compose the message  
        try {
            // Create a  default MimeMessage object.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Image (No replay)");
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part(the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            //  String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";

            String htmlText = "<img src=\"cid:image\" height=\"68\" width=\"250\">"
                    + "<br/>"
                    + "Votre pré-inscrition à l'ENCG Agadir à bien été effectuer sous la référence :"
                    + "PRE2019-" + id 
                    + "<br/>"
                    + "<br/>"
                    + "Si vous n'avez pas encore imprimé votre reçu, veuillez vous connecter sur "
                    + "<a href='http://myencg.uiz.ac.ma/preins'>la plateforme de pré-inscription</a>, puis visualisez l'inscription, et cliquez sur le bouton \"Imprimer Reçu\"";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // second part (the image)
            messageBodyPart = new MimeBodyPart();

            String relativeWebPath = "resources/spark-layout/images/";
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            File file = new File(absoluteDiskPath, "logo.png");

            DataSource fds = new FileDataSource(file);

            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(messageBodyPart);

            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
