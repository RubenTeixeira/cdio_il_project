package dal;

import domain.Cabinet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1130874
 */
public class CabinetDAO extends GenericDAO<Cabinet> {

    /**
     * Table NAME
     */
    private final static String TABLENAME = "ARMARIO";

    /**
     * New empty CabinetDAO
     *
     * @param con The connection
     */
    public CabinetDAO(Connection con)
    {
        super(con, TABLENAME);
    }

    /**
     * Return a list of Cabinets with the Cabinet ID, Name and Maintenance
     *
     * @param dropID The relative DropPoint
     * @return list of Cabinets
     */
    public List<Cabinet> getListOfCabinets(int dropID)
    {
        ResultSet rs = executeQuery("select * from Armario where id_droppoint = " + dropID);
        List<Cabinet> lstCabinets = new ArrayList<>();

        if (rs != null)
        {
            try
            {
                while (rs.next())
                {
                    Cabinet cabinet = new Cabinet();
                    cabinet.setId(rs.getInt("ID_ARMARIO"));
                    cabinet.setName(rs.getString("NOME"));
                    cabinet.setMaintenance(rs.getInt("MANUTENCAO"));
                    lstCabinets.add(cabinet);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(CabinetDAO.class.getName()).log(Level.SEVERE, "Error trying to create Cabinet`s", ex);
            }
        }
        return lstCabinets;
    }

    /**
     * Return a list of Cabinets with the Cabinet ID, Name and Maintenance state
     * Not in Maintenance
     *
     * @param dropID The relative DropPoint
     * @return list of Cabinets
     */
    public List<Cabinet> getListOfCabinetsNotInMaintenance(int dropID)
    {
        ResultSet rs = executeQuery("select * from Armario where id_droppoint = " + dropID + "and manutencao = 0");
        List<Cabinet> lstCabinets = new ArrayList<>();

        if (rs != null)
        {
            try
            {
                while (rs.next())
                {
                    Cabinet cabinet = new Cabinet();
                    cabinet.setId(rs.getInt("ID_ARMARIO"));
                    cabinet.setName(rs.getString("NOME"));
                    cabinet.setMaintenance(rs.getInt("MANUTENCAO"));
                    lstCabinets.add(cabinet);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(CabinetDAO.class.getName()).log(Level.SEVERE, "Error trying to create Cabinet`s", ex);
            }
        }
        return lstCabinets;
    }
    /**
     * Return a list of Cabinets with the Cabinet ID, Name and Maintenance state
     * in Maintenance
     *
     * @param dropID The relative DropPoint
     * @return list of Cabinets
     */
    public List<Cabinet> getListOfCabinetsInMaintenance(int dropID)
    {
        ResultSet rs = executeQuery("select * from Armario where id_droppoint = " + dropID + "and manutencao = 1");
        List<Cabinet> lstCabinets = new ArrayList<>();

        if (rs != null)
        {
            try
            {
                while (rs.next())
                {
                    Cabinet cabinet = new Cabinet();
                    cabinet.setId(rs.getInt("ID_ARMARIO"));
                    cabinet.setName(rs.getString("NOME"));
                    cabinet.setMaintenance(rs.getInt("MANUTENCAO"));
                    lstCabinets.add(cabinet);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(CabinetDAO.class.getName()).log(Level.SEVERE, "Error trying to create Cabinet`s", ex);
            }
        }
        return lstCabinets;
    }
    
    /**
     * Put Cabinet in Maintenance
     *
     * @param cabinet the cabinet to put in maintenance
     * @return True if sucess or False Not sucess
     */
    public boolean putInMaintenance(Cabinet cabinet)
    {
        return update(cabinet);
    }

    /**
     * Stop the Maintenance
     *
     * @param cabinet the cabinet to stop maintenence
     * @return True if sucess or False if not sucess
     */
    public boolean stopMaintenance(Cabinet cabinet)
    {
        return update(cabinet);
    }

    @Override
    public boolean insertNew(Cabinet obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cabinet cabinet)
    {
        String query = "UPDATE ARMARIO SET MANUTENCAO = "
                + cabinet.getMaintenance()
                + " where id_armario = " + cabinet.getId();
        ResultSet rs = executeQuery(query);
        return rs != null;
    }

    @Override
    public void delete(Cabinet obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cabinet get(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}