/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.Incident;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcoSousa
 */
public class IncidentDAO extends GenericDAO<Incident> {

    private final static String TABLENAME = "INCIDENT";

    /**
     * Constructs a new IncidentDAO object with the specified SQL connection
     *
     * @param con the SQL connection
     */
    public IncidentDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Returns a new empty Incident object
     *
     * @return the new Incident
     */
    public Incident newIncident() {
        return new Incident();
    }

    /**
     * Returns a new Incident object
     *
     * @param incident_id the incident id
     * @param incident_type_id the incident type id
     * @param droppoint_id the drop point id
     * @param cell_id the cell id
     * @param date the date
     * @param maintenance_assistant_id the maintenance assistant id
     * @return the incident
     */
    public Incident newIncident(int incident_id, int incident_type_id, int droppoint_id, int cell_id, Date date, int maintenance_assistant_id) {
        return new Incident(incident_id, incident_type_id, droppoint_id, cell_id, date, maintenance_assistant_id);
    }
    
    /**
     * Returns true if insert the incident without errors into database
     * @param obj the insert object tho insert
     * @return true if insert ; false if not
     */
    @Override
    public boolean insertNew(Incident obj) {
        CallableStatement callableStatement = null;
        String insertStoreProc = "{call insertIncident(?,?,?,?)}";

        try {
            try {
                callableStatement = con.prepareCall(insertStoreProc);

                callableStatement.setInt(1,obj.getIncident_type_id());
                callableStatement.setInt(2,obj.getCell_id());
                callableStatement.setDate(3, new java.sql.Date(obj.getIncident_date().getTime()));
                callableStatement.setInt(4,1601091);

                callableStatement.executeUpdate();

                System.out.println("Record is inserted into Indident table!");
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

    @Override
    public boolean update(Incident obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Incident obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Incident get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
