/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public interface Token {

    /*
    *   Getter methods
    */
    int getId();
    String getGenerationDate();
    String getExpirationDate();
    int getState();   
    String getCode();
    int getReservationId();
    
    /*
    *   Setter methods
    */
    void setId(int id);
    void setGenerationDate(String generationDate);
    void setExpirationDate(String expirationDate);
    void setState(int state);
    void setCode(String code);
    void setReservationId(int reservationID);
    
    /**
     * Starts the transaction instantiating and returning
     * an object of the implementer class
     * @param manager SQLConnection object responsible for DAO instantiation
     * @return object implementing CellTransaction (Delivery, PickUp (...))
     */
    CellTransaction newTransaction(SQLConnection manager);
    
    /**
     * Retrieves cell which corresponds to this Token/Transaction
     * @param manager DAO instantiator
     * @return Cell
     */
    Cell getCell(SQLConnection manager);
    
    /**
     * Closes the transaction sending it to the Database
     * @param manager DAO instantiator
     * @param transaction object implementing CellTransaction (Delivery, PickUp (...))
     */
    void close(SQLConnection manager, CellTransaction transaction);
 
}
