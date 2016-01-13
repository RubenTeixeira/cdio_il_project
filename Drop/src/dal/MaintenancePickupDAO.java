/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.MaintenancePickup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcoSousa
 */
public class MaintenancePickupDAO extends GenericDAO<MaintenancePickup> {

    private final static String TABLENAME = "RECOLHA_MANUTENCAO";

    public MaintenancePickupDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public boolean insertNew(MaintenancePickup obj) {

        String query = "INSERT INTO MAINTENANCE_PICKUP ("
                + "MAINTENANCE_PICKUP_ID,OPEN_SHELF_DATE,CLOSE_SHELF_DATE,ID_ENTREGA, ID_TOKEN_COLABORADOR,PHOTO_PATH) "
                + " VALUES (" + obj.getMaintenancePickupId() + ","
                + "TO_DATE('" + obj.getDateOpen() + "', 'dd-mm-yyyy HH24:MI'),"
                + "TO_DATE('" + obj.getDateClose() + "', 'dd-mm-yyyy HH24:MI'),"
                + obj.getDeliveryId() + ", "
                + obj.getTokenId() + ",'"
                + obj.getFilePath()  + "')";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintenancePickupDAO.class.getName()).log(Level.SEVERE, "Not possible to add new Pick.", ex);
        }
        return false;
    }

    @Override
    public boolean update(MaintenancePickup obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(MaintenancePickup obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MaintenancePickup get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNextId() {
        String query = "select nvl(max(maintenance_pickup_id),0)+1 as id from maintenance_pickup";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    public MaintenancePickup newMaintenancePickup(int maintenancePickupId, String dateOpen, String dateClose, int deliveryId, int tokenId, String filePath) {
        return new MaintenancePickup(maintenancePickupId, dateOpen, dateClose, deliveryId, tokenId, filePath);
    }

    public MaintenancePickup newMaintenancePickup(){
        return new MaintenancePickup();
    }
}
