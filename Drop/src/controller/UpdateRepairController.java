package controller;

import dal.DropPointDAO;
import dal.RepairDAO;
import dal.Table;
import domain.DropPoint;
import domain.Repair;
import domain.RepairPlan;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import persistence.SQLConnection;

/**
 *
 * @author André 1130874
 */
public class UpdateRepairController {

    SQLConnection manager;
    RepairDAO repairDAO;
    DropPointDAO dropPointDAO;
    RepairPlan repairPlan;
    Repair repair;
    
    public UpdateRepairController(String file) throws SQLException
    {
        this.manager = persistence.OracleDb.getInstance(file);
        this.repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
        this.dropPointDAO = (DropPointDAO) manager.getDAO(Table.DROPPOINT);
    }

    public List<Repair> listRepairCurrentDay() throws SQLException
    {
        this.repairPlan = repairDAO.getCurrentRepairPlan();
        return repairPlan.getPlanPath();
    }

    public void selectRepair(Repair choosenRepair)
    {
        this.repair = choosenRepair;
    }

    public DropPoint getDropPointByRepair(Repair r) throws SQLException
    {
        int idDrop = repairDAO.getCorrespondingDropPointID(r);
        return this.dropPointDAO.get(idDrop);
    }

    public List<String> getIncidentsByRepair(Repair r) throws SQLException
    {
        return repairDAO.getRepairIncidents(r);
        
    }

    public void updateRepairInfo(Date date, String partUsed, String observ)
    {
        this.repair.setRepairDate(date);
        this.repair.setPartsUsed(partUsed);
        this.repair.setObservations(observ);
        this.repairDAO.update(this.repair);
    }
}
