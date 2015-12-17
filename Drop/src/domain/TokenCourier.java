/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.DeliveryDAO;
import persistence.CellDAO;
import persistence.SQLConnection;
import persistence.Table;

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
            deliveryDAO.insertNew(delivery);
            String email = deliveryDAO.getClientEmail(delivery);
            String pickUpToken = deliveryDAO.getPickUpToken(delivery);
            Notice.enviarEmail(email, pickUpToken);
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
    }
    
}
