package domain;

import java.util.Objects;

public class Address implements Comparable<Address> {

    private int id;
    private String street;
    private String postalCode;
    private String locality;
    private double latitude;
    private double longitude;

    /**
     * Main constructor.
     *
     * @param rua
     * @param codigoPostal
     * @param localidade
     */
    public Address(String rua, String codigoPostal, String localidade) {
        this.setStreet(rua);
        this.setPostalCode(codigoPostal);
        this.setLocality(localidade);
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
    public String getStreet() {
        return street;
    }

    /**
     * Give it back postal code value attribute
     *
     * @return String Postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Give it back city value attribute
     *
     * @return String City
     */
    public String getLocality() {
        return locality;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    /**
     * Set street value attribute
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Set postal code attribute
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Set city attribute
     *
     * @param city
     */
    public void setLocality(String city) {
        this.locality = city;
    }

    /**
     * Test if the object is a valid one.
     *
     * @return boolean
     */
    public boolean validate() {
        if (getStreet() == null || getStreet().isEmpty()) {
            return false;
        }
        if (getPostalCode() == null || getPostalCode().isEmpty()) {
            return false;
        }
        if (getLocality() == null || getLocality().isEmpty()) {
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
                + "Localidade: %s\n", getStreet(), getPostalCode(), getLocality());

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.street);
        hash = 61 * hash + Objects.hashCode(this.postalCode);
        hash = 61 * hash + Objects.hashCode(this.locality);
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
        final Address other = (Address) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        return Objects.equals(this.locality, other.locality);
    }

    /**
     * Compare two instances of morada
     *
     * @param other
     * @return int
     */
    @Override
    public int compareTo(Address other) {
        return this.getStreet().compareTo(other.getStreet());
    }

}
