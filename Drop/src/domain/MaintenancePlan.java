/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MaintenancePlan implements WorkPlan {
    
    private List<Maintenance> planPath;

    /**
     * Empty constructor
     */
    public MaintenancePlan() {
        this.planPath = new ArrayList<>();
    }

    public MaintenancePlan(List<Maintenance> planPath) {
        this.planPath = planPath;
    }

    // Getter and Setter
    public List<Maintenance> getPlanPath() {return planPath;}
    public void setPlanPath(List<Maintenance> planPath) {this.planPath = planPath;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.planPath);
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
        final MaintenancePlan other = (MaintenancePlan) obj;
        if (!Objects.equals(this.planPath, other.planPath)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder planStr = new StringBuilder();
        for (Maintenance maintenance : planPath)
            planStr.append(maintenance+"\n");
        
        return planStr.toString();
    }

    @Override
    public List<WorkPlan> calcPlanPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
