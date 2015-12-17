/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cabinet;
import domain.Cell;
import domain.DropPoint;
import domain.Gestao;
import java.sql.SQLException;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.CabinetDAO;
import persistence.CellDAO;
import persistence.DropPointDAO;
import persistence.OracleDb;
import persistence.SQLConnection;
import persistence.Table;

/**
 *
 * @author nuno
 */
public class MakeMaintenanceController {
    
    private Gestao gestao;
    private Cabinet cabinet;
    private CabinetDAO cabinetDAO;
    private CellDAO cellDAO;
    private DropPointDAO dropDAO;
    private Cell cell;
    
    public MakeMaintenanceController(List<String> file) throws SQLException {
        SQLConnection instance = OracleDb.getInstance(file.get(0),file.get(1), file.get(2), file.get(3));
        this.cabinetDAO = (CabinetDAO) instance.getDAO(Table.CABINET);
        this.cellDAO = (CellDAO) instance.getDAO(Table.CELL);
        this.dropDAO = (DropPointDAO) instance.getDAO(Table.DROPPOINT);
    }
    
    public List<DropPoint> listDropPoints(){
        return this.dropDAO.getListDropPoints();
    }
    
    public List<Cabinet> listCabinetsInMaintenance(int dropID) {
        List<Cabinet> listOfCabinets = this.cabinetDAO.getListOfCabinets(dropID);
        listOfCabinets.removeIf(p -> p.getMaintenance() == 0);
        return listOfCabinets;
    }
    
    public void selectCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
    
    public Deque<Cell> cellsToOpen() throws SQLException {
        return this.cellDAO.cellsToOpen(this.cabinet);
    }
    
    public boolean openCell(Cell cell) throws SQLException {
        this.cell = cell;
        return this.cellDAO.updateCell(this.cell,CellDAO.OPEN);
    }
    
    public boolean insertReport(String report) {
        return this.cellDAO.insertReport(this.cell);
    }
    
    public boolean closeCell() throws SQLException {
        return this.cellDAO.updateCell(cell, CellDAO.CLOSE);
    }
    
}
