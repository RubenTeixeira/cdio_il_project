/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import domain.Notice;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dal.DropPointDAO;
import persistence.SQLConnection;
import dal.Table;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class AnomalyReportController {

    private DropPoint dropPoint;
    private String description;
    private final SQLConnection manager;
    
    public AnomalyReportController() {
        manager = persistence.OracleDb.getInstance();
    }
    
    /**
     * Starts Anomaly Report process.
     * @param descr Body of the notice
     * @param id DropPoint id
     * @return true if data is valid, false otherwise
     */
    public boolean beginAnomalyReport(String descr, int id) {
        this.description = descr;
        try {
            this.dropPoint = ((DropPointDAO)manager.getDAO(Table.DROPPOINT)).get(id);
            if (this.dropPoint == null)
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(AnomalyReportController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * Dispatch Anomaly Report notice
     * @return truee if dispatched successfull, false otherwise
     */
    public boolean sendAnomalyReport() {
        Notice notice = new Notice();
        StringBuilder strBuild = new StringBuilder();
        
        strBuild.append("This is an anomaly notification for the Authorities.\n");
        strBuild.append("\nLocation: "+this.dropPoint.getName()+"\n");
        strBuild.append("\nDescription:\n");
        strBuild.append(this.description+"\n");
        strBuild.append("\nReporter:\nDropPoint Inc.\n\nBest Regards.");

        notice.setMessage(strBuild.toString());
        notice.setEmail("vasco.pinho@gmail.com");
        
        return notice.dispatchMail();
    }

    
}
