/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 * @param <T>
 */
public abstract class GenericDAO<T> {

    //public abstract int count() throws SQLException; 
    public abstract boolean insertNew(T obj);
    public abstract boolean update(T obj);
    

    protected final String tableName;
    protected Connection con;

    protected GenericDAO(Connection con, String tableName) {
        this.tableName = tableName;
        this.con = con;
    }

}
