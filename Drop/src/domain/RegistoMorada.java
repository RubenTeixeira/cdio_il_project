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
    private SQLConnection conn;

    private String insert="INSERT INTO Morada (IDMORADA,RUA,CODPOSTAL,LOCALIDADE) VALUES (?, ?, ?, ?)";

    
    public RegistoMorada() {
        this.conn=OracleDb.getInstance();
    }

    public RegistoMorada(SQLConnection conn) {
        this.conn = conn;
    }
    
    public boolean registarMorada(Morada morada){
        String m="SELECT * FROM MORADA ORDER BY idmorada";
        ResultSet executeQuery = conn.executeQuery(m);
        int lastId=0;
        try {
            while(executeQuery.next()){
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         PreparedStatement prepareStatement = conn.prepareStatement(insert);
         
        try {
            prepareStatement.setInt(1, lastId);
            prepareStatement.setString(2, morada.getRua());
            prepareStatement.setString(3, morada.getCodigoPostal());
            prepareStatement.setString(4, morada.getLocalidade());
            morada.setId(lastId);
            return prepareStatement.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistoMorada.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       return false;
    }
    
    public Morada novaMorada(String rua, String codigoPostal, String localidade){
        return new Morada(rua, codigoPostal, localidade);
    }
    
    
    
    
}
