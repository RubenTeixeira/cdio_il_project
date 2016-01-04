/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author MarcoSousa
 */
public class MaintenancePickup {

    private int maintenacePickupId;
    private String dateOpen;
    private String dateClose;
    private int deliveryId;
    private int tokenId;
    private String filePath;

    public MaintenancePickup() {
    }

    /*Getters*/
    public int getMaintenacePickupId() {
        return this.maintenacePickupId;
    }

    public String getDateOpen() {
        return this.dateOpen;
    }

    public String getDateClose() {
        return this.dateClose;
    }

    public int getDeliveryId() {
        return this.deliveryId;
    }

    public int getTokenId() {
        return this.tokenId;
    }

    public String getFilePath() {
        return filePath;
    }
    
    /*Setters*/
    public void setMaintenacePickupId(int maintenacePickupId) {
        this.maintenacePickupId = maintenacePickupId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public boolean validate() {
        return maintenacePickupId > 0 
                && deliveryId > 0 
                && tokenId > 0 
                && dateOpen != null 
                && dateClose != null;
    }

    @Override
    public String toString() {
        return "Recolha de Manutenção: \n"
                + "ID: " + maintenacePickupId
                + "\nEntrega ID: " + deliveryId
                + "\nData Abretura Prateleira: " + dateOpen
                + "\nData Fecho Prateleira: " + dateClose
                + "\nToken ID: " + tokenId;
    }
}
