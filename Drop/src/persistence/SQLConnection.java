/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.ResultSet;

/**
 *
 * @author vascopinho
 */
public interface SQLConnection {

    public ResultSet executeQuery(String query);
    public boolean closeConnection();
}
