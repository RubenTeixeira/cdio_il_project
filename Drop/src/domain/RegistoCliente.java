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

    private String nextId = "select nvl(max(id_cliente),0)+1 from cliente";
    private String delete = "DELETE FROM CLIENTE WHERE IDCLIENTE=?";
    private String login = "SELECT * FROM CLIENTE WHERE USERNAME=? AND UPASSWORD=?";
    private String insert = "Insert Into CLIENTE (id_Cliente,NOME,NIF,EMAIL,TELEMOVEL,USERNAME,UPASSWORD,IDMORADA)"
            + " VALUES (?,?,?,?,?,?,?,?)";
    private String update = "Update CLIENTE set USERNAME=?, PASSWORD=?"
            + " WHERE IDCLIENTE =?";

    public RegistoCliente(SQLConnection conn) {
        this.conn = conn;
    }
    
    public RegistoCliente() {
        conn = OracleDb.getInstance();
    }

    public SQLConnection getConn() {
        return conn;
    }

    public void setConn(SQLConnection conn) {
        this.conn = conn;
    }
    
    private int nextClientId() {
        ResultSet executeQuery = conn.executeQuery(nextId);
        int nextId=0;
        try {
            while(executeQuery.next()){
                nextId=executeQuery.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return nextId;
    }

    public boolean registarCliente(Cliente novoCliente) {
        PreparedStatement prepareStatement = conn.prepareStatement(insert);
        try {

            prepareStatement.setInt(1, nextClientId());
            prepareStatement.setString(2, novoCliente.getNome());
            prepareStatement.setInt(3, novoCliente.getNIF());
            prepareStatement.setString(4, novoCliente.getEmail());
            prepareStatement.setInt(5, novoCliente.getTelemovel());
            prepareStatement.setString(6, novoCliente.getUsername());
            prepareStatement.setString(7, novoCliente.getPassword());
            prepareStatement.setInt(8, novoCliente.getId());

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
    
    public void closeConection(){
        conn.closeConnection();
    }

}
