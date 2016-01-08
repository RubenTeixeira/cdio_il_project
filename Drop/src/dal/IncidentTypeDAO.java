/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import domain.IncidentType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarcoSousa
 */
public class IncidentTypeDAO extends GenericDAO<IncidentType> {

    private final static String TABLENAME = "INCIDENT_TYPE";

    /**
     * Constructs a new IncidentTypeDAO object with the specified SQL connection
     *
     * @param con the SQL connection
     */
    public IncidentTypeDAO(Connection con) {
        super(con, TABLENAME);
    }

    @Override
    public boolean insertNew(IncidentType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(IncidentType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(IncidentType obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IncidentType get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns a list of all IncidentType objects from the database
     *
     * @return the list of incidenttypes
     */
    public List<IncidentType> getListOfIncidentTypes() {
        ResultSet rs = executeQuery("select * from Incident_Type");
        List<IncidentType> lstIncidentTypes = new ArrayList<>();

        if(rs != null) {
            try {
                while (rs.next()) {
                    IncidentType iType = new IncidentType();
                    iType.setIncidentType_id(rs.getInt("INCIDENT_TYPE_ID"));
                    iType.setDescription(rs.getString("DESCRIPTION"));
                    lstIncidentTypes.add(iType);
                }
            } catch (Exception ex) {
                Logger.getLogger(IncidentTypeDAO.class.getName()).log(Level.SEVERE, "Error trying to create Incident Types", ex);
            }
        }
        return lstIncidentTypes;
    }

}
