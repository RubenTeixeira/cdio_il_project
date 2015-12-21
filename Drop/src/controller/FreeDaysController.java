/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import java.sql.SQLException;
import java.util.List;
import dal.DropPointDAO;
import persistence.SQLConnection;
import dal.Table;

/**
 *
 * @author vascopinho
 */
public class FreeDaysController {

    private static SQLConnection con;
    private int idDropPoint,freeDays;
    private DropPointDAO dropPointDao;

    public FreeDaysController() throws SQLException {
        con = persistence.OracleDb.getInstance();
        this.dropPointDao = (DropPointDAO) con.getDAO(Table.DROPPOINT);
    }

    public List<DropPoint> initializeFreeDays() {
        return dropPointDao.getListDropPoints();
    }

    public void selectDroppoint(int id) {
        this.idDropPoint = id;
    }

    public void setFreeDays(int freeDays) {
        this.freeDays = freeDays;
    }

    public boolean updateFreeDays() {
        return this.dropPointDao.setFreeDays(this.idDropPoint,this.freeDays);
    }

}
