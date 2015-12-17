/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Gestao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 * @param <T>
 */
public abstract class GenericDAO<T> {

    protected final String tableName;
    protected Connection con;

    /**
     * Generic DAO constructor, requiring the connection used and the correponding Table name
     * @param con Connection to be used by the DAO
     * @param tableName Corresponding Table name
     */
    protected GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }
    
    /**
     * Inserts Object of wich the DAO is responsible into the respective Table
     * @param obj Object the DAO is responsible for
     * @return true if successfull, false otherwise
     */
    public abstract boolean insertNew(T obj);
    
    /**
     * Updates Object of wich the DAO is responsible in the respective Table
     * @param obj Object the DAO is responsible for
     * @return true if successfull, false otherwise
     */
    public abstract boolean update(T obj);
    
    /**
     * Deletes Object of wich the DAO is responsible in the respective Table
     * @param obj Object the DAO is responsible for
     */
    public abstract void delete(T obj);
    
    /**
     * Returns Object the DAO is responsible for
     * @param id ID of the correponding record in the Object's Table
     * @return Object
     */
    public abstract T get(int id);
    
    
    /**
     * Executes a query given its SQL statement, validates the ResultSet and returns it
     * @param query String containing the SQL statement to execute
     * @return ResultSet object
     */
    public ResultSet executeQuery(String query) {
        PreparedStatement stmnt;
        ResultSet rs = null;
        try {
            stmnt = this.con.prepareStatement(query);
            rs = stmnt.executeQuery();
            if (!rs.isBeforeFirst() ) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Error contacting the Database.");
        }
        return rs;
    }
}
