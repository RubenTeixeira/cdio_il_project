/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.GenericDAO;
import domain.Delivery;
import domain.DropPoint;
import domain.Token;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class DeliveryDAO extends GenericDAO<Delivery> {

    private final static String TABLENAME = "ENTREGA";

    public DeliveryDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Returns Delivery ID corresponding to given token
     *
     * @param token Token code string
     * @return Delivery ID
     */
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
    
    public Delivery getDeliveryByReservationIDFromDP(int reservationId, DropPoint dropPoint){
        Delivery delivery = null;
        String qry = "select * from entrega e, reserva r"
                + " where e." + reservationId + "= r.id_reserva"
                + " and r.id_dropPoint = "+dropPoint.getId();
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                delivery = new Delivery();
                delivery.setId(rs.getInt("id_reserva"));
                delivery.setDateOpen();
                delivery.setDateClose();
                delivery.setReservationID(reservationId);
                delivery.setCellID(rs.getInt("id_prateleira"));
                delivery.setCourierTokenID(rs.getInt("id_token_estafeta"));
                delivery.setAssistantTokenID(rs.getInt("id_token_colaborador"));
            } catch (SQLException e) {
            }
        }
        return delivery;
    }

    public Delivery getDeliveryByReservationID(int reservationId) {
        Delivery delivery = null;
        String qry = "select * from entrega"
                + " where " + reservationId + "= id_reserva";
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                delivery = new Delivery();
                delivery.setId(rs.getInt("id_reserva"));
                delivery.setDateOpen();
                delivery.setDateClose();
                delivery.setReservationID(reservationId);
                delivery.setCellID(rs.getInt("id_prateleira"));
                delivery.setCourierTokenID(rs.getInt("id_token_estafeta"));
                delivery.setAssistantTokenID(rs.getInt("id_token_colaborador"));
            } catch (SQLException e) {
            }
        }
        return delivery;
    }

    /**
     * Retrieves incremental ID for this object correponding Table
     *
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
                + " VALUES (" + obj.getDeliveryID() + "," + obj.getCellID() + "," + obj.getReservationID() + "," + obj.getCourierTokenID() + ","
                + "TO_DATE('" + obj.getOpenedDate() + "', 'dd-mm-yyyy HH24:MI'),"
                + "TO_DATE('" + obj.getClosedDate() + "', 'dd-mm-yyyy HH24:MI'))";
        ResultSet rs = executeQuery(query);
        return rs != null;
    }

    @Override
    public boolean update(Delivery delivery) {
        String query = "update entrega set data_abre_prateleira=TO_DATE('" + delivery.getOpenedDate().split(" ")[0] + "','dd-mm-yyyy'),"
                + " data_fecha_prateleira=TO_DATE('" + delivery.getClosedDate().split(" ")[0] + "','dd-mm-yyyy'),"
                + " id_reserva=" + delivery.getReservationID() + ","
                + " id_prateleira=" + delivery.getCellID() + ","
                + " id_token_estafeta=" + delivery.getCourierTokenID() + ","
                + " id_token_colaborador=" + delivery.getAssistantTokenID()
                + " where id_entrega = " + delivery.getDeliveryID();
        ResultSet rs = executeQuery(query);
        return rs != null;
    }

    @Override
    public void delete(Delivery obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Delivery get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns Client email corresponding to given Delivery
     *
     * @param delivery Delivery
     * @return Client email string
     */
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

    /**
     * Returns token code string used to PickUp Cell content
     *
     * @param delivery Delivery in process
     * @return token code string
     */
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

    /**
     * Returns the Delivery ID of an occupied Cell
     *
     * @param id Cell id
     * @return id
     */
    public int getDeliveryIdByCellId(int id) {
        String query = "SELECT ID_ENTREGA"
                + " FROM entrega e, prateleira p"
                + " WHERE p.ocupado = 1"
                + " AND p.ID_PRATELEIRA = " + id
                + " AND p.ID_PRATELEIRA = e.ID_PRATELEIRA";

        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                return rs.getInt("ID_ENTREGA");
            } catch (SQLException ex) {
            }
        }
        return -1;

    }

    /**
     * Returns the delivery list.
     *
     * @param idDropPoint
     * @return String
     */
    public String deliveriesList(DropPoint idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = executeQuery("select e.DATAENT, p.idprateleira, e.idtoken "
                + "  from entrega e, prateleira p, armario a, droppoint d"
                + " where e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint.getId());

        try {
            String str = "";
            while (executeQuery.next()) {

                str += "Delivery date: " + executeQuery.getString("dataent") + ". ID Cell: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";
                aux.add(str);
            }
            if (str.isEmpty()) {
                str = "Does not exist.";
            }

            return str;
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Give the list of deliveries collected.
     *
     * @param idDropPoint
     * @return String
     */
    public String collectedList(DropPoint idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = executeQuery("select r.DATAREC, p.idprateleira, r.idtoken "
                + "  from recolha r, entrega e, prateleira p, armario a, droppoint d"
                + "  where r.identrega = e.idtoken"
                + "   and e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint.getId());
        try {
            String str = "";
            while (executeQuery.next()) {

                str += "Delivery date: " + executeQuery.getString("DATAREC") + ". ID Cell: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";

            }

            if (str.isEmpty()) {
                str = "Does not exist.";
            }

            return str;

        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
