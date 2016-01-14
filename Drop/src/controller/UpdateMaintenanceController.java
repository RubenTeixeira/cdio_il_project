package controller;

import dal.DropPointDAO;
import dal.MaintenanceDAO;
import dal.Table;
import domain.DropPoint;
import domain.Maintenance;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import persistence.SQLConnection;

/**
 *
 * @author 1130874
 */
public class UpdateMaintenanceController {

    /**
     * SQL Connection
     */
    private SQLConnection manager;
    /**
     * Maintenance DAO
     */
    private MaintenanceDAO maintenanceDAO;
    /**
     * DropPoint DAO
     */
    private DropPointDAO dropPointDAO;
    /**
     * The selected maintenance
     */
    private Maintenance maintenance;

    /**
     * Constructor for new UpdateMaintenanceController
     *
     * @param file File for the Connection
     * @throws SQLException
     */
    public UpdateMaintenanceController(String file) throws SQLException
    {
        this.manager = persistence.OracleDb.getInstance(file);
        this.maintenanceDAO = (MaintenanceDAO) manager.getDAO(Table.MANUTENCAO);
        this.dropPointDAO = (DropPointDAO) manager.getDAO(Table.DROPPOINT);

    }

    /**
     * Returns the list of Maintenances in a CurrentDay
     *
     * @return List of Maintenances
     * @throws SQLException
     */
    public List<Maintenance> listMaintenancesCurrentDay() throws SQLException
    {
        return this.maintenanceDAO.getListMaintenanceCurrentDay();
    }

    /**
     * Gets the DropPoint associate at the Maintenance
     *
     * @param id the DropPoint ID
     * @return DropPoint
     */
    public DropPoint getDropPointByID(int id)
    {
        return this.dropPointDAO.get(id);
    }

    /**
     * Gets the Tasks of a Relative DropPoint by ID
     *
     * @param id The DropPoint ID
     * @return List of Tasks
     * @throws SQLException
     */
    public List<String> getTaskOfDropPoint(int id) throws SQLException
    {
        return this.maintenanceDAO.getTasksOfDropPoint(id);
    }

    /**
     * Starts the Date of the maintenance
     */
    public void setStartDateMaintenance()
    {
        this.maintenance.setStartDate(new Date());
    }

    /**
     * Finishes the date of the maintenance
     */
    public void setFinishDateMaintenance()
    {
        this.maintenance.setFinishDate(new Date());
        this.maintenanceDAO.updateDates(maintenance);
        //.....
    }

    public void selectMaintenance(Maintenance choosenMaintenence)
    {
        this.maintenance= choosenMaintenence;
    }
}
