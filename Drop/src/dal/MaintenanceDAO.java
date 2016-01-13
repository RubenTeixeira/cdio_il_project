/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.DropPoint;
import domain.Maintenance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MaintenanceDAO extends GenericDAO<Maintenance> {

    private final static String TABLENAME = "TOKEN";
    
    public MaintenanceDAO(Connection con) {
        super(con, TABLENAME);
    }
    
    /**
     * Get unexecuted Maintenance corresponding to given DropPoint
     * @param dp DropPoint
     * @return Maintenance
     */
    public Maintenance getDropPointMaintenance(DropPoint dp) {
        Maintenance maintenance = null;
        ResultSet rs = executeQuery("SELECT * FROM MANUTENCAO" +
                                    "  WHERE ID_DROPPOINT = "+dp.getId()+
                                    "  AND DATA_FIM = NULL");
        if (rs != null) {
            try {
                rs.next();
                maintenance = new Maintenance(
                        rs.getInt("ID_MANUTENCAO"), rs.getInt("INDEX"), dp, rs.getDate("DATA_INICIO"), rs.getDate("DATA_FIM"));
            } catch (SQLException ex) {
            }
        }
        if (maintenance != null) {
            rs = executeQuery("SELECT * FROM CELL_MAINTENANCE"
                            + "     WHERE ID_MAINTENANCE = "+maintenance.getId());
            if (rs != null) {
                try {
                    while (rs.next())
                        maintenance.addCellMaintenance(rs.getInt("ID_CELL"));
                } catch (SQLException ex) {
                }
            }
            return maintenance;
        }
        return null;
    }
    
    /**
     * Returns the total duration of the tasks defined for given DropPoint
     * @param dp DropPoint
     * @return total duration
     */
    public Float getDropPointMaintenanceDuration(DropPoint dp) {

        ResultSet rs = executeQuery("SELECT SUM(AVG_DURATION) AS DURATION FROM PREEMPTIVE_DP_PLAN p, MAINTENANCE_TASK t\n" +
                                    "  WHERE p.ID_DROPPOINT = "+dp.getId()+"\n" +
                                    "  AND p.ID_TASK = t.ID_TASK");
        if (rs != null) {
                try {
                    if (rs.next())
                        return rs.getFloat("DURATION");
                } catch (SQLException ex) {
                }
        }
        return null;
    }

    @Override
    public boolean insertNew(Maintenance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Maintenance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Maintenance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Maintenance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
