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
public class Entrega implements TransaccaoPrateleira {
    
    private int idEntrega;
    private int idPrat;
    private String dateOpen;
    private String dateClose;
    private int idReservation;
    private int idToken;

    public Entrega(int idPrat, int idReservation, int idToken) {
        this.idPrat = idPrat;
        this.idReservation = idReservation;
        this.idToken = idToken;
    }

    public Entrega(int idReservation, int idToken) {
        this.idReservation = idReservation;
        this.idToken = idToken;
    }

    public Entrega() {
    }

    /*Getter methods*/
    public int getIdPrat() {return idPrat;}
    public String getDateOpen() {return dateOpen;}
    public String getDateClose() {return dateClose;}
    public int getIdReservation() {return idReservation;}
    public int getIdEntrega() {return idEntrega;}
    public int getIdToken() {return idToken;}

    /*Setter methods*/
    
    @Override
    public void setId(int idEntrega) {this.idEntrega = idEntrega;}
    public void setIdPrat(int idPrat) {this.idPrat = idPrat;}
    
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
    
    public void setIdReservation(int idReservation) {this.idReservation = idReservation;}

    @Override
    public void setIdToken(int idToken) {this.idToken = idToken;}

    @Override
    public boolean valido() {
        return idEntrega > 0 && idPrat > 0 && idReservation > 0 && idToken > 0 && dateOpen != null && dateClose != null;
    }

    @Override
    public String toString() {
        return "Entrega{" + "idEntrega=" + idEntrega + ", idPrat=" + idPrat + ", dateOpen=" + dateOpen + ", dateClose=" + dateClose + ", idReservation=" + idReservation + ", idToken=" + idToken + '}';
    }
    
}
