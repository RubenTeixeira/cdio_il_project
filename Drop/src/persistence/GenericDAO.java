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


    public abstract boolean insertNew(T obj);
    public abstract boolean update(T obj);
    public abstract void delete(T obj);
    public abstract T get(int id);
    

    protected final String tableName;
    protected Connection con;

    protected GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }
    
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
