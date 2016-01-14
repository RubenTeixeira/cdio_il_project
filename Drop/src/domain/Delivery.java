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
    private String notificationEmail;
    private int reservationID;
    private int courierTokenID;
    private int assistantTokenID;

    /**
     * Constructor with cellID, reservationID and tokenID as parametres
     * @param cellID
     * @param reservationID
     * @param tokenID 
     */
    public Delivery(int cellID, int reservationID, int tokenID) {
        this.cellID = cellID;
        this.reservationID = reservationID;
        this.courierTokenID = tokenID;
    }

    /**
     * Constructor with reservationID and tokenID as parametres
     * @param reservationID
     * @param tokenID 
     */
    public Delivery(int reservationID, int tokenID) {
        this.reservationID = reservationID;
        this.courierTokenID = tokenID;
    }

    /**
     * Constructor with no parametres
     */
    public Delivery() {
    }

    /*Getter methods*/
    public int getCellID() {
        return cellID;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getDeliveryID() {
        return deliveryID;
    }

    public int getCourierTokenID() {
        return courierTokenID;
    }

    public int getAssistantTokenID() {
        return assistantTokenID;
    }   

    private String getDateStr() {
        Date data = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return ft.format(data);
    }

    public String getNotificationEmail() {
        return notificationEmail;
    }


    /*Setter methods*/
    @Override
    public void setId(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    @Override
    public void setDateOpen() {
        this.openedDate = getDateStr();
    }

    @Override
    public void setDateClose() {
        this.closedDate = getDateStr();
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setCourierTokenID(int courierTokenID) {
        this.courierTokenID = courierTokenID;
    }

    public void setAssistantTokenID(int assistantTokenID) {
        this.assistantTokenID = assistantTokenID;
    }

    public void setNotificationEmail(String notificationEmail) {
        this.notificationEmail = notificationEmail;
    }

    /**
     * Validates a Delivery instance
     * @return 
     */
    @Override
    public boolean validate() {
        return cellID > 0 && reservationID > 0 && courierTokenID > 0 && openedDate != null && closedDate != null;
    }

    /**
     * Description of a Delivery instance
     * @return 
     */
    @Override
    public String toString() {
        return "Delivery{" + "deliveryID=" + deliveryID + ", cellID=" + cellID + ", openedDate=" + openedDate + ", closedDate=" + closedDate + ", reservationID=" + reservationID + ", tokenID=" + courierTokenID + '}';
    }

}
