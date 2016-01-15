package dal;

import domain.DropPoint;
import domain.Maintenance;
import domain.MaintenancePlan;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class MaintenanceDAO extends GenericDAO<Maintenance> {

    private final static String TABLENAME = "MANUTENCAO";

    public MaintenanceDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Get unexecuted Maintenance corresponding to given DropPoint
     *
     * @param dp DropPoint
     * @return Maintenance
     */
    public Maintenance getDropPointMaintenance(DropPoint dp) {
        Maintenance maintenance = null;
        ResultSet rs = executeQuery("SELECT * FROM MANUTENCAO"
                + "  WHERE ID_DROPPOINT = " + dp.getId()
                + "  AND DATA_FIM = NULL");
        if (rs != null) {
            try {
                rs.next();
                maintenance = new Maintenance(
                        rs.getInt("ID_MANUTENCAO"), rs.getInt("VISIT_INDEX"), dp, rs.getDate("DATA_INICIO"), rs.getDate("DATA_FIM"), rs.getInt("ID_MAINT_PLAN"));
            } catch (SQLException ex) {
            }
        }
        if (maintenance != null) {
            rs = executeQuery("SELECT * FROM CELL_MAINTENANCE"
                    + "     WHERE ID_MAINTENANCE = " + maintenance.getId());
            if (rs != null) {
                try {
                    while (rs.next()) {
                        maintenance.addCellMaintenance(rs.getInt("ID_CELL"));
                    }
                } catch (SQLException ex) {
                }
            }
            return maintenance;
        }
        return null;
    }

    /**
     * Returns the total duration of the tasks defined for given DropPoint
     *
     * @param dp DropPoint
     * @return total duration
     */
    public Float getDropPointMaintenanceDuration(DropPoint dp) {

        ResultSet rs = executeQuery("SELECT SUM(AVG_DURATION) AS DURATION FROM PREEMPTIVE_DP_PLAN p, MAINTENANCE_TASK t\n"
                + "  WHERE p.ID_DROPPOINT = " + dp.getId() + "\n"
                + "  AND p.ID_TASK = t.ID_TASK");
        if (rs != null) {
            try {
                if (rs.next()) {
                    return rs.getFloat("DURATION");
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    /**
     * Retrieves incremental ID for this object correponding Table
     *
     * @return int ID
     */
    public int getNextId() {
        String query = "select nvl(max(id_manutencao),0)+1 as id from " + TABLENAME;
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

    /**
     * Retrieves incremental ID for this object correponding Table
     *
     * @return int ID
     */
    public int getNextPlanId() {
        String query = "select nvl(max(id_Maint_Plan),0)+1 as id from Maintenance_Plan";
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
    public boolean insertNew(Maintenance maint) {
        ResultSet rs = executeQuery("INSERT INTO MANUTENCAO (ID_MANUTENCAO,VISIT_INDEX,ID_DROPPOINT,ID_MAINT_PLAN)"
                + " VALUES(" + maint.getId() + "," + maint.getIndex() + ","
                + maint.getDropPoint().getId() + "," + maint.getPlanID() + ")");
        if (rs != null) {
            try {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
            }
        }
        return false;
    }

    @Override
    public boolean update(Maintenance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Maintenance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Maintenance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean insertPlan(MaintenancePlan plan) {
        String qry = "INSERT INTO MAINTENANCE_PLAN (ID_MAINT_PLAN,MAINT_PLAN_DATE,ID_MAINT_TEAM)\n"
                + "    VALUES (" + plan.getId() + "," + plan.getPlanDate() + "," + plan.getTeamID() + ")";
        ResultSet rs = executeQuery(qry);

        if (rs != null) {
            try {
                if (rs.next()) {
                    return true;
                }
            } catch (SQLException ex) {
            }
        }
        return false;
    }

    public List<Maintenance> getListMaintenanceCurrentDay() throws SQLException {
        List<Maintenance> list = new ArrayList<>();
        ResultSet rs = executeQuery("select m.ID_MANUTENCAO,m.ID_DROPPOINT from MAINTENANCE_PLAN p, MANUTENCAO m\n"
                + "where p.ID_MAINT_PLAN = m.ID_MAINT_PLAN\n"
                + "and p.MAINT_PLAN_DATE >= TO_DATE(TO_CHAR(CURRENT_DATE, 'dd-mm-yyyy'),'dd-mm-yyyy')\n"
                + "and p.MAINT_PLAN_DATE < TO_DATE(TO_CHAR(CURRENT_DATE, 'dd-mm-yyyy'),'dd-mm-yyyy')+1\n"
                + "and m.DATA_INICIO is null");
        if (rs != null) {
            while (rs.next()) {
                Maintenance m = new Maintenance();
                m.setId(rs.getInt("ID_MANUTENCAO"));
                m.setDropPointID(rs.getInt("ID_DROPPOINT"));
                list.add(m);
            }
        }
        return list;
    }

    public List<String> getTasksOfDropPoint(int id) throws SQLException {
        List<String> list = new ArrayList<>();
        ResultSet rs = executeQuery("select m.DESCRIPTION from MAINTENANCE_TASK m,PREEMPTIVE_DP_PLAN p\n"
                + "where m.ID_TASK = p.ID_TASK\n"
                + "and p.ID_DROPPOINT = " + id);
        if (rs != null) {
            while (rs.next()) {
                list.add(rs.getString("DESCRIPTION"));
            }
        }
        return list;
    }

    public List<String> getCompletedMaintenancebyDropPoint(DropPoint droppoint) throws SQLException {
        List<String> lMaintenance = new ArrayList<String>();
        ResultSet rs = executeQuery("SELECT * FROM MANUTENCAO m, employee e"
                + "  WHERE ID_DROPPOINT = " + droppoint.getId()
                + " AND m.id_maint_ass = e.e_number"
        //        + "  AND m.DATA_FIM = NOT NULL"
        );
        if (rs != null) {
            while (rs.next()) {
                String maintenance = "ID Maintenance: " + rs.getInt("id_manutencao") + "\n"
                        + "Start Date: " + rs.getDate("data_inicio") + "\n"
                        + "Finish Date: " + rs.getDate("data_fim") + "\n"
                        + "Employee: " + rs.getString("e_name") + "\n";
                
                lMaintenance.add(maintenance);
            }
        }
        return lMaintenance;

    }

    public boolean updateDates(Maintenance maintenance)
    {
        CallableStatement callableStatement = null;
        String insertStoreProc = "{call updateMaintenanceDate(?,?,?)}";

        try {
            try {
                callableStatement = con.prepareCall(insertStoreProc);

                callableStatement.setDate(1,new java.sql.Date(maintenance.getStartDate().getTime()));
                callableStatement.setDate(2,new java.sql.Date(maintenance.getFinishDate().getTime()));
                callableStatement.setInt(3,maintenance.getId());

                callableStatement.executeUpdate();
                return true;

            } catch (SQLException e) {

                System.out.println(e.getMessage());

            } finally {

                if(callableStatement != null) {
                    callableStatement.close();
                }
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
        return false;
    }

}
