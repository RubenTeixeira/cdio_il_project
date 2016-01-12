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
public class PickUp implements CellTransaction {

    private int pickUpID;
    private int deliveryID;
    private String openedDate;
    private String closedDate;
    private int tokenID;
    
    public PickUp() {
    }

    /**
     * Constructor with the following parametres:
     * @param pickUpID
     * @param deliveryID
     * @param openedDate
     * @param tokenID 
     */
    public PickUp(int pickUpID, int deliveryID, String openedDate, int tokenID) {
        this.pickUpID = pickUpID;
        this.deliveryID = deliveryID;
        this.openedDate = openedDate;
        this.tokenID = tokenID;
    }

    /**
     * Constructor with the following parametres:
     * @param deliveryID
     * @param tokenID 
     */
    public PickUp(int deliveryID, int tokenID) {
        this.deliveryID = deliveryID;
        this.tokenID = tokenID;
    }
    
    /*Getters*/

    public int getPickUpID() {return pickUpID;}
    public int getTokenID() {return tokenID;}
    public int getDeliveryID() {return deliveryID;}
    public String getOpenedDate() {return openedDate;}
    public String getClosedDate() {return closedDate;}

    /*Setters*/

    @Override
    public void setId(int idRecolha) {
        this.pickUpID = idRecolha;
    }
    
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
    
    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }
    
    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    /**
     * Validates an instance of Pickup
     * @return 
     */
    @Override
    public boolean validate() {
        return deliveryID > 0 && tokenID > 0 && openedDate != null && closedDate != null;
    }

    /**
     * Description of an instance of Pickup
     * @return 
     */
    @Override
    public String toString() {
        return "PickUp{" + "pickUpID=" + pickUpID + ", deliveryID=" + deliveryID + ", openedDate=" + openedDate + ", closedDate=" + closedDate + ", tokenID=" + tokenID + '}';
    }
    
}
