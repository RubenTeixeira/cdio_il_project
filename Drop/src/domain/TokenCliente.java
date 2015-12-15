/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.EntregaDAO;
import persistence.PrateleiraDAO;
import persistence.RecolhaDAO;
import persistence.SQLConnection;
import persistence.Table;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class TokenCliente extends TokenImpl {

    public TokenCliente(int id, String codigo, int idReserva) {
        super(id, codigo, idReserva);
    }

    @Override
    public TransaccaoPrateleira novaTransaccao(SQLConnection manager) {
        int idEntrega;
        try {
            idEntrega = ((EntregaDAO)manager.getDAO(Table.ENTREGA)).getIdByToken(getCodigo());
        } catch (SQLException ex) {
            return null;
        }
        return new Recolha(idEntrega, getId());
    }

    @Override
    public Prateleira getPrateleira(SQLConnection manager) {
        Prateleira prateleira = null;
        try {
            prateleira = ((PrateleiraDAO)manager.getDAO(Table.PRATELEIRA)).procurarPrateleiraRecolha(getCodigo());
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
        return prateleira;
    }

    @Override
    public void terminar(SQLConnection manager, TransaccaoPrateleira transaccao) {
        RecolhaDAO recolhaDAO;
        try {
            recolhaDAO = (RecolhaDAO)manager.getDAO(Table.RECOLHA);
            transaccao.setId(recolhaDAO.getNextId());
            recolhaDAO.insertNew((Recolha)transaccao);
            
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
        
    }
    
}
