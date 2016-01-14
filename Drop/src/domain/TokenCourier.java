/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dal.DeliveryDAO;
import dal.CellDAO;
import persistence.SQLConnection;
import dal.Table;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class TokenCourier extends TokenImpl {

    public TokenCourier(int id, String generationDate, String expirationDate, int state, String code, int idReservation) {
        super(id, generationDate, expirationDate, state, code, idReservation);
    }
    
    @Override
    public CellTransaction newTransaction(SQLConnection manager) {
        Cell cell = getCell(manager);
        if (cell != null) {
            System.out.println(cell);
            return new Delivery(cell.getId(), getReservationId(), getId());
            
        }
        return null;
    }

    @Override
    public Cell getCell(SQLConnection manager) {
        Cell cell = null;
        try {
            cell = ((CellDAO)manager.getDAO(Table.CELL)).findCellForDelivery(getCode());
        } catch (SQLException ex) {
        }
        return cell;
    }

    @Override
    public void close(SQLConnection manager, CellTransaction transaction) {
        DeliveryDAO deliveryDAO;
        Delivery delivery = (Delivery) transaction;
        try {
            deliveryDAO = (DeliveryDAO)manager.getDAO(Table.DELIVERY);
            delivery.setId(deliveryDAO.getNextId());
            delivery.setNotificationEmail(deliveryDAO.getClientEmail(delivery));
            deliveryDAO.insertNew(delivery);
            String pickUpToken = deliveryDAO.getPickUpToken(delivery);
            Notice.enviarEmail(delivery.getNotificationEmail(), pickUpToken);
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, 
                    "Error contacting the Database.");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        TokenCourier other = (TokenCourier) o;
        if (this.getId()==other.getId() 
                && this.getGenerationDate()==other.getGenerationDate() 
                && this.getExpirationDate()==other.getExpirationDate()
                && this.getState()==other.getState()
                && this.getCode()==other.getCode()
                && this.getReservationId()==other.getReservationId()) {
            return false;
        }
        return true;
    }
    
}
