package org.example;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;


public class App 
{
    public static void main( String[] args )
    {
        String username = "";
        String password = "";// gere uma senha de app (necessario habilitar 2FA na conta do google) e substitua aqui
        Properties props = new Properties();

        /* Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);
        try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setSubject("Meu titulo 123");

        Address[] toUser = InternetAddress
                    .parse(username);

            message.setRecipients(Message.RecipientType.TO, toUser);



        String msg = "Minha mensagem";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();

            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        }
}
