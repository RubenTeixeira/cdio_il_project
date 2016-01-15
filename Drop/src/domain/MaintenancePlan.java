/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dal.AddressDAO;
import dal.DropPointDAO;
import dal.MaintenanceDAO;
import dal.Table;
import esinf.dropGraph.GraphDropPointNet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MaintenancePlan implements WorkPlan {
    
    private int id;
    private int teamID;
    private Date planDate;
    private List<Maintenance> planPath;
    private MaintenanceDAO maintenanceDAO;
    private DropPointDAO dropPointDAO;
    private AddressDAO addressDAO;
    private GraphDropPointNet graph;

    /**
     * Constructor with no parametre
     * @throws java.sql.SQLException
     */
    public MaintenancePlan() throws SQLException {
        this(new ArrayList<>());
    }

    /**
     * Constructor with a planPath as parametre
     * @param planPath 
     */
    public MaintenancePlan(List<Maintenance> planPath) throws SQLException {
        this.planPath = planPath;
        SQLConnection manager = persistence.OracleDb.getInstance();
        maintenanceDAO = (MaintenanceDAO) manager.getDAO(Table.MANUTENCAO);
        dropPointDAO = (DropPointDAO)manager.getDAO(Table.DROPPOINT);
        addressDAO = (AddressDAO) manager.getDAO(Table.ADDRESS);
        
    }

    // Getter and Setter
    public List<Maintenance> getPlanPath() {return planPath;}
    public void setPlanPath(List<Maintenance> planPath) {this.planPath = planPath;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public int getTeamID() {return teamID;}
    public void setTeamID(int teamID) {this.teamID = teamID;}
    public Date getPlanDate() {return planDate;}
    public void setPlanDate(Date planDate) {this.planDate = planDate;}

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
        int i = 1;
        for (Maintenance maintenance : planPath) {
            planStr.append(i).append(" - Maintenance: ").append(maintenance.getDropPoint().getName()).append("\n");
            i++;
        }
        return planStr.toString();
    }

    @Override
    public void calcPlanPath() throws SQLException {
        Map<DropPoint, Float> map = createDropPointMap();
        Address initVertex = addressDAO.getHeadQuartersLocation();
        Address endVertex = addressDAO.getHeadQuartersLocation();
        long workHoursinSeconds = 28800;
        
        graph = new GraphDropPointNet();
        List<DropPoint> lstDropPoints = graph.buildPathWithPriority(map, initVertex, endVertex, workHoursinSeconds);
        
        for (int i = 0; i < lstDropPoints.size(); i++) {
            DropPoint dp = lstDropPoints.get(i);
            Maintenance maintenance = new Maintenance(i, dp, null, null, 0);
            this.planPath.add(maintenance);
        }
        
        graph = null;
    }

    /**
     * Cretes HashMap with key Droppoint and Value its Maintenance prospected duration
     * @return HashMap
     */
    private Map<DropPoint, Float> createDropPointMap() {
        SQLConnection manager = persistence.OracleDb.getInstance();
        List<DropPoint> lstDropPoint;

        if (dropPointDAO != null) {
            lstDropPoint = dropPointDAO.getListDropPoints();
            Map<DropPoint,Float> mapDropPoints = new HashMap();
            for (DropPoint dp : lstDropPoint) {
                Float maintenanceDuration = maintenanceDAO.getDropPointMaintenanceDuration(dp);
                mapDropPoints.put(dp, maintenanceDuration);
                //System.out.println("DropPoint: "+dp.getName()+" Dur: "+maintenanceDuration);
            }
            return mapDropPoints;
        }
        return null;
    }

    /**
     * Submits all maintenance jobs to DB aswell as its corresponding Plan
     * @return true if successfull, false otherwise
     */
    @Override
    public boolean submitPlanPath() {

        this.id = maintenanceDAO.getNextPlanId();
        this.teamID = 1; // still testing
        this.planDate = new Date();
        if (!maintenanceDAO.insertPlan(this))
            return false;
        
        for (Maintenance m : this.planPath) {
            m.setId(maintenanceDAO.getNextId());
            m.setPlanID(this.id);
            if (!maintenanceDAO.insertNew(m))
                return false;
        }
        
        return true;
    }

    @Override
    public String getElements() {
        StringBuilder strB = new StringBuilder();
        for (DropPoint dp : dropPointDAO.getListDropPoints())
            strB.append(dp.getName()).append("\n");
        
        return strB.toString();
    }
    
    
    
}
