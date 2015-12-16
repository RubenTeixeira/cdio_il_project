package controller;

import domain.Cabinet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.CabinetDAO;
import persistence.SQLConnection;
import persistence.Table;

/**
 *
 * @author 1130874
 */
public class MaintenanceDPController {

    /**
     * The Connection
     */
    private SQLConnection manager;

    /**
     * The Cabinet
     */
    private Cabinet cabinet;
    /**
     * Cabinet Interface DataBase
     */
    private CabinetDAO cabinetDAO;

    /**
     * Creates a new Controller with a settings by @param
     *
     * @param file The file of settings to create connection
     */
    public MaintenanceDPController(String file)
    {
        manager = persistence.OracleDb.getInstance(file);
        try
        {
            cabinetDAO = (CabinetDAO) manager.getDAO(Table.ARMARIO);
        } catch (Exception e)
        {
            Logger.getLogger(MaintenanceDPController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Get the List of Cabinets of a relative DropPoint
     *
     * @param dropID DropPoint
     * @return List of Cabinets
     */
    public List<Cabinet> getListOfCabinets(int dropID)
    {
        return cabinetDAO.getListOfCabinets(dropID);
    }

    public void selectCabinet(Cabinet cabinet)
    {
        this.cabinet = cabinet;
    }

    /**
     * Put the Cabinet in Maintenance
     *
     * @return True if sucess False if not sucess
     */
    public boolean putInMaintenance()
    {
        cabinet.setMaintenance(1);
        return cabinetDAO.putInMaintenance(cabinet);
    }

    /**
     * Stop the Job of Maintenance of Cabinet
     *
     * @return True if Sucess False is not Sucess
     */
    public boolean stopMaintenance()
    {
        cabinet.setMaintenance(0);
        return cabinetDAO.stopMaintenance(cabinet);
    }
}

