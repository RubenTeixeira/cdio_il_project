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

    /**
     * The maintenance pickup id
     */
    private int maintenancePickupId;

    /**
     * The maintenance pickup open cell date
     */
    private String dateOpen;

    /**
     * The maintenance pickup close cell date
     */
    private String dateClose;

    /**
     * The delivery id
     */
    private int deliveryId;

    /**
     * The token id
     */
    private int tokenId;

    /**
     * The photo path
     */
    private String filePath;

    /**
     * Constructor of MaintenancePickup with the following parametres:
     *
     * @param maintenancePickupId
     * @param dateOpen
     * @param dateClose
     * @param deliveryId
     * @param tokenId
     * @param filePath
     */
    public MaintenancePickup(int maintenancePickupId, String dateOpen, String dateClose, int deliveryId, int tokenId, String filePath) {
        this.maintenancePickupId = maintenancePickupId;
        this.dateOpen = dateOpen;
        this.dateClose = dateClose;
        this.deliveryId = deliveryId;
        this.tokenId = tokenId;
        this.filePath = filePath;
    }

    /**
     * Constructor of MaintenancePickup with no parametres
     */
    public MaintenancePickup() {
    }

    /*Getters*/
    /**
     * Returns the maintenance pickup id
     *
     * @return the id
     */
    public int getMaintenancePickupId() {
        return this.maintenancePickupId;
    }

    /**
     * Returns the maintenance pickup open cell date
     *
     * @return the open date
     */
    public String getDateOpen() {
        return this.dateOpen;
    }

    /**
     * Returns the maintenance pickup close cell date
     *
     * @return the close cell date
     */
    public String getDateClose() {
        return this.dateClose;
    }

    /**
     * Returns the delivery id
     *
     * @return the delivery id
     */
    public int getDeliveryId() {
        return this.deliveryId;
    }

    /**
     * Returns the token id
     *
     * @return the token id
     */
    public int getTokenId() {
        return this.tokenId;
    }

    /**
     * Returns the photo path
     * @return the photo path
     */
    public String getFilePath() {
        return filePath;
    }

    /*Setters*/
    /**
     * Sets a new maintenance pickup id
     * @param maintenacePickupId the new id
     */
    public void setMaintenacePickupId(int maintenacePickupId) {
        this.maintenancePickupId = maintenacePickupId;
    }

    /**
     * Sets a new delivery id
     * @param deliveryId the new delivery id
     */
    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    /**
     * Sets a new cell open date 
     * @param dateOpen the new open date
     */
    public void setDateOpen(String dateOpen) {
        this.dateOpen = dateOpen;
    }

    /**
     * Sets the new cell close date
     * @param dateClose the new close date
     */
    public void setDateClose(String dateClose) {
        this.dateClose = dateClose;
    }

    /**
     * Sets the new token id
     * @param tokenId the new token id
     */
    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    /**
     * Sets the new photo path 
     * @param filePath the new photo path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Validates an instance of MaintenancePickup
     *
     * @return true if is valid, otherwise false
     */
    public boolean validate() {
        return maintenancePickupId > 0
                && deliveryId > 0
                && tokenId > 0
                && dateOpen != null
                && dateClose != null;
    }

    /**
     * Description of an instance of MaintenancePickup
     *
     * @return a string representation of maintenance pickup object
     */
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
