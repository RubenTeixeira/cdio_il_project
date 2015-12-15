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
import persistence.PickUpDAO;
import persistence.SQLConnection;
import persistence.Table;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class TokenClient extends TokenImpl {

    public TokenClient(int id, String code, int reservationID) {
        super(id, code, reservationID);
    }

    @Override
    public CellTransaction newTransaction(SQLConnection manager) {
        int deliveryID;
        try {
            deliveryID = ((DeliveryDAO)manager.getDAO(Table.ENTREGA)).getDeliveryIdByToken(getCode());
        } catch (SQLException ex) {
            return null;
        }
        return new PickUp(deliveryID, getId());
    }

    @Override
    public Cell getCell(SQLConnection manager) {
        Cell cell = null;
        try {
            cell = ((CellDAO)manager.getDAO(Table.PRATELEIRA)).findCellForPickUp(getCode());
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
        return cell;
    }

    @Override
    public void close(SQLConnection manager, CellTransaction transaction) {
        PickUpDAO recolhaDAO;
        try {
            recolhaDAO = (PickUpDAO)manager.getDAO(Table.RECOLHA);
            transaction.setId(recolhaDAO.getNextId());
            recolhaDAO.insertNew((PickUp)transaction);
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
        
    }
    
}
