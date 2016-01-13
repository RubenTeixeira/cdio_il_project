/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DeliveryDAO;
import dal.MaintenancePickupDAO;
import dal.PickUpDAO;
import dal.Table;
import dal.TokenDAO;
import domain.Delivery;
import domain.DropPoint;
import domain.Token;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author Afonso
 */
public class ParcelPickupController {

    private TokenDAO tokenDAO;
    private DeliveryDAO deliveryDAO;
    private Connection con;
    private SQLConnection manager;

    /*
    Construtor da classe que inicializa a conecção com os dados na interface Settings
    e tem acesso à DAO de token
     */
    public ParcelPickupController() {
        try {
            manager = persistence.OracleDb.getInstance();
            tokenDAO = (TokenDAO) manager.getDAO(Table.TOKEN);
            deliveryDAO = (DeliveryDAO) manager.getDAO(Table.DELIVERY);
        } catch (SQLException ex) {
            Logger.getLogger(ExtendTokenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String generateRandomToken(){
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public List<Token> checkValidity() {
        return tokenDAO.checkValidity();
    }

    public TokenDAO getTokenDAO() {
        return tokenDAO;
    }

    public DeliveryDAO getDeliveryDAO() {
        return deliveryDAO;
    }
    
    public boolean updateDelivery(Delivery delivery){
        return deliveryDAO.update(delivery);
    }
    
    public boolean insertNewToken(Token token){
        return getTokenDAO().insertNew(token);
    }
    
    public Delivery getDeliveryByReservationID(int reservationId){
        return getDeliveryDAO().getDeliveryByReservationID(reservationId);
    }
    
    public Delivery getDeliveryByReservationIDFromDP(int reservationId, DropPoint dropPoint){
        return getDeliveryDAO().getDeliveryByReservationIDFromDP(reservationId, dropPoint);
    }
    
    public int getNextTokenID(){
        return getTokenDAO().getNextId();
    }
  
}
