package controller;

import dal.RepairDAO;
import dal.Table;
import domain.Repair;
import domain.RepairPlan;
import java.sql.SQLException;
import java.util.List;
import persistence.SQLConnection;

/**
 *
 * @author André
 */
public class UpdateRepairController {

    SQLConnection manager;
    RepairDAO repairDAO;
    RepairPlan repairPlan;
    
    public UpdateRepairController(String file) throws SQLException
    {
        this.manager = persistence.OracleDb.getInstance(file);
        this.repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
    }

    public List<Repair> listRepairCurrentDay()
    {
        this.repairPlan = repairDAO.getCurrentRepairPlan();
        return repairPlan.getPlanPath();
    }

}
