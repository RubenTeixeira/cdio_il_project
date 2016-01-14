/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.DropPoint;
import domain.Repair;
import domain.RepairPlan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<String> getCompletedRepairbyDropPoint(DropPoint droppoint) throws SQLException{
        List<String> lRepair = new ArrayList<>();
        String query = "SELECT * from repair r, incident i, prateleira p, armario a, incident_type t "
                + "WHERE r.id_incident = i.id_incident "
                + "AND i.id_incident_type = t.id_incident_type "
                + "AND i.id_prateleira = p.id_prateleira "
                + "AND p.id_armario = a.id_armario "
                + "AND a.id_droppoint = " + droppoint.getId();
        ResultSet rs = executeQuery(query);
        while(rs.next()){
            String repair = "Repair ID: " + rs.getInt("id_repair") + "\n"
                    + "Incident type: " + rs.getString("description") + "\n"
                    + "Date: " + rs.getDate("repair_date") + "\n"
                    + "Cell ID: " + rs.getInt("id_prateleira") + "\n";
            
            lRepair.add(repair);
        }               
        
        
        
        return lRepair;
    }

    public RepairPlan getCurrentRepairPlan()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
