/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistence.OracleDb;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class ConsultarOcupacaoEntregasController {
    
    private SQLConnection con;
    private ResultSet rs;
    
    public List<String> iniciarConsultaEntregasRecolhasDroppoint() throws SQLException{
        SQLConnection instance = OracleDb.getInstance();
        ResultSet executeQuery = instance.executeQuery("SELECT DROPPOINT.IDDROPPOINT, MORADA.RUA FROM DROPPOINT INNER JOIN MORADA ON MORADA.IDMORADA=DROPPOINT.IDDROPPOINT");
        List<String> aux = new ArrayList<>();
        ResultSetMetaData rsm = executeQuery.getMetaData();
        
       
        while(executeQuery.next()){
            String temp="DropPoint ID:" + String.valueOf(executeQuery.getString("IDDROPPOINT")) + 
                        " com morada:" + executeQuery.getString("RUA") + "\n";
            aux.add(temp);
            
        }
        return aux;
    }
    
    public static void seleccionarDroppoint(){
        
    }
    
    public static void getListaRegistoEntregues(){
        
    }
    public static void getListaRegistoRecolhidoas(){
        
    }
    
    

}
