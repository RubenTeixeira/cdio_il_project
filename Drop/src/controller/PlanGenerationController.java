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
import java.util.ArrayList;
import java.util.List;
import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class PlanGenerationController {

    private WorkPlan plan;
    private SQLConnection manager;

    /**
     * Controller Constructor
     *
     * @param file settings file containing the credentials to create the DAL
     * SQL connection
     */
    public PlanGenerationController(String file) {
        manager = persistence.OracleDb.getInstance(file);
    }

    public void startRepairPlanGeneration() {
        this.plan = new RepairPlan();
    }

    public void startMaintenancePlanGeneration() {
        this.plan = new MaintenancePlan();
    }

    public List<DropPoint> getDropPointList() {
        //..placeholder
        return new ArrayList<>();
    }

    public void setSelectedDropPointList(List<DropPoint> list) {
        //this.plan.
    }
}
