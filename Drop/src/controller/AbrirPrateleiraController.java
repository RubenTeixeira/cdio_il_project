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
public class AbrirPrateleiraController {

    private CellTransaction transaccao;
    private Cell prateleira;
    private Token token;
    private SQLConnection manager;
    private TokenDAO tokenDAO;

    public AbrirPrateleiraController(String file) {
        manager = persistence.OracleDb.getInstance(file);
        try {
            tokenDAO = (TokenDAO) manager.getDAO(Table.TOKEN);
        } catch (SQLException ex) {
            Logger.getLogger(AbrirPrateleiraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String iniciaAberturaPrateleira(String tok) {
        this.token = tokenDAO.getByCodigo(tok);
        this.transaccao = token.newTransaction(manager);
        this.prateleira = token.getCell(manager);
        return this.prateleira.toString();
    }

    public void abrePrateleira() {
        this.transaccao.setDateOpen();
    }

    public void fechaPrateleira() {
        this.transaccao.setDateClose();
        terminarTransaccao();
    }

    private void terminarTransaccao() {
        if (this.transaccao.validate())
            this.token.close(manager, this.transaccao);
        else
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Invalid data, please verify.");
    }
}
