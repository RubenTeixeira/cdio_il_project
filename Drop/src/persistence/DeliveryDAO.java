/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Delivery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class DeliveryDAO extends GenericDAO<Delivery> {

    private final static String TABLENAME = "ENTREGA";

    public DeliveryDAO(Connection con) {
        super(con, TABLENAME);
    }

    public int getDeliveryIdByToken(String token) {
        String qry = "select e.id_entrega from token t, reserva r, entrega e"
                + "     where t.id_reserva = r.id_reserva"
                + "         and r.id_reserva = e.id_reserva"
                + "         and t.codigo = '" + token + "'";
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("id_entrega");
            } catch (SQLException ex) {
            }
        }
        return -1;
        
    }

    /**
     * Retrieves incremental ID for this object correponding Table
     * @return int ID
     */
    public int getNextId() {
        String query = "select nvl(max(id_entrega),0)+1 as id from entrega";
        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("id");
            } catch (SQLException ex) {
            }
        }
        return -1;
    }

    @Override
    public boolean insertNew(Delivery obj) {
        String query = "INSERT INTO ENTREGA ("
                + "ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) "
                + " VALUES (" + obj.getDeliveryID() + "," + obj.getCellID() + "," + obj.getReservationID() + "," + obj.getTokenID() + ","
                + "TO_DATE('" + obj.getOpenedDate() + "', 'dd-mm-yyyy HH24:MI'),"
                + "TO_DATE('" + obj.getClosedDate() + "', 'dd-mm-yyyy HH24:MI'))";
        ResultSet rs = executeQuery(query);
        return rs != null;
    }

    @Override
    public boolean update(Delivery obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Delivery obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Delivery get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getClientEmail(Delivery delivery) {
        int idReservation = delivery.getReservationID();

        String qry = "select c.email from cliente c, reserva r"
                + "     where r.id_reserva = " + idReservation
                + "AND r.id_cliente = c.id_cliente";
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                return rs.getString("email");
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public String getPickUpToken(Delivery delivery) {
        int idReservation = delivery.getReservationID();

        String qry = "select t.codigo from token t"
                + "     where t.id_reserva = " + idReservation
                + "AND t.id_tipo_token = 2"
                + "AND t.ativo = 1";
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                return rs.getString("codigo");
            } catch (SQLException ex) {
            }
        }
        return null;
    }

}
