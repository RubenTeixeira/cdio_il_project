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
    private SQLConnection manager;
    private EntregaDAO entregaDAO;
    private RecolhaDAO recolhaDAO;
    private PrateleiraDAO prateleiraDAO;
    private TokenDAO tokenDAO;

    public AbrirPrateleiraController(String file) {
        manager = persistence.OracleDb.getInstance(file);
        try {
            tokenDAO = (TokenDAO)manager.getDAO(Table.TOKEN);
            entregaDAO = (EntregaDAO)manager.getDAO(Table.ENTREGA);
            recolhaDAO = (RecolhaDAO)manager.getDAO(Table.RECOLHA);
            prateleiraDAO = (PrateleiraDAO)manager.getDAO(Table.PRATELEIRA);
        } catch (SQLException ex) {
            Logger.getLogger(AbrirPrateleiraController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String iniciaAberturaPrateleira(String tok) {
        
        Token token = tokenDAO.getByCodigo(tok);
        transaccao = token.novaTransaccao();
        if (transaccao instanceof Entrega)
            return iniciaAberturaPrateleiraEntrega((Entrega)transaccao,token);
        if (transaccao instanceof Recolha)
            return iniciaAberturaPrateleiraRecolha((Recolha)transaccao, token);
        return null;
    }

    private String iniciaAberturaPrateleiraEntrega(Entrega entrega, Token token) {
        this.prateleira = this.prateleiraDAO.procurarPrateleiraEntrega(token.getCodigo());
        entrega.setIdPrat(this.prateleira.getId());
        return this.prateleira.toString();
    }
    
    private String iniciaAberturaPrateleiraRecolha(Recolha recolha, Token token) {
        this.prateleira = this.prateleiraDAO.procurarPrateleiraRecolha(token.getCodigo());
        int idEntrega = this.entregaDAO.getIdByToken(token.getCodigo());
        if (idEntrega == -1) {return null;}
        recolha.setIdEntrega(idEntrega);
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
        if (transaccao instanceof Entrega) {
            terminarEntrega();
        } else if (transaccao instanceof Recolha) {
            terminarRecolha();
        }
    }

    private void terminarEntrega() {
        Entrega entrega = (Entrega) this.transaccao;
        entrega.setId(this.entregaDAO.getNextId());
        System.out.println("Entrega:");
        System.out.println(entrega);
        if (entrega.valido())
            this.entregaDAO.insertNew(entrega);
        else
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Entrega inválida. Verifique os dados.");
    }

    private void terminarRecolha() {
        Recolha recolha = (Recolha) this.transaccao;
        recolha.setId(this.recolhaDAO.getNextId());
        System.out.println("Recolha:");
        System.out.println(recolha);
        if (recolha.valido())
            this.recolhaDAO.insertNew(recolha);
        else
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Recolha inválida. Verifique os dados.");
    }
}
