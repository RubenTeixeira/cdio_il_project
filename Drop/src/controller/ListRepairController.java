/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DropPointDAO;
import dal.RepairDAO;
import dal.Table;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class ListRepairController {

    private int droppoint;
    private SQLConnection manager;
    private RepairDAO repairDAO;
    private DropPointDAO droppointDAO;

    public ListRepairController(String file) {
        manager = persistence.OracleDb.getInstance(file);
        try {
            droppointDAO = (DropPointDAO) manager.getDAO(Table.DROPPOINT);
            repairDAO = (RepairDAO) manager.getDAO(Table.REPAIR);
        } catch (SQLException ex) {
            Logger.getLogger(ListRepairController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a List of all existing DropPoints
     *
     * @return the list of Droppoints
     */
    public List<DropPoint> listDropPoints() {
        return droppointDAO.getListDropPoints();
    }

    /**
     * Select the droppoint chosen by costumer
     *
     * @param dropChosen the droppoint chosen
     */
    public void selectDropPoint(int dropChosen) {
        this.droppoint = dropChosen;
    }
    
    public String getRepairCompleted(DropPoint dp){
        try {
            return repairDAO.getCompletedRepairbyDropPoint(dp);
        } catch (SQLException ex) {
            Logger.getLogger(ListRepairController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
