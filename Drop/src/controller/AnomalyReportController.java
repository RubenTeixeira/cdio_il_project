/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import domain.Notice;
import persistence.SQLConnection;
import persistence.Table;

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
    
    public boolean beginAnomalyReport(String descr, int id) {
        if (descr.length() > 500)
            return false;
        this.description = descr;
        //this.dropPoint = ((DropPointDAO)manager.getDAO(Table.DROPPOINT)).get(id);
        return true;
    }
    
    public boolean sendAnomalyReport() {
        Notice notice = new Notice();
        StringBuilder strBuild = new StringBuilder();
        
        strBuild.append("This is an anomaly notification for the auothorities.\n");
        strBuild.append("\nLocation: TBA\n");
        strBuild.append("\nDescription:\n");
        strBuild.append(this.description);
        strBuild.append("\nReporter:\nDropPoint Inc.\n\nBest Regards.");

        notice.setMessage(strBuild.toString());
        notice.setEmail("1140780@isep.ipp.pt");
        
        return notice.dispatchMail();
    }

    
}
