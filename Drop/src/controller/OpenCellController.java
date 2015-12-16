/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.*;

/**
 *
 * @author 1140864
 */
public class OpenCellController {

    private CellTransaction transaction;
    private Cell cell;
    private Token token;
    private SQLConnection manager;
    private TokenDAO tokenDAO;

    public OpenCellController(String file) {
        manager = persistence.OracleDb.getInstance(file);
        try {
            tokenDAO = (TokenDAO) manager.getDAO(Table.TOKEN);
        } catch (SQLException ex) {
            Logger.getLogger(OpenCellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String beginOpenCell(String tok) {
        this.token = tokenDAO.getByCodigo(tok);
        this.transaction = token.newTransaction(manager);
        this.cell = token.getCell(manager);
        return this.cell.toString();
    }

    public void openCell() {
        this.transaction.setDateOpen();
    }

    public void closeCell() {
        this.transaction.setDateClose();
        closeTransaction();
    }

    private void closeTransaction() {
        if (this.transaction.validate())
            this.token.close(manager, this.transaction);
        else
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Invalid data, please verify.");
    }
}
