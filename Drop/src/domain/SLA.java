/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dal.DropPointDAO;
import dal.GenericDAO;
import dal.Table;
import java.sql.SQLException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vascopinho
 */
public class SLA {
    /**
     * DropPoint List
     */
    private List<DropPoint> lsdroppoint;
    
    /**
     * HashMap with DropPoint ID and occupancy rate
     */
    HashMap<Integer, Double> map;
    
    /**
     * Database Connection (DAO)
     */
    private DropPointDAO dpDAO;
    
    public SLA() {
        try {
            map = new HashMap<>();
            dpDAO =(DropPointDAO) persistence.OracleDb
                            .getInstance()
                            .getDAO(Table.DROPPOINT);
            lsdroppoint = dpDAO.getListDropPoints();
            
        } catch (SQLException ex) {
            Logger.getLogger(SLA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Creates a priority map concerning occupancy rate
     * @return HashMap with ID and rate
     */
    public HashMap<Integer, Double> buildPriorityMap(){
        for (DropPoint lsdroppoint1 : lsdroppoint) {
            double priorityRate = dpDAO.getPriority(lsdroppoint1);
            map.put(lsdroppoint1.getId(), priorityRate);
        }
        
        return map;
    }
    
}
