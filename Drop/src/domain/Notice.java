/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import javax.mail.MessagingException;
import utils.GoogleMail;

/**
 *
 * @author vascopinho
 */
public class Notice {
    
    private String email;
    private String title;
    private String message;
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Constructor for empty object
     */
    public Notice() {
    }

    /*
    *   Setter methods
    */
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    /*
    *   Getter methods
    */
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }
    
    
    /**
     * Mail dispatch method
     * @return true if successfull, false otherwise
     */
    public boolean dispatchMail() {
        if (!valid())
            return false;
        GoogleMail mail = new GoogleMail();
        mail.setRecipientEmail(email);
        mail.setTitle("Anomaly Report from DropPoint");
        mail.setMessage(this.message);
        mail.send();
        System.out.println("Email enviado para: " + email);
        return true;
    }
    
    public static void enviarEmail(String email, String token){
        GoogleMail notificacao = new GoogleMail();
        notificacao.setRecipientEmail(email);
        notificacao.setTitle("Token de Abertura");
        notificacao.setMessage("O token de abertura da prateleira é:" + token);
        notificacao.send();
        System.out.println("Email enviado para: " + email);
    }
    
    /**
     * Validates notice
     * @return true if valid, false otherwise
     */
    private boolean valid() {
        return this.email.matches(EMAIL_PATTERN) && this.message.length() > 0;
    }
}
