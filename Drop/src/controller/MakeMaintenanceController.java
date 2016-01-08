/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cabinet;
import domain.Cell;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.Deque;
import java.util.List;
import dal.CabinetDAO;
import dal.CellDAO;
import dal.DropPointDAO;
import dal.IncidentDAO;
import dal.IncidentTypeDAO;
import persistence.OracleDb;
import persistence.SQLConnection;
import dal.Table;
import domain.Incident;
import domain.IncidentType;
import java.util.Date;

/**
 *
 * @author nuno
 */
public class MakeMaintenanceController {

    /**
     * The CabinetDAO
     */
    private final CabinetDAO cabinetDAO;

    /**
     * The CellDAO
     */
    private final CellDAO cellDAO;

    /**
     * The DroppointDAO
     */
    private final DropPointDAO dropDAO;

    /**
     * The IncidentDAO
     */
    private final IncidentDAO incidentDAO;

    /**
     * The IncidentTypeDAO
     */
    private final IncidentTypeDAO incidentTypeDAO;

    /**
     * The Cabinet in maintenance
     */
    private Cabinet cabinet;

    /**
     * The cell open
     */
    private Cell cell;

    /**
     * The Droppoint ID
     */
    private int droppointID;

    /**
     * The incident of the cell
     */
    private Incident incident;

    /**
     * The incident type
     */
    private IncidentType incidentType;

    /**
     * Constructs a new MakeMaintenanceController object with the specified file
     * containing the credentials to create the SQL connection
     *
     * @param file settings file containing the credentials to create the SQL
     * connection
     * @exception SQLException
     */
    public MakeMaintenanceController(String file) throws SQLException {
        SQLConnection instance = OracleDb.getInstance(file);
        this.cabinetDAO = (CabinetDAO) instance.getDAO(Table.CABINET);
        this.cellDAO = (CellDAO) instance.getDAO(Table.CELL);
        this.dropDAO = (DropPointDAO) instance.getDAO(Table.DROPPOINT);
        this.incidentDAO = (IncidentDAO) instance.getDAO(Table.INCIDENT);
        this.incidentTypeDAO = (IncidentTypeDAO) instance.getDAO(Table.INCIDENT_TYPE);
    }

    /**
     * Returns a list of all existing DropPoints
     *
     * @return the list of Droppoints
     */
    public List<DropPoint> listDropPoints() {
        return this.dropDAO.getListDropPoints();
    }

    /**
     * Returns the list of all cabinets in maintenace from a specified droppoint
     *
     * @param dropID the droppoint id
     * @return the cabinet list
     */
    public List<Cabinet> listCabinetsInMaintenance(int dropID) {
        this.droppointID = dropID;
        List<Cabinet> listOfCabinets = this.cabinetDAO.getListOfCabinets(dropID);
        listOfCabinets.removeIf(p -> p.getMaintenance() == 0);
        return listOfCabinets;
    }

    /**
     * Select the cabinet to put into maintenance
     *
     * @param cabinet the caninet
     */
    public void selectCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    /**
     * Returns a list of cells to open for maintenance
     *
     * @return the list of cells
     * @throws SQLException
     */
    public Deque<Cell> cellsToOpen() throws SQLException {
        return this.cellDAO.cellsToOpen(this.cabinet);
    }

    /**
     * Returns true if the cell chosen is open, false if not
     *
     * @param cell the cell to open
     * @return true - open ; false - close
     * @throws SQLException
     */
    public boolean openCell(Cell cell) throws SQLException {
        this.cell = cell;
        return this.cellDAO.updateCell(this.cell, CellDAO.OPEN);
    }

    public boolean insertReport(String report) {
        return this.cellDAO.insertReport(this.cell);
    }

    /**
     * Returns true if the cell is closed and marked as active or not depending
     * on the specified option, or false if is not closed
     *
     * @param op option
     * @return true if closed or false not closed
     * @throws SQLException
     */
    public boolean closeCell(int op) throws SQLException {
        if(op == 0) {
            return this.cellDAO.updateCell(cell, CellDAO.OPEN);
        }
        return this.cellDAO.updateCell(cell, CellDAO.CLOSE);
    }

    /**
     * Mark cell as non operational and creates an Incident that describe what
     * is the problem with the cell
     *
     * @param no the operational state
     */
    public void createIncident(int no) {
        this.cell.setIsOperational(no);
        this.incident = this.incidentDAO.newIncident();
        this.incident.setCell_id(this.cell.getId());
    }

    /**
     * Returns a list of all existing incident types
     *
     * @return the list of incident types
     */
    public List<IncidentType> getIncidentsType() {
        return this.incidentTypeDAO.getListOfIncidentTypes();
    }

    /**
     * Returns if the incident is correctly inserted on database
     *
     * @param incident_type_id the incident type id
     */
    public boolean selectIncidentType(int incident_type_id) {
        this.incident.setIncident_type_id(incident_type_id);
        this.incident.setIncident_date(new Date());
        return this.incidentDAO.insertNew(incident);
    }
}
