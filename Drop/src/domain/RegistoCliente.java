/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private String delete = "DELETE FROM CLIENTE WHERE IDCLIENTE=?";
    private String login = "SELECT * FROM CLIENTE WHERE USERNAME=? AND UPASSWORD=?";
    private String insert = "Insert Into CLIENTE (IDCLIENTE,NOME,NIF,USERNAME,UPASSWORD,MORADA,EMAIL,TELEMOVEL)"
            + " VALUES (?,?,?,?,?,?,?,?))";
    private String update = "Update CLIENTE set USERNAME=?, PASSWORD=?"
            + " WHERE IDCLIENTE =?";

    public RegistoCliente() {
        conn = OracleDb.getInstance();
    }

    public boolean registarCliente(Cliente novoCliente) {
        PreparedStatement prepareStatement = conn.prepareStatement(insert);
        try {

            prepareStatement.setInt(1, 66);
            prepareStatement.setString(2, novoCliente.getNome());
            prepareStatement.setInt(3, novoCliente.getNIF());
            prepareStatement.setString(4, novoCliente.getUsername());
            prepareStatement.setString(5, novoCliente.getPassword());
            prepareStatement.setInt(6, 1);
            prepareStatement.setString(7, novoCliente.getEmail());
            prepareStatement.setInt(8, novoCliente.getTelemovel());

            return prepareStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(RegistoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Cliente login(String username, String password) {
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(login);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);

            ResultSet executeQuery = prepareStatement.executeQuery();

            if (executeQuery.next()) {
                return novoCliente(executeQuery.getInt("ID_CLIENTE"),
                        executeQuery.getString("NOME"),
                        executeQuery.getInt("NIF"),
                        executeQuery.getString("USERNAME"),
                        executeQuery.getString("UPASSWORD"),
                        null,
                        executeQuery.getString("EMAIL"),
                        executeQuery.getInt("TELEMOVEL"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RegistoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Cliente novoCliente(int id, String nome, int NIF, String username, String password, Morada morada, String email, int telemovel) {
        return new Cliente(id, nome, NIF, username, password, morada, email, telemovel);
    }

}
