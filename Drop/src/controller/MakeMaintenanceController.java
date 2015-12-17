/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cabinet;
import domain.Cell;
import domain.Gestao;
import java.sql.SQLException;
import java.util.Deque;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.CellDAO;
import persistence.Table;

/**
 *
 * @author nuno
 */
public class MakeMaintenanceController {
    
    private Gestao gestao;
    private Cabinet cabinet;
    private CellDAO cellDAO;
    private Cell cell;
    
    public MakeMaintenanceController() throws SQLException {
        this.gestao = new Gestao();
        this.cellDAO = (CellDAO) this.gestao.getBd().getDAO(Table.PRATELEIRA);
    }
    
    public List<Cabinet> listCabinetsInMaintenance() {
        return null;
    }
    
    public void selectCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }
    
    public Deque<Cell> cellsToOpen() throws SQLException {
        return this.cellDAO.cellsToOpen(this.cabinet);
    }
    
    public boolean openCell(Cell cell) {
        this.cell = cell;
        return this.cellDAO.openCell(this.cell);
    }
    
    public boolean insertReport(String report) {
        return this.cellDAO.insertReport(this.cell);
    }
    
    public boolean closeCells() {
        return this.cellDAO.closeCell(this.cell);
    }
    
}
