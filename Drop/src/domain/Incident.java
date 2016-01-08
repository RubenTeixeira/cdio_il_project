package domain;

import java.util.Date;

/**
 *
 * @author MarcoSousa
 */
public class Incident {

    /**
     * The Incident ID
     */
    private int incident_id;

    /**
     * The Incident Type ID
     */
    private int incident_type_id;

    /**
     * The DropPoint ID
     */
    private int droppoint_id;

    /**
     * The Cell ID
     */
    private int cell_id;

    /**
     * The Date ID
     */
    private Date incident_date;

    /**
     * The maintenance Assistant ID
     */
    private int maintenance_assistant_id;

    /**
     * Constructs a new Incident object without any specified arguments
     *
     */
    public Incident() {
    }

    /**
     * Constructs a new Incident object with the specified arguments
     *
     * @param incident_id the incident id
     * @param incident_type_id the incident type id
     * @param droppoint_id the drop point id
     * @param cell_id the cell id
     * @param incident_date the incident_date
     * @param maintenance_assistant_id the maintenance assistant id
     */
    public Incident(int incident_id, int incident_type_id, int droppoint_id, int cell_id, Date incident_date, int maintenance_assistant_id) {
        this.incident_id = incident_id;
        this.incident_type_id = incident_type_id;
        this.droppoint_id = droppoint_id;
        this.cell_id = cell_id;
        this.incident_date = incident_date;
        this.maintenance_assistant_id = maintenance_assistant_id;
    }

    /**
     * Returns the incident id
     *
     * @return the incident_id
     */
    public int getIncident_id() {
        return this.incident_id;
    }

    /**
     * Returns the incident type id
     *
     * @return the incident_type_id
     */
    public int getIncident_type_id() {
        return this.incident_type_id;
    }

    /**
     * Returns the droppoint id
     *
     * @return the droppoint_id
     */
    public int getDroppoint_id() {
        return this.droppoint_id;
    }

    /**
     * Returns the cell id
     *
     * @return the cell_id
     */
    public int getCell_id() {
        return this.cell_id;
    }

    /**
     * Returns the incident incident_date
     *
     * @return the incdent incident_date
     */
    public Date getIncident_date() {
        return this.incident_date;
    }

    /**
     * Returns the maintenance assistant id
     *
     * @return the maintenance_assistant_id
     */
    public int getMaintenance_assistant_id() {
        return this.maintenance_assistant_id;
    }

    /**
     * Insert the new incident id
     *
     * @param incident_id the incident_id to set
     */
    public void setIncident_id(int incident_id) {
        this.incident_id = incident_id;
    }

    /**
     * Insert the new incident type id
     *
     * @param incident_type_id the incident_type_id to set
     */
    public void setIncident_type_id(int incident_type_id) {
        this.incident_type_id = incident_type_id;
    }

    /**
     * Insert the new droppoint id
     *
     * @param droppoint_id the droppoint_id to set
     */
    public void setDroppoint_id(int droppoint_id) {
        this.droppoint_id = droppoint_id;
    }

    /**
     * the new cell id
     *
     * @param cell_id the cell_id to set
     */
    public void setCell_id(int cell_id) {
        this.cell_id = cell_id;
    }

    /**
     * Insert the new incident date
     *
     * @param incident_date the incident_date to set
     */
    public void setIncident_date(Date incident_date) {
        this.incident_date = incident_date;
    }

    /**
     * Insert the new maintenance assistant id
     *
     * @param maintenance_assistant_id the maintenance_assistant_id to set
     */
    public void setMaintenance_assistant_id(int maintenance_assistant_id) {
        this.maintenance_assistant_id = maintenance_assistant_id;
    }
}
