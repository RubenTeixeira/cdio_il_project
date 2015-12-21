/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.DropPoint;
import domain.Gestao;
import domain.Token;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.OracleDb;
import persistence.SQLConnection;
import dal.Table;
import dal.TokenDAO;

/**
 *
 * @author Afonso
 */
public class ExtendTokenController {

    private TokenDAO tokenDAO;
    private DropPoint dropPoint;
    private Connection con;    
    private SQLConnection manager;

    /*
    Construtor da classe que inicializa a conecção com os dados na interface Settings
    e tem acesso à DAO de token
    */
    public ExtendTokenController() {
        try {
            manager=persistence.OracleDb.getInstance();
            tokenDAO = (TokenDAO)manager.getDAO(Table.TOKEN);
        } catch (SQLException ex) {
            Logger.getLogger(ExtendTokenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Token getTokenByCode(String code){
        return getTokenDAO().getByCode(code);
    }

    public TokenDAO getTokenDAO() {
        return tokenDAO;
    }
    
    
}
