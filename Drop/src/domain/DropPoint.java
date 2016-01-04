package domain;

/**
 *
 * @author 11408
 */
public class DropPoint {

    private int m_id;
    private String m_nome;
    private int m_idMorada;
    private int free_days;

    /**
     * Constructor with parameters
     * @param m_id DropPoint ID
     * @param m_nome DropPoint name
     * @param m_idMorada DropPoint's address
     * @param free_days DropPoint's grace period which will be added to the Reservation period
     */
    public DropPoint(int m_id, String m_nome, int m_idMorada, int free_days) {
        this.m_id = m_id;
        this.m_nome = m_nome;
        this.m_idMorada = m_idMorada;
        this.free_days = free_days;
    }
    
    public DropPoint() {
    }
    
    //GET`S
    public int getId() {
        return this.m_id;
    }

    public String getNome() {
        return this.m_nome;
    }
    
    public int getIdMorada() {
        return this.m_idMorada;
    }
    
    public int getFree_Days(){
        return this.free_days;
    }

    //SET`S
    public void setId(int m_id) {
        this.m_id = m_id;
    }

    public void setNome(String m_nome) {
        this.m_nome = m_nome;
    }

    public void setIdMorada(int m_idMorada) {
        this.m_idMorada = m_idMorada;
    }
    
    public void setFree_Days(int free_days){
        this.free_days = free_days;
    }

    /**
     * 
     * @return DropPoint name
     */
    @Override
    public String toString() {

        return this.m_nome;
    }
}
