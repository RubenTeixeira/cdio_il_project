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
import java.sql.PreparedStatement;
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
                                  "    VALUES (seq_id_repair.nextval,"+repair.getPlanID()+","
                                                +repair.getIncidentID()+","+getNextVisitIndex(repair)+")");
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
        ResultSet rs = executeQuery("UPDATE REPAIR set REPAIR_DATE = TO_DATE('"+new java.sql.Date(obj.getRepairDate().getTime())+"', 'yyyy-mm-dd')"
                + ",OBSERVATIONS = '"+obj.getObservations()+"', PARTS_USED = '"+obj.getPartsUsed()+"'"
        + " where id_repair = "+obj.getId());
        return rs != null;
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
    
    public int getNextVisitIndex(Repair repair) {
        String query = "SELECT nvl(MAX(VISIT_INDEX),0)+1 AS vIndex\n" +
                "          FROM REPAIR r\n" +
                "         WHERE r.ID_REPAIR_PLAN = "+repair.getPlanID();
        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("vIndex");
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

    public void insertPlan(RepairPlan plan) throws SQLException {
        PreparedStatement stmnt;
        PreparedStatement stmntAux;
        String qry;
        this.con.setAutoCommit(false);
        qry = "INSERT INTO REPAIR_PLAN (ID_REPAIR_PLAN,REPAIR_PLAN_DATE,ID_REPAIR_TEAM)\n" +
                                    "    VALUES ("+plan.getId()+",(sysdate),"+plan.getTeamID()+")";
        stmnt = this.con.prepareStatement(qry);
        
        if (stmnt.executeUpdate() > 0) {
            int visiIndex = 0;
            for (Repair r : plan.getPlanPath()) {
                qry = "INSERT INTO REPAIR (ID_REPAIR,ID_REPAIR_PLAN,ID_INCIDENT,VISIT_INDEX)\n" +
                                                  "    VALUES (seq_id_repair.nextval,"+plan.getId()+","
                                                        +r.getIncidentID()+","+ ++visiIndex +")";
                stmntAux = this.con.prepareStatement(qry);
                if (stmntAux.executeUpdate() == 0) {
                    this.con.rollback();
                }
            }

        }
        this.con.commit();
        this.con.setAutoCommit(true);
    }
    
    public String getCompletedRepairbyDropPoint(DropPoint droppoint) throws SQLException{
        String lRepair = "";
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
            
            lRepair += repair + "\n";
        }               
        
        
        
        return lRepair;
    }

    public RepairPlan getCurrentRepairPlan() throws SQLException
    {
        RepairPlan repairPlan = new RepairPlan();
        
        ResultSet rs = executeQuery("SELECT * from REPAIR_PLAN p" +
                  "                     WHERE p.REPAIR_PLAN_DATE >= TO_DATE(TO_CHAR(CURRENT_DATE, 'dd-mm-yyyy'),'dd-mm-yyyy')\n" +
                  "                       AND p.REPAIR_PLAN_DATE < TO_DATE(TO_CHAR(CURRENT_DATE, 'dd-mm-yyyy'),'dd-mm-yyyy')+1");
        if (rs.next()) {
            repairPlan.setId(rs.getInt("ID_REPAIR_PLAN"));
            repairPlan.setTeamID(rs.getInt("ID_REPAIR_TEAM"));
            repairPlan.setDate(rs.getDate("REPAIR_PLAN_DATE"));

            rs = executeQuery("select r.*, d.* from REPAIR_PLAN l, REPAIR r, INCIDENT i, PRATELEIRA p, ARMARIO a, DROPPOINT d\n" +
                    "              where i.REPAIRED = 0\n" +
                    "                and l.ID_REPAIR_PLAN = "+repairPlan.getId()+"\n" +
                    "                and l.ID_REPAIR_PLAN = r.ID_REPAIR_PLAN\n" +
                    "                and r.ID_INCIDENT = i.ID_INCIDENT\n" +
                    "                and i.ID_PRATELEIRA = p.ID_PRATELEIRA\n" +
                    "                and p.ID_ARMARIO = a.ID_ARMARIO\n" +
                    "                and a.ID_DROPPOINT = d.ID_DROPPOINT\n" +
                    "                ORDER BY r.VISIT_INDEX ASC");

            while(rs.next()){
                Repair repair = new Repair(rs.getInt("ID_INCIDENT"), rs.getInt("VISIT_INDEX"));
                repair.setId(rs.getInt("ID_REPAIR"));
                DropPoint dropPoint = new DropPoint(rs.getInt("ID_DROPPOINT"), rs.getString("NOME_DROPPOINT"), rs.getInt("ID_MORADA"), rs.getInt("FREE_DAYS"));
                repair.setDropPoint(dropPoint);
                repair.setPlanID(rs.getInt("ID_REPAIR_PLAN"));
                repairPlan.addRepair(repair);
            }
        }
        return repairPlan;
    }
    public List<String> getRepairIncidents(Repair repair) throws SQLException
    {
        List<String> listIncidents = new ArrayList<>();
        ResultSet rs = executeQuery("select p.description from Repair r, incident i, incident_type p\n" +
                           "where r.id_repair = "+repair.getId()+
                           " and r.id_incident = i.id_incident\n" +
                           "and i.id_incident_type = p.id_incident_type");
        while (rs.next())
        {
            listIncidents.add(rs.getString("DESCRIPTION"));
        }
         return listIncidents;
    }

    public int getCorrespondingDropPointID(Repair r) throws SQLException
    {
        int id = 0;
        ResultSet rs = executeQuery("select d.ID_DROPPOINT FROM Repair r, Incident i, Prateleira p, Armario a, DropPoint d\n" +
                            "Where r.ID_REPAIR = "+r.getId()+
                            " and r.ID_INCIDENT = i.ID_INCIDENT\n" +
                            "and i.ID_PRATELEIRA = p.ID_PRATELEIRA\n" +
                            "and p.ID_ARMARIO = a.ID_ARMARIO\n" +
                            "and a.ID_DROPPOINT = d.ID_DROPPOINT");
        while (rs.next())
        {
                  id=rs.getInt("ID_DROPPOINT");
        }
        return id;
    }

    public void deletePlannedRepairs(int planID) {
        executeQuery("delete from REPAIR r WHERE r.ID_REPAIR_PLAN = "+planID);
        executeQuery("delete from Repair_Plan p WHERE p.ID_REPAIR_PLAN = "+planID);
    }

}
