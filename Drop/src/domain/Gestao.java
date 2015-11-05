/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class Gestao {

    private SQLConnection bd;

    /**
     * Construtor sem parâmetros.
     * Inicializa e agrega objecto do tipo SQLConnection
     */
    public Gestao() {
        this.bd = persistence.OracleDb.getInstance();
    }

    /**
     * Permite listar DropPoints
     * @return Lista do tipo String dos DropPoints do Sistema 
     */
    public List<String> listarDropPoint() {
        ResultSet executeQuery = bd.executeQuery("SELECT DROPPOINT.IDDROPPOINT, MORADA.RUA FROM DROPPOINT INNER JOIN MORADA ON MORADA.IDMORADA=DROPPOINT.IDDROPPOINT");
        List<String> aux = new ArrayList<>();
        try {

            while (executeQuery.next()) {
                String temp = "DropPoint ID:" + String.valueOf(executeQuery.getString("IDDROPPOINT"))
                        + " com morada:" + executeQuery.getString("RUA") + "\n";
                aux.add(temp);
                
            }
            return aux;
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Permite listar as entregas de um DropPoint
     * @param idDropPoint 
     * @return Lista do tipo String das entregas
     */
    public List<String> listarEntregas(int idDropPoint) {
        return null;
    }

    /**
     * Permite listar as Recolhas de um DropPoint
     * @param idDropPoint
     * @return Lista do tipo String das recolhas
     */
    public List<String> listarRecolhidas(int idDropPoint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
