/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Repair;
import domain.RepairPlan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class RepairDAO extends GenericDAO<Repair>{
    
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
    
    private final static String TABLENAME = "REPAIR";
    
    public RepairDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public boolean insertNew(Repair repair) {
        ResultSet rs = executeQuery("INSERT INTO REPAIR (ID_REPAIR,ID_REPAIR_PLAN,ID_INCIDENT,VISIT_INDEX)\n" +
                                  "    VALUES ("+repair.getId()+","+repair.getPlanID()+","
                                                +repair.getIncidentID()+","+repair.getIndex()+")");
        if (rs != null) {
                try {
                    if (rs.next())
                        return true;
                } catch (SQLException ex) {
                }
        }
        return false;
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
    
    public int getNextId() {
        String query = "select nvl(max(id_Repair),0)+1 as id from Repair";
        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("id");
            } catch (SQLException ex) {
            }
        }
        return -1;
    }

    public int getNextPlanId() {
        String query = "select nvl(max(id_Repair_Plan),0)+1 as id from Repair_Plan";
        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("id");
            } catch (SQLException ex) {
            }
        }
        return -1;
    }

    public boolean insertPlan(RepairPlan plan) {
        ResultSet rs = executeQuery("INSERT INTO REPAIR_PLAN (ID_REPAIR_PLAN,REPAIR_PLAN_DATE,ID_REPAIR_TEAM)\n" +
                                    "    VALUES ("+plan.getId()+","+plan.getDate()+","+plan.getTeamID()+")");
        
        if (rs != null) {
                try {
                    if (rs.next())
                        return true;
                } catch (SQLException ex) {
                }
        }
        return false;
    }
}
