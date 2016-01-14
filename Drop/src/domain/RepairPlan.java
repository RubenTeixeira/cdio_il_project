/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
        graph = new GraphDropPointNet();
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

    @Override
    public String toString() {
        StringBuilder planStr = new StringBuilder();
        int i = 1;
        for (Repair repair : planPath) {
            planStr.append(i).append(" - ").append(repair).append("\n");
            i++;
        }
        return planStr.toString();
    }

    @Override
    public void calcPlanPath() {
        List<DropPoint> lstDropPoints = graph.buildPathWithPriority(createDropPointMap());
        List<Incident> lstIncidents;
        DropPoint dp;
        int size = lstDropPoints.size();
        for (int i = 0; i < size; i++) {
            dp = lstDropPoints.get(i);
            lstIncidents = incidentDAO.getIncidentsFromDropPoint(dp);
            for (Incident in : lstIncidents)
                this.planPath.add(new Repair(in.getIncident_id(),i));
        }
    }
    
    private Map<DropPoint, Float> createDropPointMap() {
        List<DropPoint> lstDropPoints = dropPointDAO.getDropPointsWithIncidents();
        return SLA.buildPriorityMap(lstDropPoints);
    }

    @Override
    public boolean submitPlanPath() {
        this.date = new Date();
        this.teamID = 1; // testing purposes
        this.id = repairDAO.getNextPlanId();
        
        if (!repairDAO.insertPlan(this))
            return false;
        
        for (Repair r : this.planPath) {
            r.setId(repairDAO.getNextId());
            r.setPlanID(this.id);
            if (!repairDAO.insertNew(r))
                return false;
        }
        
        return true;
    }

    @Override
    public String getElements() {
        StringBuilder strB = new StringBuilder();
        for (String in : incidentDAO.getAllIncidents())
            strB.append(in).append("\n");
        
        return strB.toString();
    }
    
}
