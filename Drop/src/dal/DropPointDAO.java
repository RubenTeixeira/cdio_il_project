package dal;

import domain.DropPoint;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1130874
 */
public class DropPointDAO extends GenericDAO<DropPoint> {

    private final static String TABLENAME = "DROPPOINT";

    public DropPointDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Return a list of DropPoints with the DropPoint ID, Name and Adress ID
     *
     * @return list of DropPoints
     */
    public List<DropPoint> getListDropPoints() {
        ResultSet rs = executeQuery("select * from droppoint");
        List<DropPoint> lstDroPoint = new ArrayList<>();

        if (rs != null) {
            try {
                while (rs.next()) {
                    DropPoint drop = new DropPoint();
                    drop.setId(rs.getInt("ID_DROPPOINT"));
                    drop.setName(rs.getString("NOME_DROPPOINT"));
                    drop.setIdAddress(rs.getInt("ID_MORADA"));
                    drop.setFree_Days(rs.getInt("FREE_DAYS"));
                    lstDroPoint.add(drop);
                }
            } catch (Exception ex) {
                Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, "Error trying to create DropPoints", ex);
            }
        }
        return lstDroPoint;
    }

    @Override
    public boolean insertNew(DropPoint obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DropPoint obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DropPoint obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DropPoint get(int id) {
        DropPoint dropPoint = null;
        String query = "SELECT * FROM " + TABLENAME + " WHERE ID_DROPPOINT = " + id;
        ResultSet rs = executeQuery(query);
        if (rs != null) {
            try {
                rs.next();
                dropPoint = new DropPoint(rs.getInt("ID_DROPPOINT"), rs.getString("NOME_DROPPOINT"), rs.getInt("ID_MORADA"), rs.getInt("FREE_DAYS"));
            } catch (SQLException ex) {
                Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dropPoint;
    }

    public boolean setFreeDays(int idDropPoint, int freeDays) {
        String query = "UPDATE DROPPOINT SET FREE_DAYS = "
                + freeDays
                + " where id_DropPoint = " + idDropPoint;

        ResultSet rs = executeQuery(query);
        return rs != null;
    }

    /**
     * Returns the occupancy rate of a particular drop point.
     *
     * @param idDropPoint
     * @return String
     */
    public String consultOccupation(DropPoint DropPoint) {
        ResultSet executeQuery
                = executeQuery("SELECT COUNT(id_Prateleira) "
                        + "FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (id_armario)) "
                        + "USING (id_Droppoint)) WHERE id_droppoint = " + DropPoint.getId());
        String total = "";
        try {
            while (executeQuery.next()) {

                total += executeQuery.getString("COUNT(id_Prateleira)");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet executeQuery2 = executeQuery("SELECT COUNT(id_Prateleira) FROM (SELECT id_droppoint, id_Prateleira FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (id_armario)) USING (id_Droppoint)) WHERE id_droppoint =" + DropPoint.getId() + ")"
                + "INNER JOIN "
                + "(SELECT id_prateleira FROM Prateleira NATURAL JOIN Entrega WHERE id_prateleira NOT IN ("
                + "SELECT id_prateleira FROM "
                + "Entrega JOIN Recolha ON Entrega.id_Entrega = Recolha.id_Entrega)) USING (id_prateleira)");
        try {
            while (executeQuery2.next()) {
                String ocupada = "";

                ocupada += executeQuery2.getString("COUNT(id_Prateleira)");
                return "The Drop Point with id "
                        + DropPoint.getId()
                        + " has " + ocupada
                        + " occupied shelves, a total of "
                        + total + ".";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public float getPriority(DropPoint DropPoint) {
        float capacity = 0;
        float busy = 0;
        float max = 0;

        ResultSet executeQuery
                = executeQuery("SELECT COUNT(id_Prateleira) "
                        + "FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (id_armario)) "
                        + "USING (id_Droppoint)) WHERE id_droppoint = " + DropPoint.getId());
        String total = "";
        try {
            while (executeQuery.next()) {

                total += executeQuery.getString("COUNT(id_Prateleira)");
                capacity = Float.valueOf(total);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet executeQuery2 = executeQuery("SELECT COUNT(id_Prateleira) FROM (SELECT id_droppoint, id_Prateleira FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (id_armario)) USING (id_Droppoint)) WHERE id_droppoint =" + DropPoint.getId() + ")"
                + "INNER JOIN "
                + "(SELECT id_prateleira FROM Prateleira NATURAL JOIN Entrega WHERE id_prateleira NOT IN ("
                + "SELECT id_prateleira FROM "
                + "Entrega JOIN Recolha ON Entrega.id_Entrega = Recolha.id_Entrega)) USING (id_prateleira)");
        try {
            while (executeQuery2.next()) {
                String ocupada = "";

                ocupada += executeQuery2.getString("COUNT(id_Prateleira)");
                busy = Float.valueOf(ocupada);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet executeQuery3 = executeQuery("select max_rate from droppoint where id_droppoint= " +DropPoint.getId());
        try {
            while (executeQuery3.next()) {
                String max_rate = "";

                max_rate += executeQuery3.getString("MAX_RATE");
                max = Float.valueOf(max_rate);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DropPointDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        float rate = busy / capacity;

        return (max-rate);
    }

    public List<DropPoint> getDropPointsWithIncidents() {
        List<DropPoint> lstDropPoints = null;
        ResultSet rs = executeQuery("SELECT d.* FROM INCIDENT i, PRATELEIRA p, ARMARIO a, DROPPOINT d\n" + "    WHERE i.ID_PRATELEIRA = p.ID_PRATELEIRA\n" + "    AND p.ID_ARMARIO = a.ID_ARMARIO\n" + "    AND a.ID_DROPPOINT = d.ID_DROPPOINT");
        if (rs != null) {
            lstDropPoints = new ArrayList<>();
            try {
                while (rs.next()) {
                    DropPoint dp = new DropPoint();
                    dp.setId(rs.getInt("ID_DROPPOINT"));
                    dp.setName(rs.getString("NOME_DROPPOINT"));
                    lstDropPoints.add(dp);
                }
            } catch (SQLException ex) {
            }
        }
        return lstDropPoints;
    }

}
