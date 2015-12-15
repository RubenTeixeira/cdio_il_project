/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import utils.GoogleMail;

/**
 *
 * @author vascopinho
 */
public class Notice {
    private String email;
    private String token;
    
    

    public Notice() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public static void enviarEmail(String email, String token){
        GoogleMail notificacao = new GoogleMail();
        notificacao.setRecipientEmail(email);
        notificacao.setTitle("Token de Abertura");
        notificacao.setMessage("O token de abertura da prateleira é:" + token);
        notificacao.send();
        System.out.println("Email enviado para: " + email);
    }
    
    
}
