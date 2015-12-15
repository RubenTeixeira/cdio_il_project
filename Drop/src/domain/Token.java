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

    int getId();
    void setId(int id);
    String getCode();
    void setCode(String code);
    int getReservationId();
    void setReservationId(int reservationID);
    CellTransaction newTransaction(SQLConnection manager);
    Cell getCell(SQLConnection manager);
    void close(SQLConnection manager, CellTransaction transaction);
 
}
