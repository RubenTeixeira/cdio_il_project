package domain;

/**
 *
 * @author 11408
 */
public class DropPoint {

    private int m_id;
    private String m_nome;
    private int m_idMorada;

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

    @Override
    public String toString() {

        return this.m_nome;
    }
}
