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

    private int maintenancePickupId;
    private String dateOpen;
    private String dateClose;
    private int deliveryId;
    private int tokenId;
    private String filePath;

    public MaintenancePickup(int maintenancePickupId, String dateOpen, String dateClose, int deliveryId, int tokenId, String filePath) {
        this.maintenancePickupId=maintenancePickupId;
        this.dateOpen=dateOpen;
        this.dateClose=dateClose;
        this.deliveryId=deliveryId;
        this.tokenId=tokenId;
        this.filePath=filePath;
    }
    
    public MaintenancePickup(){
    }

    /*Getters*/
    public int getMaintenancePickupId() {
        return this.maintenancePickupId;
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
        this.maintenancePickupId = maintenacePickupId;
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
        return maintenancePickupId > 0 
                && deliveryId > 0 
                && tokenId > 0 
                && dateOpen != null 
                && dateClose != null;
    }

    @Override
    public String toString() {
        return "Recolha de Manutenção: \n"
                + "ID: " + maintenancePickupId
                + "\nEntrega ID: " + deliveryId
                + "\nData Abertura Prateleira: " + dateOpen
                + "\nData Fecho Prateleira: " + dateClose
                + "\nToken ID: " + tokenId;
    }
}
