/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.DropPoint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.OracleDb;
import persistence.SQLConnection;

/**
 *
 * @author 1140864
 */
public class SeeInfoDAL {

    private SQLConnection connection;

    public SeeInfoDAL() {
        this.connection = OracleDb.getInstance();
    }
    /**
     * Termina a ligação utilizada pela base de dados.
     */
    public void closeConection() {
        connection.closeConnection();
    }
    
    /**
     * Return a list of DropPoints with the DropPoint ID, Name and Adress ID
     *
     * @return list of DropPoints
     */
    public List<DropPoint> getListDropPoints() {

        ResultSet rs = connection.executeQuery("select * from droppoint");
        List<DropPoint> lstDroPoint = new ArrayList<>();

        try {
            while (rs.next()) {
                DropPoint drop = new DropPoint();
                drop.setId(rs.getInt("ID_DROPPOINT"));
                drop.setNome(rs.getString("NOME_DROPPOINT"));
                drop.setIdMorada(rs.getInt("ID_MORADA"));
                lstDroPoint.add(drop);
            }
        } catch (Exception ex) {
            Logger.getLogger(SeeInfoDAL.class.getName()).log(Level.SEVERE, "Error trying to create DropPoints", ex);
        }
        return lstDroPoint;
    }

    public String getDropPointShelvesByDropPointID(int dropID) {
        
        ResultSet rs = connection.executeQuery("select d.DESCRICAO as DIM_DESC, d.ALTURA, d.LARGURA, d.COMPRIMENTO, t.DESCRICAO as TMP_DESC, t.TEMP_MAX, t.TEMP_MIN, COUNT(*) as PRATELEIRAS_DISPONIVEIS \n" +
"                from armario a, prateleira p, classe_temperatura t, classe_dimensao d\n" +
"                where a.ID_DROPPOINT = "+dropID+" \n" +
"                and a.id_armario = p.id_armario\n" +
"                and p.OCUPADO = 0\n" +
"                and p.ATIVO = 1\n" +
"                and p.ID_TEMPERATURA = t.ID_TEMPERATURA\n" +
"                and p.ID_TIPO_DIMENSAO = d.ID_TIPO_DIMENSAO \n" +
"                group by d.ALTURA, d.DESCRICAO, t.DESCRICAO, t.TEMP_MAX, t.TEMP_MIN, d.LARGURA, d.COMPRIMENTO\n" +
"                order by d.COMPRIMENTO");
        String info = "Prateleiras:\n";
        try {
            while (rs.next()) {
                info += String.format("\nDimensão: %s\n"
                        + "CxLxA: %sx%sx%s\n"
                        + "Temperatura: %s\n"
                        + "TMin: %s\n"
                        + "TMax: %s\n"
                        + "Disponiveis: %d\n",
                        rs.getString("DIM_DESC"),
                        rs.getString("COMPRIMENTO"),
                        rs.getString("LARGURA"),
                        rs.getString("ALTURA"),
                        rs.getString("TMP_DESC"),
                        rs.getString("TEMP_MIN"),
                        rs.getString("TEMP_MAX"),
                        rs.getInt("PRATELEIRAS_DISPONIVEIS"));
            }
        } catch (Exception ex) {
            Logger.getLogger(SeeInfoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return info;
    }
    
    public String getDropPointAdressByID(int adressID) {
        ResultSet rs = connection.executeQuery("select RUA, CODPOSTAL, LOCALIDADE from morada where ID_MORADA = " + adressID+ "");
        String adress = "";
        try {
            while (rs.next()) {
                adress = String.format("%s\n"
                        + "%s, %s\n",
                        rs.getString("RUA"),
                        rs.getString("CODPOSTAL"),
                        rs.getString("LOCALIDADE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeeInfoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adress;
    }

    public String getDropPointCoorByAdressID(int adressID) {
        ResultSet rs = connection.executeQuery("select LATITUDE, LONGITUDE from morada where ID_MORADA = " + adressID+ "");
        String adress = "";
        try {
            while (rs.next()) {
                adress = String.format("%s;%s",
                        rs.getString("LATITUDE"),
                        rs.getString("LONGITUDE"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeeInfoDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return adress;
    }
}
