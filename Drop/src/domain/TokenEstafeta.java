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
import persistence.SQLConnection;
import persistence.Table;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class TokenEstafeta extends TokenImpl {

    public TokenEstafeta(int id, String codigo, int idReserva) {
        super(id, codigo, idReserva);
    }
    
    @Override
    public TransaccaoPrateleira novaTransaccao(SQLConnection manager) {
        Prateleira prateleira = getPrateleira(manager);
        if (prateleira != null)
            return new Entrega(prateleira.getId(), getIdReserva(), getId());
        return null;
    }

    @Override
    public Prateleira getPrateleira(SQLConnection manager) {
        Prateleira prateleira = null;
        try {
            prateleira = ((PrateleiraDAO)manager.getDAO(Table.PRATELEIRA)).procurarPrateleiraEntrega(getCodigo());
        } catch (SQLException ex) {
        }
        return prateleira;
    }

    @Override
    public void terminar(SQLConnection manager, TransaccaoPrateleira transaccao) {
        EntregaDAO entregaDAO;
        Entrega entrega = (Entrega) transaccao;
        try {
            entregaDAO = (EntregaDAO)manager.getDAO(Table.ENTREGA);
            transaccao.setId(entregaDAO.getNextId());
            entregaDAO.insertNew((Entrega)transaccao);
            String email = entregaDAO.getEmailCliente(entrega);
            String tokenRecolha = entregaDAO.getTokenRecolha(entrega);
            Notificacao.enviarEmail(email, tokenRecolha);
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
    }
    
}
