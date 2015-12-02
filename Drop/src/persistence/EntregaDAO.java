/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Entrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class EntregaDAO extends GenericDAO<Entrega> {

    private final static String TABLENAME = "ENTREGA";

    public EntregaDAO(Connection con) {
        super(con, TABLENAME);
    }

    public int getIdByToken(String token) {
        String qry = "select e.id_entrega from token t, reserva r, entrega e"
                + "     where t.id_reserva = r.id_reserva"
                + "         and r.id_reserva = e.id_reserva"
                + "         and t.codigo = '" + token + "'";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_entrega");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    public int getNextId() {
        String query = "select nvl(max(id_entrega),0)+1 as id from entrega";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean insertNew(Entrega obj) {
        String query = "INSERT INTO ENTREGA ("
                + "ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) "
                + " VALUES (" + obj.getIdEntrega() + "," + obj.getIdPrat() + "," + obj.getIdReservation() + "," + obj.getIdToken() + ","
                + "TO_DATE('" + obj.getDateOpen() + "', 'dd-mm-yyyy HH24:MI'),"
                + "TO_DATE('" + obj.getDateClose() + "', 'dd-mm-yyyy HH24:MI'))";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
    }

    @Override
    public boolean update(Entrega obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Entrega obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entrega get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getEmailCliente(Entrega entrega) {
        int idReservation = entrega.getIdReservation();

        String qry = "select c.email from cliente c, reserva r"
                + "     where r.id_reserva = " + idReservation
                + "AND r.id_cliente = c.id_cliente";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
        }
        return "";
    }

    public String getTokenRecolha(Entrega entrega) {
        int idReservation = entrega.getIdReservation();

        String qry = "select t.codigo from token t"
                + "     where t.id_reserva = " + idReservation
                + "AND t.id_tipo_token = 2"
                + "AND t.ativo = 1";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException ex) {
        }
        return "";
    }

}
