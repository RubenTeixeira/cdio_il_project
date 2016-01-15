/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DeliveryDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.DropPoint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class ConsultOccupationDeliveriesController {

    private ResultSet rs;
    private int idDropPoint;
    private DropPointDAO dropDao;
    private DeliveryDAO deliveryDao;
    private SQLConnection con;
    private List<DropPoint> dropList;
    
    public ConsultOccupationDeliveriesController(String file) {
        con = persistence.OracleDb.getInstance();
        try {
            dropDao = (DropPointDAO) con.getDAO(Table.DROPPOINT);
            deliveryDao = (DeliveryDAO) con.getDAO(Table.DELIVERY);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultOccupationDeliveriesController.class.getName()).log(Level.SEVERE,
                    "Connection failed.", ex);
        }
        
    }

    public List<DropPoint> deliveriesConsultationCollectionsDropPoint() {
        dropList = dropDao.getListDropPoints();
        return dropList;
    }

    public void selectDropPoint(int id) {
        this.idDropPoint = id;
    }

    public String getListRegisterDelivered(DropPoint dp) {
        return deliveryDao.deliveriesList(dp);
    }

    public String getListRegistrationCollected(DropPoint dp) {
        return deliveryDao.collectedList(dp);
    }

    public String getOccupation(DropPoint dp) {
        return dropDao.consultOccupation(dp);
    }

}
