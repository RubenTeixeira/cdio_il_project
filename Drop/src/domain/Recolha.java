/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class Recolha implements TransaccaoPrateleira {

    private int idRecolha;
    private int idEntrega;
    private String dateOpen;
    private String dateClose;
    private int idToken;
    
    public Recolha() {
    }

    public Recolha(int idRecolha, int idToken) {
        this.idRecolha = idRecolha;
        this.idToken = idToken;
    }

    public Recolha(int idToken) {
        this.idToken = idToken;
    }
    
    /*Getters*/

    public int getIdRecolha() {return idRecolha;}
    public int getIdToken() {return idToken;}
    public int getIdEntrega() {return idEntrega;}
    public String getDateOpen() {return dateOpen;}
    public String getDateClose() {return dateClose;}

    /*Setters*/

    @Override
    public void setId(int idRecolha) {
        this.idRecolha = idRecolha;
    }
    
    @Override
    public void setDateOpen() {
        this.dateOpen = getDateStr();
    }
    
    @Override
    public void setDateClose() {
        this.dateClose = getDateStr();
    }
    
    private String getDateStr() {
        Date data = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
        return ft.format(data);
    }
    
    @Override
    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }
    
    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    @Override
    public boolean valido() {
        return idEntrega > 0 && idRecolha > 0 && idToken > 0 && dateOpen != null && dateClose != null;
    }

    @Override
    public String toString() {
        return "Recolha{" + "idRecolha=" + idRecolha + ", idEntrega=" + idEntrega + ", dateOpen=" + dateOpen + ", dateClose=" + dateClose + ", idToken=" + idToken + '}';
    }
    
}
