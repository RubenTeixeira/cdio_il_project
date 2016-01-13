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
public class MaintenancePlan implements WorkPlan {
    
    private List<Maintenance> planPath;

    /**
     * Constructor with no parametre
     */
    public MaintenancePlan() {
        this.planPath = new ArrayList<>();
    }

    /**
     * Constructor with a planPath as parametre
     * @param planPath 
     */
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

    /**
     * Checks if the parametre is equal to the instance of MaintenancePlan
     * @param obj
     * @return 
     */
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

    /**
     * Description of an instance of MaintenancePlan
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder planStr = new StringBuilder();
        for (Maintenance maintenance : planPath)
            planStr.append(maintenance+"\n");
        
        return planStr.toString();
    }

    @Override
    public List<Plannable> calcPlanPath() {
        Map<DropPoint, Float> map = createDropPointMap();
        return esinf.dropGraph.GraphDropPointNet.nomeDoMetodo(map); // .. a alterar nome do metodo
    }


    private Map<DropPoint, Float> createDropPointMap() {
        SQLConnection manager = persistence.OracleDb.getInstance();
        DropPointDAO dropPointDAO = null;
        MaintenanceDAO maintenanceDAO = null;
        List<DropPoint> lstDropPoint;
        
        try {
            dropPointDAO = (DropPointDAO)manager.getDAO(Table.DROPPOINT);
            maintenanceDAO = (MaintenanceDAO)manager.getDAO(Table.MANUTENCAO);
        } catch (SQLException ex) {
            Logger.getLogger(MaintenancePlan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (dropPointDAO != null) {
            lstDropPoint = dropPointDAO.getListDropPoints();
            Map<DropPoint,Float> mapDropPoints = new HashMap();
            for (DropPoint dp : lstDropPoint) {
                Float maintenanceDuration = maintenanceDAO.getDropPointMaintenanceDuration(dp);
                mapDropPoints.put(dp, maintenanceDuration);
            }
            return mapDropPoints;
        }
        return null;
    }
    
    
    
}
