/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.GenericDAO;
import domain.PickUp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class PickUpDAO extends GenericDAO<PickUp> {
    
    private final static String TABLENAME = "RECOLHA";

    public PickUpDAO(Connection con) {
        super(con, TABLENAME);
    }
    
    /**
     * Retrieves incremental ID for this object correponding Table
     * @return int ID
     */
    public int getNextId() {
        String query = "select nvl(max(id_recolha),0)+1 as id from recolha";
        PreparedStatement stmnt;
        try {
            stmnt=this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean insertNew(PickUp obj) {
        String query = "INSERT INTO RECOLHA ("
                + "ID_RECOLHA,ID_ENTREGA,ID_TOKEN_CLIENTE,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA)"
                + " VALUES ("+obj.getPickUpID()+","+obj.getDeliveryID()+","+obj.getTokenID()+","
                + "TO_DATE('"+obj.getOpenedDate()+"', 'dd-mm-yyyy HH24:MI'),"
                + "TO_DATE('"+obj.getClosedDate()+"', 'dd-mm-yyyy HH24:MI'))";
        PreparedStatement stmnt;
        try {
            stmnt=this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public boolean update(PickUp obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(PickUp obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PickUp get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
