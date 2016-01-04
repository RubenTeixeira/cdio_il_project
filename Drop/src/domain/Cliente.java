package domain;

public class Cliente implements Comparable<Cliente>, Cloneable {

    private int id;
    private String name;
    private int NIF;
    private String username;
    private String password;
    private Address address;
    private String email;
    private int mobilePhone;

    /**
     * Void construtor.
     */
    public Cliente() {
    }

    /**
     * Main constructor.
     *
     * @param id
     * @param name
     * @param NIF
     * @param username
     * @param password
     * @param address
     * @param email
     * @param cellPhoneNumber
     */
    public Cliente(int id, String name, int NIF, String username, String password, Address address, String email, int cellPhoneNumber) {
        this.setId(id);
        this.setName(name);
        this.setNIF(NIF);
        this.setUsername(username);
        this.setPassword(password);
        this.setAddress(address);
        this.setEmail(email);
        this.setMobilePhone(cellPhoneNumber);
    }

    /**
     * Private Copy constructor.
     *
     * @param outro
     */
    private Cliente(Cliente outro) {
        this(outro.getId(), outro.getName(),
                outro.getNIF(),
                outro.getUsername(),
                outro.getPassword(),
                outro.getAddress(),
                outro.getEmail(),
                outro.getMobilePhone());
    }

    /**
     * Give it back cell phone number attribute value.
     *
     * @return int
     */
    public int getMobilePhone() {
        return mobilePhone;
    }

    /**
     * Give it back username attribute value.
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Give it back password attribute value.
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Give it back address attribute value.
     *
     * @return Address object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Give it back ID attribute value.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Give it back name attribute value.
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Give it back NIF attribute value.
     *
     * @return int
     */
    public int getNIF() {
        return NIF;
    }

    /**
     * Give it back email attribute value.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email attribute value.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set cell phone Number value attribute
     *
     * @param cellphoneNumber
     */
    public void setMobilePhone(int cellphoneNumber) {
        this.mobilePhone = cellphoneNumber;
    }

    /**
     * Set username value attribute.
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set password value attribute.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set address value attribute.
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Set ID value attribute.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set name value attribute.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set NIF value attribute.
     *
     * @param NIF
     */
    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    /**
     * Test is a object is valid.
     *
     * @return boolean
     */
    public boolean validate() {
        if (getEmail() == null
                || getEmail().isEmpty()
                || !utils.ValidateEmail.isValidEmail(getEmail())) {
            return false;
        }

        if (getNIF() < 0) {
            return false;
        }

        if (getName() == null || getName().isEmpty()) {
            return false;
        }

        if (getMobilePhone() < 0 || getMobilePhone() > 999999999) {
            return false;
        }

        if (getUsername() == null || getUsername().isEmpty()) {
            return false;
        }

        if (getPassword() == null || getPassword().isEmpty()) {
            return false;
        }

        if (!getAddress().validate()) {
            return false;
        }

        return true;
    }

    /**
     * State of object.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ""
                + ", nome=" + name + ""
                + ", NIF=" + NIF + ""
                + ", username=" + username + ""
                + ", password=" + password + ""
                + ", morada=" + address + ""
                + ", email=" + email + ""
                + ", telemovel=" + mobilePhone + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        return hash;
    }

    /**
     * Test if two objects are equals. True, if same ID.
     *
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Compare two objects.
     *
     * @param other
     * @return int
     */
    @Override
    public int compareTo(Cliente other) {
        return this.getName().compareTo(other.getName());
    }

    /**
     * Returns new instance with same attributes values.
     *
     * @return Cliente instance
     */
    @Override
    public Cliente clone() {
        return new Cliente(this);
    }

}
