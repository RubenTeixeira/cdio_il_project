/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class Management {

    private SQLConnection bd;

    /**
     * Constructor parameters. Initializes and adds object of type.
     * SQLConnection
     */
    public Management() {
        this.bd = persistence.OracleDb.getInstance();
    }

    public Management(SQLConnection con) {
        this.setBd(con);
    }

    public SQLConnection getBd() {
        return bd;
    }

    public void setBd(SQLConnection bd) {
        this.bd = bd;
    }

    /**
     * Returns list the type of refrigerated shelves.
     *
     * @return list
     */
    public List<String> ListPreferencesTemperature() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_TEMPERATURA");
        List<String> op = new ArrayList<>();

        try {

            while (executeQuery.next()) {

                op.add("Prateleira ID: "
                        + executeQuery.getString("ID_TEMPERATURA") + " "
                        + executeQuery.getString("DESCRICAO") + "["
                        + executeQuery.getString("TEMP_MAX") + "|"
                        + executeQuery.getString("TEMP_MIN"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, "Não foi possivel buscar os tipo de prateleira", ex);
        }

        return op;
    }

    /**
     * Returns list the type of available dimensions.
     *
     * @return String
     */
    public List<String> ListPreferencesDimension() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_DIMENSAO");
        List<String> op = new ArrayList<>();

        try {

            while (executeQuery.next()) {

                op.add("Cell ID: "
                        + executeQuery.getString("ID_TIPO_DIMENSAO") + " of type "
                        + executeQuery.getString("DESCRICAO")
                        + executeQuery.getString("ALTURA") + "x"
                        + executeQuery.getString("LARGURA") + "x"
                        + executeQuery.getString("COMPRIMENTO"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE,
                    "Could not get the dimensions.", ex);
        }

        return op;
    }

    /**
     * Given the parameters, this add customer reservation.
     *
     * @param idCliente
     * @param idDropPoint
     * @param idTemperatura
     * @param idDimensao
     * @return int
     */
    public int reserveCell(int idCliente,
            int idDropPoint,
            int idTemperatura,
            int idDimensao) {
        String m = "select nvl(max(ID_RESERVA),0)+1 FROM RESERVA";
        ResultSet executeQuery = bd.executeQuery(m);
        int lastId = 0;

        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "INSERT INTO "
                + "Reserva(ID_RESERVA,ID_DROPPOINT,ID_CLIENTE,ID_TEMPERATURA,ID_TIPO_DIMENSAO) "
                + "VALUES (?,?,?,?,?)";

        PreparedStatement prepareStatement = bd.prepareStatement(query);

        try {

            prepareStatement.setInt(1, lastId);
            prepareStatement.setInt(2, idDropPoint);
            prepareStatement.setInt(3, idCliente);
            prepareStatement.setInt(4, idTemperatura);
            prepareStatement.setInt(5, idDimensao);

            prepareStatement.execute();

            return lastId;

        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE,
                    "It was not possible to register the reservation", ex);
        }
        return 0;
    }

    /**
     * Given the id of booking, this seeks his token and to which correspond
     *      * returning code.
     *
     * @param idReserva
     * @return String
     */
    public String tokenReferentReservaId(int idReserva) {
        String query = "select nvl(max(id_token),0)+1 FROM TOKEN";
        ResultSet executeQuery = bd.executeQuery(query);
        int lastId = 0;
        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
        }

        query = "select free_days from DropPoint "
                + "INNER JOIN Reserva ON "
                + "Reserva.id_DropPoint=DropPoint.id_DropPoint "
                + "WHERE id_reserva=" + idReserva;

        executeQuery = bd.executeQuery(query);

        int freeDays = 0;
        try {
            while (executeQuery.next()) {
                freeDays = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE, null, ex);
        }

        String select = "Insert into "
                + "TOKEN(id_token,data_geracao,data_validade,id_tipo_token,ativo,id_reserva,codigo) "
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement prepareStatement = bd.prepareStatement(select);

        try {

            Calendar instance = Calendar.getInstance();

            Date now = new Date(instance.getTimeInMillis());
            instance.add(Calendar.MONTH, 1);
            instance.add(Calendar.DAY_OF_MONTH, freeDays);

            Date validade = new Date(instance.getTimeInMillis());
            System.out.println(validade);
            String token = UUID.randomUUID().toString().substring(0, 8);
            prepareStatement.setInt(1, lastId);
            prepareStatement.setDate(2, now);
            prepareStatement.setDate(3, validade);
            prepareStatement.setInt(4, 1);
            prepareStatement.setInt(5, 1);
            prepareStatement.setInt(6, idReserva);
            prepareStatement.setString(7, token);

            prepareStatement.execute();

            String token1 = UUID.randomUUID().toString().substring(0, 8);
            prepareStatement.setInt(1, lastId + 1);
            prepareStatement.setInt(4, 2);
            prepareStatement.setString(7, token1);

            prepareStatement.execute();

            return token;

        } catch (SQLException ex) {
            Logger.getLogger(Management.class.getName()).log(Level.SEVERE,
                    "Could not make the reservation.", ex);
        }

        return null;
    }

    public void closeConection() {
        bd.closeConnection();
    }

}
