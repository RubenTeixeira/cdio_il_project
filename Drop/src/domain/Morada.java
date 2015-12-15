package domain;

import java.util.Objects;

public class Morada implements Comparable<Morada> {

    private int id;
    private String rua;
    private String codigoPostal;
    private String localidade;

    /**
     * Main constructor.
     *
     * @param rua
     * @param codigoPostal
     * @param localidade
     */
    public Morada(String rua, String codigoPostal, String localidade) {
        this.setRua(rua);
        this.setCodigoPostal(codigoPostal);
        this.setLocalidade(localidade);
    }

    /**
     * Give it back ID value attribute
     *
     * @return int ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID value attribute
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Give it back street value attribute
     *
     * @return String Street
     */
    public String getRua() {
        return rua;
    }

    /**
     * Give it back postal code value attribute
     *
     * @return String Postal code
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Give it back city value attribute
     *
     * @return String City
     */
    public String getLocalidade() {
        return localidade;
    }

    /**
     * Set street value attribute
     *
     * @param street
     */
    public void setRua(String street) {
        this.rua = street;
    }

    /**
     * Set postal code attribute
     *
     * @param postalCode
     */
    public void setCodigoPostal(String postalCode) {
        this.codigoPostal = postalCode;
    }

    /**
     * Set city attribute
     *
     * @param city
     */
    public void setLocalidade(String city) {
        this.localidade = city;
    }

    /**
     * Test if the object is a valid one.
     *
     * @return boolean
     */
    public boolean valida() {
        if (getRua() == null || getRua().isEmpty()) {
            return false;
        }
        if (getCodigoPostal() == null || getCodigoPostal().isEmpty()) {
            return false;
        }
        if (getLocalidade() == null || getLocalidade().isEmpty()) {
            return false;

        }
        return true;
    }

    /**
     * Consulting the state of object
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("Rua: %s\n"
                + "Código Postal: %s\n"
                + "Localidade: %s\n", getRua(), getCodigoPostal(), getLocalidade());

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.rua);
        hash = 61 * hash + Objects.hashCode(this.codigoPostal);
        hash = 61 * hash + Objects.hashCode(this.localidade);
        return hash;
    }

    /**
     * Test if who objects are equal.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Morada other = (Morada) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        return Objects.equals(this.localidade, other.localidade);
    }

    /**
     * Compare two instances of morada
     *
     * @param other
     * @return int
     */
    @Override
    public int compareTo(Morada other) {
        return this.getRua().compareTo(other.getRua());
    }

}
