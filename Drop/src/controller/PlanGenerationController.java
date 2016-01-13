/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import domain.MaintenancePlan;
import domain.Plannable;
import domain.RepairPlan;
import domain.WorkPlan;
import java.sql.SQLException;
import java.util.ArrayList;
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
     * @param file settings file containing the credentials to create the DAL
     * SQL connection
     */
    public PlanGenerationController(String file) {
        manager = persistence.OracleDb.getInstance(file);
    }

    public boolean startRepairPlanGeneration() {
        this.plan = new RepairPlan();
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

    public String getPlannableList() {
        this.plan.calcPlanPath();
        return this.plan.toString();
    }

    public void submitPlan(List<DropPoint> list) {
        this.plan.submitPlanPath();
    }
}
