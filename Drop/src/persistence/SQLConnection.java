/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author vascopinho
 */
public interface SQLConnection {

    public GenericDAO getDAO(Table t) throws SQLException;
    public ResultSet executeQuery(String query);
    public PreparedStatement prepareStatement(String prepare);
    public boolean executeUpdate(String query);
    public boolean closeConnection();
    
    
}
