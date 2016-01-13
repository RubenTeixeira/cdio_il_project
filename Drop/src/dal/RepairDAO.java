/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.DropPoint;
import domain.Repair;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class RepairDAO extends GenericDAO<Repair> {
    
    private final static String TABLENAME = "REPAIR";
    
    public RepairDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public boolean insertNew(Repair obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Repair obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Repair obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Repair get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<DropPoint> getDropPointsWithIncidents() {
        List<DropPoint> lstDropPoints = null;
        
        ResultSet rs = executeQuery("SELECT d.* FROM INCIDENT i, PRATELEIRA p, ARMARIO a, DROPPOINT d\n" +
                                "    WHERE i.ID_PRATELEIRA = p.ID_PRATELEIRA\n" +
                                "    AND p.ID_ARMARIO = a.ID_ARMARIO\n" +
                                "    AND a.ID_DROPPOINT = d.ID_DROPPOINT");
        
        if (rs != null) {
            lstDropPoints = new ArrayList<>();
            try {
                while (rs.next()) {
                    DropPoint dp = new DropPoint();
                    dp.setId(rs.getInt("ID_DROPPOINT"));
                    dp.setName(rs.getString("NOME_DROPPOINT"));
                    lstDropPoints.add(dp);
                }
            } catch (SQLException ex) {
            }
        }
        return lstDropPoints;
    }
    
}
