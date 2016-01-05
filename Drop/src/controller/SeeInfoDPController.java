package controller;

import dal.AddressDAO;
import dal.CellDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

public class SeeInfoDPController {

    /**
     * The Connection to the Database
     */
    private SQLConnection manager;

    /**
     * The DropPointDAO
     */
    private DropPointDAO dropPointDAO;

    /**
     * The AddressDAO
     */
    private AddressDAO addressDAO;
    
    /**
     * The CellDAO
     */
    private CellDAO cellDAO;
    
    /**
     * The DropPoint
     */
    private DropPoint dropPoint;

    /**
     * Constructs a new SeeInfoDPController object with the specified file
     * containing the credentials to create the SQL connection
     *
     * @param file settings file containing the credentials to create the SQL
     * connection
     */
    public SeeInfoDPController(String file) {
        manager = persistence.OracleDb.getInstance(file);
        try {
            dropPointDAO = (DropPointDAO) manager.getDAO(Table.DROPPOINT);
            addressDAO = (AddressDAO) manager.getDAO(Table.ADDRESS);
            cellDAO = (CellDAO) manager.getDAO(Table.CELL);
        } catch (SQLException ex) {
            Logger.getLogger(OpenCellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a List of all existing DropPoints
     *
     * @return the list of Droppoints
     */
    public List<DropPoint> listDropPoints() {
        return dropPointDAO.getListDropPoints();
    }

    /**
     * Select the droppoint chosen by costumer
     *
     * @param dropChosen the droppoint chosen
     */
    public void selectDropPoint(DropPoint dropChosen) {
        this.dropPoint = dropChosen;
    }

    /**
     * Returns a String object with the name of the droppoint
     *
     * @return name of the droppoint
     */
    public String getDropPointName() {
        return this.dropPoint.getNome();
    }

    /**
     * Returns a String object with all information about the droppoint, the
     * name, the address, and it's cells
     *
     * @return the droppoint info
     */
    public String getDropPointInfo() throws SQLException{
        return String.format("%s\n%s\n%s",
                this.dropPoint.getNome(),
                this.addressDAO.getAdressByID(dropPoint.getIdMorada()),
                this.cellDAO.getCellsByDropPointID(dropPoint.getId()));
    }

    /**
     * Returns a String object with the droppoint coordinates
     * @return the droppoint coordinates
     * @throws SQLException 
     */
    public String getDropPointCoor() throws SQLException{
        return this.addressDAO.getCoordinatesByAddressID(dropPoint.getIdMorada());
    }

}
