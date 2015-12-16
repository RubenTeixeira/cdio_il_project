package persistence;

import domain.DropPoint;
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
public class DropPointDAO extends GenericDAO<DropPoint>{

    private final static String TABLENAME = "DROPPOINT";

    public DropPointDAO(Connection con)
    {
        super(con, TABLENAME);
    }

    /**
     * Return a list of DropPoints with the DropPoint ID, Name and Adress ID
     *
     * @return list of DropPoints
     */
    public List<DropPoint> getListDropPoints()
    {
        ResultSet rs = executeQuery("select * from droppoint");
        List<DropPoint> lstDroPoint = new ArrayList<>();

        if (rs != null) {
            try
            {
                while (rs.next())
                {
                    DropPoint drop = new DropPoint();
                    drop.setId(rs.getInt("ID_DROPPOINT"));
                    drop.setNome(rs.getString("NOME_DROPPOINT"));
                    drop.setIdMorada(rs.getInt("ID_MORADA"));
                    lstDroPoint.add(drop);
                }
            } catch (Exception ex)
            {
                Logger.getLogger(SeeInfoDAL.class.getName()).log(Level.SEVERE, "Error trying to create DropPoints", ex);
            }
        }
        return lstDroPoint;
    }

    @Override
    public boolean insertNew(DropPoint obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DropPoint obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(DropPoint obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DropPoint get(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
