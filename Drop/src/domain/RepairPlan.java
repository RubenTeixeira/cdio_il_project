/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dal.RepairDAO;
import dal.Table;
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
    
    private RepairDAO repairDAO;

    /**
     * Empty constructor
     */
    public RepairPlan() throws SQLException {
        this.planPath = new ArrayList<>();
        SQLConnection manager = persistence.OracleDb.getInstance();
        repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
    }

    /**
     * Constructor with planPath
     * @param planPath ordered path containing planned Repairs 
     */
    public RepairPlan(List<Repair> planPath) throws SQLException {
        this.planPath = planPath;
        SQLConnection manager = persistence.OracleDb.getInstance();
        repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
    }
    
    // Getter and Setter
    public List<Repair> getPlanPath() {return planPath;}
    public void setPlanPath(List<Repair> planPath) {this.planPath = planPath;}

    
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
        for (Repair repair : planPath)
            planStr.append(repair+"\n");
        
        return planStr.toString();
    }

    @Override
    public void calcPlanPath() {
        List<DropPoint> lstDropPoints = esinf.dropGraph.GraphDropPointNet.nomeDoMetodo(createDropPointMap()); // .. a alterar nome do metodo
        for (int i = 0; i < lstDropPoints.size(); i++) {
            DropPoint dp = lstDropPoints.get(i);
            Repair repair = new Repair(incident);
            this.planPath.add(repair);
        }
    }
    
    private Map<DropPoint, Float> createDropPointMap() {
        List<DropPoint> lstDropPoints = repairDAO.getDropPointsWithIncidents();
        return SLA.buildPriorityMap(lstDropPoints);
    }

    @Override
    public boolean submitPlanPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
