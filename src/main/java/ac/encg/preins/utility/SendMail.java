/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.encg.preins.utility;

import ac.encg.preins.entity.User;
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
    private static Properties prop = System.getProperties();
    
    
    private static void defineProperties(){
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
    }

    public static void sendRecalamation(String cne, String email, String text) {
        String to = "preins.encg@uiz.ac.ma";//change accordingly  
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
            message.setSubject(cne+ "-Réclamation");
            //  message.setText("Hello, this is example of sending email  ");
            message.setContent("Email:" + "<br/>"+email
                    +  "<br/><br/>"+text, "text/html");

            // Send message  
            Transport.send(message);
            System.out.println("message sent successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static void sendInscriptionConfirmationMail(String email, Long id) {
        String to = email;
        String from = "preins.encg@uiz.ac.ma";
        String host = "smtp.gmail.com";//or IP address  

        //Get the session object  
        defineProperties();
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
    
    public static void sendEmailConfirmationMail(User user, String token) {
        String to = user.getUsername();
        String from = "preins.encg@uiz.ac.ma";
        String host = "smtp.gmail.com";//or IP address  
     
        //Get the session object  
        defineProperties();
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
            message.setSubject("ENCG AGADIR (No replay)");
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part(the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            //  String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";

            String htmlText = "<img src=\"cid:image\" height=\"68\" width=\"250\">"
                    + "<br/>"
                    + "Bonjour,"
                    + "<br/>"
                    +"Vous recevez cet email parce que votre adresse email a été utiliser pour effectuer une pré-inscription à l'ENCG AGADIR."
                    + "<br/>"
                    +"Si vous n'êtes pas à l'origine de cette action, veillez ignorer cet email."
                    + "<br/>"
                    + "Sinon, prière de confirmer votre adresse email en cliquant sur le lien ci-dessous:"
                    + "<br/>"
    //TODO Modifier l'adresse locale
                    + "<a href='http://localhost:8080/preins/validation.xhtml?token="+ token
                    + "'>"
                    + "Lien de Confirmation</a>"
                    ;
           
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
    
    public static void sendPasswordUpdateMail(User user, String token) {
        String to = user.getUsername();
        String from = "preins.encg@uiz.ac.ma";
        String host = "smtp.gmail.com";//or IP address  
     
        //Get the session object  
        defineProperties();
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
            message.setSubject("Récupération du mot de passe Pré-inscition ENCG AGADIR (No replay)");
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");

            // first part(the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            //  String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";

            String htmlText = "<img src=\"cid:image\" height=\"68\" width=\"250\">"
                    + "<br/>"
                    + "Bonjour,"
                    + "<br/>"
                    +"Un utilisateur a demandé un nouveau mot de passe pour le compte suivant sur la plateforme de pré-inscription à l'ENCG AGADIR."
                    + "<br/>"
                    +"Si vous n'êtes pas à l'origine de cette action, veillez ignorer cet email."
                    + "<br/>"
                    + "Sinon, cliquez ici pour réinitialiser votre mot de passe:"
                    + "<br/>"
    //TODO Modifier l'adresse locale
                    + "<a href='http://localhost:8080/preins/update-password.xhtml?token="+ token
                    + "'>"
                    + "Mise à jours du mot de passe</a>"
                    ;
           
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
