package gestdb.BugSegnalator;
        
import gestdb.Graphics.MessaggiGrafici;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Properties;
import java.util.regex.Pattern;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/*
 * @param  String da   : stringa contenente Email destinatario
 * @param  String body : corpo dell'Email
 * 
 **/

public class Email {
    final String username="spotted.spiaggetta@gmail.com";
    protected final String password="niccoloferrigno";
    private String da;
    private String body;
    private File a;

    public Email(String utente, String body, File allegato)
    {
     da=utente;
     this.body=body;
     a=allegato;
    }
    
    public Email(String utente, String body)
    {
     da=utente;
     this.body=body;
    }
    
    public void sendWithAttached(String from, String body, File allegato) throws FileNotFoundException
    {
     Properties prop=new Properties();
     prop.put("mail.smtp.host", "smtp.gmail.com");
     prop.put("mail.smtp.port", "465");
     prop.put("mail.smtp.auth", "true");
     prop.put("mail.smtp.socketFactory.port", "465");
     prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
     try{
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
         message.setSubject("Problema al programma inviato da:  "+from);
         message.setText(body);
         Multipart multipart = new MimeMultipart();
         BodyPart corpo= new MimeBodyPart();
         corpo.setText(body);
         multipart.addBodyPart(corpo);
         BodyPart attaccamento  = new MimeBodyPart();
         DataOutputStream dop=new DataOutputStream(new FileOutputStream(allegato));
         attaccamento.setDataHandler(new DataHandler(new FileDataSource(allegato)));//
         attaccamento.setFileName(allegato.getName());//
         multipart.addBodyPart (attaccamento);
         message.setContent(multipart);
         Transport.send(message);
         MessaggiGrafici.SegnalazioneInviata();
        } 
      catch (MessagingException e) {
            MessaggiGrafici.SegnalazioneNonInviata(e);
            e.printStackTrace();
      }
    }
    
    public void sendWithOutAttached(String from, String body)
    {
     Properties prop=new Properties();
     prop.put("mail.smtp.host", "smtp.gmail.com");
     prop.put("mail.smtp.port", "465");
     prop.put("mail.smtp.auth", "true");
     prop.put("mail.smtp.socketFactory.port", "465");
     prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
     Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
     try{
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
         message.setSubject("Problema al programma inviato da:  "+from);
         message.setText(body);
         Transport.send(message);
         MessaggiGrafici.SegnalazioneInviata();
        } 
      catch (MessagingException e) {
            MessaggiGrafici.SegnalazioneNonInviata(e);
            e.printStackTrace();
      }
    }
    
    public static boolean ValidaEmail(String email){
       final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
       return EMAIL_REGEX.matcher(email).matches();  
    }
    
    public static boolean ValidaFileAllegato(String FileName){
      boolean esito=false;
      switch(FileName){
            case ".log": esito=true;
                         break;
                             
            case ".png": esito=true;
                         break;  
                                     
            case ".jpeg": esito=true;
                          break;  
      }
     return esito;
    }
    
}

