/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dal.DropPointDAO;
import dal.MaintenanceDAO;
import dal.Table;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class RepairPlan implements WorkPlan {
    
    private List<Repair> planPath;

    /**
     * Empty constructor
     */
    public RepairPlan() {
        this.planPath = new ArrayList<>();
    }

    /**
     * Constructor with planPath
     * @param planPath ordered path containing planned Repairs 
     */
    public RepairPlan(List<Repair> planPath) {
        this.planPath = planPath;
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
    public List<Plannable> calcPlanPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Map<DropPoint, Float> createDropPointMap() {
        return null;
    }
    
}
