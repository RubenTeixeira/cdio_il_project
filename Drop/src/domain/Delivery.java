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
public class Delivery implements CellTransaction {
    
    private int deliveryID;
    private int cellID;
    private String openedDate;
    private String closedDate;
    private int reservationID;
    private int tokenID;

    public Delivery(int cellID, int reservationID, int tokenID) {
        this.cellID = cellID;
        this.reservationID = reservationID;
        this.tokenID = tokenID;
    }

    public Delivery(int reservationID, int tokenID) {
        this.reservationID = reservationID;
        this.tokenID = tokenID;
    }

    public Delivery() {
    }

    /*Getter methods*/
    public int getCellID() {return cellID;}
    public String getOpenedDate() {return openedDate;}
    public String getClosedDate() {return closedDate;}
    public int getReservationID() {return reservationID;}
    public int getDeliveryID() {return deliveryID;}
    public int getTokenID() {return tokenID;}

    /*Setter methods*/
    @Override
    public void setId(int deliveryID) {this.deliveryID = deliveryID;}
    public void setCellID(int cellID) {this.cellID = cellID;}
    
    @Override
    public void setDateOpen() {
        this.openedDate = getDateStr();
    }
    
    @Override
    public void setDateClose() {
        this.closedDate = getDateStr();
    }
    
    private String getDateStr() {
        Date data = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
        return ft.format(data);
    }
    
    public void setReservationID(int reservationID) {this.reservationID = reservationID;}

    @Override
    public void setTokenID(int tokenID) {this.tokenID = tokenID;}

    @Override
    public boolean validate() {
        return cellID > 0 && reservationID > 0 && tokenID > 0 && openedDate != null && closedDate != null;
    }

    @Override
    public String toString() {
        return "Delivery{" + "deliveryID=" + deliveryID + ", cellID=" + cellID + ", openedDate=" + openedDate + ", closedDate=" + closedDate + ", reservationID=" + reservationID + ", tokenID=" + tokenID + '}';
    }
    
}
