package domain;

/**
 *
 * @author 11408
 */
public class DropPoint {

    /**
     * The droppoint id
     */
    private int m_id;
    
    /**
     * the droppoint name
     */
    private String m_name;
    
    /**
     * The droppoint adress
     */
    private int m_idAddress;
    
    /**
     * The number of free days in 
     */
    private int free_days;

    /**
     * Constructor with parameters
     * @param m_id DropPoint ID
     * @param m_name DropPoint name
     * @param m_idAddress DropPoint's address
     * @param free_days DropPoint's grace period which will be added to the Reservation period
     */
    public DropPoint(int m_id, String m_name, int m_idAddress, int free_days) {
        this.m_id = m_id;
        this.m_name = m_name;
        this.m_idAddress = m_idAddress;
        this.free_days = free_days;
    }
    
    /**
     * Constructs an droppoint object without paramets
     */
    public DropPoint() {
    }
    
    
    /**
     * Rturns the droppoint id
     * @return the id
     */
    public int getId() {
        return this.m_id;
    }

    /**
     * Returns the droppoint name
     * @return the name
     */
    public String getName() {
        return this.m_name;
    }
    
    /**
     * Returns the droppoint address
     * @return the adress
     */
    public int getIdAddress() {
        return this.m_idAddress;
    }
    
    /**
     * Returns the dropoint Free Days
     * @return the free days
     */
    public int getFree_Days(){
        return this.free_days;
    }

    //SET`S
    /**
     * Sets a new droppoint id
     * @param m_id the new id
     */
    public void setId(int m_id) {
        this.m_id = m_id;
    }

    /**
     * Sets a new droppoint name
     * @param m_name the new name
     */
    public void setName(String m_name) {
        this.m_name = m_name;
    }

    /**
     * Sets a new droppoint address id
     * @param m_idAddress the new address id 
     */
    public void setIdAddress(int m_idAddress) {
        this.m_idAddress = m_idAddress;
    }
    
    /**
     * Sets a new droppoint free days period
     * @param free_days the new free days period 
     */
    public void setFree_Days(int free_days){
        this.free_days = free_days;
    }

    /**
     * 
     * @return DropPoint name
     */
    @Override
    public String toString() {

        return this.m_name;
    }
}
