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
public class RegistoMorada {

    private SQLConnection connection;

    private String nextId ="select nvl(max(IDMORADA),0)+1 from Morada";
    private String insert = "INSERT INTO Morada (IDMORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (?, ?, ?, ?)";

    /**
     * Construtor responsavel por cria uma conexao.
     *
     */
    public RegistoMorada() {
        this.connection = OracleDb.getInstance();
    }

    /**
     * Construtor que recebe objecto do tipo SQLConnection
     *
     * @param SQLConnection
     */
    public RegistoMorada(SQLConnection conn) {
        this.connection = conn;
    }

    /**
     * Devolve conecxao utilizada.
     *
     * @return SQLConnection
     */
    public SQLConnection getConnection() {
        return connection;
    }

    /**
     * Permite definir conexao
     *
     * @param connection
     */
    public void setConnection(SQLConnection connection) {
        this.connection = connection;
    }
    
    
    private int nextId(){
        ResultSet executeQuery = connection.executeQuery(nextId);
        
         int lastId = 0;
        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }

    /**
     * Dado um objecto do tipo morada, seus atributos são processados de forma a
     * serem introduzidos na base de dados.
     *
     * @param morada
     * @return boolean
     */
    public boolean registarMorada(Morada morada) {
        PreparedStatement prepareStatement = connection.prepareStatement(insert);
        int id=nextId();

        try {
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, morada.getRua());
            prepareStatement.setString(3, morada.getCodigoPostal());
            prepareStatement.setString(4, morada.getLocalidade());
            morada.setId(id);
            return prepareStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(RegistoMorada.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Devolve novo objecto do tipo morada.
     *
     * @param rua
     * @param codigoPostal
     * @param localidade
     * @return Morada
     */
    public Morada novaMorada(String rua, String codigoPostal, String localidade) {
        return new Morada(rua, codigoPostal, localidade);
    }

    /**
     * Termina a ligação utilizada pela base de dados.
     */
    public void closeConnection() {
        this.connection.closeConnection();
    }

}
