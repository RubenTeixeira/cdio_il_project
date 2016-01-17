/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dal.AddressDAO;
import dal.DropPointDAO;
import dal.IncidentDAO;
import dal.RepairDAO;
import dal.Table;
import esinf.dropGraph.GraphDropPointNet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class RepairPlan implements WorkPlan {
    
    private int id;
    private int teamID;
    private Date date;
    private List<Repair> planPath;
    private GraphDropPointNet graph;
    private RepairDAO repairDAO;
    private DropPointDAO dropPointDAO;
    private IncidentDAO incidentDAO;
    private AddressDAO addressDAO;

    /**
     * Empty constructor
     * @throws java.sql.SQLException
     */
    public RepairPlan() throws SQLException {
        this(new ArrayList<>());
    }

    /**
     * Constructor with planPath
     * @param planPath ordered path containing planned Repairs 
     * @throws java.sql.SQLException 
     */
    public RepairPlan(List<Repair> planPath) throws SQLException {
        this.planPath = planPath;
        SQLConnection manager = persistence.OracleDb.getInstance();
        repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
        dropPointDAO = (DropPointDAO) manager.getDAO(Table.DROPPOINT);
        incidentDAO = (IncidentDAO) manager.getDAO(Table.INCIDENT);
        addressDAO = (AddressDAO) manager.getDAO(Table.ADDRESS);
        
    }
    
    // Getter and Setter
    public List<Repair> getPlanPath() {return planPath;}
    public void setPlanPath(List<Repair> planPath) {this.planPath = planPath;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getTeamID() {return teamID;}
    public void setTeamID(int teamID) {this.teamID = teamID;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    /**
     * Adds a Repair to this RepairPlan planPath
     * @param repair
     * @return true if successfull, false otherwise
     */
    public boolean addRepair(Repair repair) {
        if (repair.getPlanID() == this.id)
            this.planPath.add(repair.getIndex(), repair);
        return false;
    }

    /**
     * Default Plan path calculation, using the HeadQuarters as
     * start and finish location. Calls private method.
     * @throws SQLException 
     */
    @Override
    public void calcPlanPath() throws SQLException {
        Address initVertex = addressDAO.getHeadQuartersLocation();
        Address endVertex = addressDAO.getHeadQuartersLocation();
        calcPlanPath(initVertex, endVertex);
    }
    
    /**
     * Actual Plan Path calculation. Builds Plan from scratch or rebuilds Plan if
     * unresolved Repairs are found making it so that if new Incidents with far more priority
     * arise, they will make it into the plan sooner.
     * @param initVertex
     * @param endVertex
     * @throws SQLException 
     */
    private void calcPlanPath(Address initVertex, Address endVertex) throws SQLException {
        this.planPath.clear();
        graph = new GraphDropPointNet();
        List<DropPoint> lstDropPoints = graph.buildPathWithPriority(createDropPointMap(), initVertex, endVertex);
        List<Incident> lstIncidents;
        int i = 0;
        for (DropPoint dp : lstDropPoints) {
            lstIncidents = incidentDAO.getIncidentsFromDropPoint(dp);
            
            for (Incident in : lstIncidents) {
                Repair r = new Repair(in.getIncident_id());
                r.setDropPoint(dp);
                this.planPath.add(r);
                i++;
            }
        }
        graph = null;
    }
    
    /**
     * Builds a list of DropPoints having unresolved incidents
     * then submits it to SLA.buildPriorityMap() which returns
     * an HashMap with key DropPoint and a float containing the sum
     * of the distance as time and the the repair priority (the lesser the more priority)
     * @return Map<DropPoint, Float>
     */
    private Map<DropPoint, Float> createDropPointMap() {
        List<DropPoint> lstDropPoints = dropPointDAO.getDropPointsWithIncidents();
        return SLA.buildPriorityMap(lstDropPoints);
    }

    /**
     * Registers this RepairPlan associating and saving all its Repair elements
     * on the DataBase. Calls private method submitPlanPath()
     * @return true if successfull, false otherwise
     */
    @Override
    public boolean submitNewPlanPath() {
        this.teamID = 1; // testing purposes
        this.id = repairDAO.getNextPlanId();
        
        if (!repairDAO.insertPlan(this))
            return false;
        
        return submitPlanPath();
        
    }
    
    /**
     * Actual Plan submission
     * @return true if successfull, false otherwise
     */
    private boolean submitPlanPath() {
        for (Repair r : this.planPath) {
            removePlannedRepair(r.getIncidentID());
            System.out.println("Inserting :"+r);
            r.setPlanID(this.id);
            
            if (!repairDAO.insertNew(r))
                return false;
        }
        
        return true;
    }

    /**
     * Method used to list the available Incidents, including
     * the ones with Repairs already planned for.
     * @return String containing all the unrepaired Incidents
     */
    @Override
    public String getElements() {
        StringBuilder strB = new StringBuilder();
        for (String in : incidentDAO.getAllIncidents())
            strB.append(in).append("\n");
        
        return strB.toString();
    }
    
    /**
     * Updates this RepairPlan by updating on the DataBase the now finished Repair
     * and then re-calculating the plan path with the new starting vertex being 
     * the current Repair location (DropPoint address)
     * @param finishedRepair
     * @throws SQLException 
     */
    public void updatePlan(Repair finishedRepair) throws SQLException {
        repairDAO.update(finishedRepair);
        this.setId(finishedRepair.getId());
        Address initVertex = addressDAO.getAddressWithLatLongById(finishedRepair.getDropPoint().getIdAddress());
        Address endVertex = addressDAO.getHeadQuartersLocation();
        calcPlanPath(initVertex, endVertex);
        submitPlanPath();
    }

    /**
     * Removes an already planned for Repair object from DataBase if
     * existant for given Incident ID
     * Usefull for replanning
     * @param incidentID 
     */
    private void removePlannedRepair(int incidentID) {
        System.out.println("Deleting Repair from incidentID :"+incidentID);
        repairDAO.deletePlannedRepair(incidentID);
    }
    
    @Override
    public String toString() {
        StringBuilder planStr = new StringBuilder();
        int i = 1;
        for (Repair repair : planPath) {
            planStr.append(i).append(" - ").append(repair.getDropPoint().getName()).append(" - IncidentID").append(repair.getIncidentID()).append("\n");
            i++;
        }
        return planStr.toString();
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.planPath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RepairPlan other = (RepairPlan) obj;
        if (!Objects.equals(this.planPath, other.planPath)) {
            return false;
        }
        return true;
    }
}
