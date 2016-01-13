/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import domain.MaintenancePlan;
import domain.RepairPlan;
import domain.WorkPlan;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class PlanGenerationController {

    private WorkPlan plan;
    private final SQLConnection manager;

    /**
     * Controller Constructor
     *
     */
    public PlanGenerationController() {
        manager = persistence.OracleDb.getInstance();
    }

    public boolean startRepairPlanGeneration() {
        try {
            this.plan = new RepairPlan();
        } catch (SQLException ex) {
            Logger.getLogger(PlanGenerationController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean startMaintenancePlanGeneration() {
        try {
            this.plan = new MaintenancePlan();
        } catch (SQLException ex) {
            Logger.getLogger(PlanGenerationController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public String getStartingList() {
        return this.plan.getElements();
    }

    public String getPlannedList() {
        this.plan.calcPlanPath();
        return this.plan.toString();
    }

    public void submitPlan() {
        this.plan.submitPlanPath();
    }

}
