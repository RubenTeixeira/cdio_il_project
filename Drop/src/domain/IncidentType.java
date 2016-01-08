package domain;

/**
 *
 * @author MarcoSousa
 */
public class IncidentType {

    private int incidentType_id;
    private String description;

    /**
     * Constructs a new IncidentType object without any specified arguments
     *
     */
    public IncidentType() {
    }

    /**
     * Returns the incident type id
     *
     * @return the incidentType_id
     */
    public int getIncidentType_id() {
        return this.incidentType_id;
    }

    /**
     * Returns the incident description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Changes the incident type id
     *
     * @param incidentType_id the incidentType_id to set
     */
    public void setIncidentType_id(int incidentType_id) {
        this.incidentType_id = incidentType_id;
    }

    /**
     * Changes the incident description
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
