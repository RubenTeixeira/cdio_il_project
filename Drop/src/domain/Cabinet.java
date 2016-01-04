package domain;

import java.util.Objects;

/**
 *
 * @author 1130874
 */
public class Cabinet {

    /**
     * ID Armario
     */
    private int id;
    /**
     * NAME Cabinet
     */
    private String name;
    /**
     * MAINTENANCE Cabinet
     */
    private int maintenance;

    public Cabinet()
    {
    }
    //GETS´s

    /**
     * Get Id Armario
     *
     * @return ID
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Get name Cabinet
     *
     * @return Name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Get state of maintenance cabinet
     *
     * @return 0 - Not in Maintenance 1 - In Maintenance
     */
    public int getMaintenance()
    {
        return maintenance;
    }

    //SET´s
    /**
     * Modify Id Cabinet
     *
     * @param id ID
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Modify name Cabinet
     *
     * @param name Name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Modify maintenance Cabinet
     *
     * @param state 0 - Not maintenance 1 - In maintenance
     */
    public void setMaintenance(int state)
    {
        this.maintenance = state;
    }

    /**
     * Info name do Armario
     *
     * @return Info
     */
    @Override
    public String toString()
    {
        return String.format("Nome: %s", this.name);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        
        final Cabinet other = (Cabinet) obj;
        return this.id == other.id && this.name.equals(other.name);
    }

    
}

