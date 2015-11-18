/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.OracleDb;
import persistence.SQLConnection;

/**
 *
 * @author nuno
 */
public class RegistoCliente {

    private SQLConnection conn;

    private String insert = "Insert Into CLIENTE (IDCLIENTE,NOME,NIF,USERNAME,PASS,MORADA)"
                            + " VALUES (?,?,?,?))";
    
    private String update = "Update CLIENTE set USERNAME=?, PASSWORD=?"
                            + " WHERE IDCLIENTE =?";
    
    private String delete =  "DELETE FROM CLIENTE WHERE IDCLIENTE=?";

    public RegistoCliente() {
        conn = OracleDb.getInstance();
    }

    public boolean registarCliente(Cliente novoCliente) {
        PreparedStatement prepareStatement = conn.prepareStatement(insert);
        try {

            prepareStatement.setString(1, novoCliente.getEmail());
            prepareStatement.setInt(2, novoCliente.getTelemovel());
            prepareStatement.setString(3, novoCliente.getUsername());
            prepareStatement.setString(4, novoCliente.getPassword());

            return prepareStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RegistoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Cliente login(String username, String password) {
        return null;
    }

}
