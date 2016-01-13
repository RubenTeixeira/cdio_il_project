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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vascopinho
 */
public class SLA {

    private static DropPointDAO getDropPointDAO() {
        DropPointDAO dpdao = null;

        try {

            dpdao = (DropPointDAO) persistence.OracleDb
                    .getInstance()
                    .getDAO(Table.DROPPOINT);

        } catch (SQLException ex) {
            Logger.getLogger(SLA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dpdao;
    }

    /**
     * Creates a priority map concerning occupancy rate
     *
     * @return HashMap with ID and rate
     */
    public static Map<DropPoint, Float> buildPriorityMap() {
        DropPointDAO dpDAO = getDropPointDAO();
        if (dpDAO != null) {
            Map<DropPoint, Float> map = new HashMap<>();
            List<DropPoint> lsdroppoint = dpDAO.getListDropPoints();
            for (DropPoint lsdroppoint1 : lsdroppoint) {
                float priorityRate = dpDAO.getPriority(lsdroppoint1);
                map.put(lsdroppoint1, priorityRate);
            }

            return map;

        } else {
            return null;
        }
    }

}
