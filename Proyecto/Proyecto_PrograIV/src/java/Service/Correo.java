/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Karlenypc
 */
public class Correo {
    
    public void enviarCorreo(String Destinatario, String Asunto, String Mensaje) {
        
        System.out.println("Destinatario:" + Destinatario + " " + Asunto + " ");
        
        //propiedades
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com"); //host
        propiedad.setProperty("mail.smtp.starttls.enable", "true"); //tls = true
        propiedad.setProperty("mail.smtp.port", "587"); //puerto
        propiedad.setProperty("mail.smtp.auth", "true"); //autenticacion = true
        
        Session sesion = Session.getDefaultInstance(propiedad);
        
        String correoEnvia = "tiendagrupo4ulatina@gmail.com";
        String contrasenna = "Tienda123+"; 
        //String destinatario = "";
        //String asunto = "Factura Electronica Prueba1";
        //String mensaje = "Esto es una prueba desde Java";
        
        //creacion del cuerpo/objeto que contendra el msj y correo desde donde se envia etc..
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(Destinatario)); //ruta donde se enviara el msj
            mail.setSubject(Asunto);
            mail.setText(Mensaje);
            
            Transport transporte = sesion.getTransport("smtp"); //servidor salida
            transporte.connect(correoEnvia, contrasenna);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            
            System.out.println("Correo Enviado");
            
        } catch (AddressException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Correo.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}