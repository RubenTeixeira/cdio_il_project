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

    private TransaccaoPrateleira transaccao;
    private Prateleira prateleira;
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
        this.transaccao = token.novaTransaccao(manager);
        this.prateleira = token.getPrateleira(manager);
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
        if (this.transaccao.valida())
            this.token.terminar(manager, this.transaccao);
        else
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Invalid data, please verify.");
    }
}
