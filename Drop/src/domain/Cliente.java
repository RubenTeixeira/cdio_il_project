package domain;

public class Cliente implements Comparable<Cliente>, Cloneable {

    private int id;
    private String nome;
    private int NIF;
    private String username;
    private String password;
    private Morada morada;
    private String email;
    private int telemovel;

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
    public Cliente(int id, String name, int NIF, String username, String password, Morada address, String email, int cellPhoneNumber) {
        this.setId(id);
        this.setNome(name);
        this.setNIF(NIF);
        this.setUsername(username);
        this.setPassword(password);
        this.setMorada(address);
        this.setEmail(email);
        this.setTelemovel(cellPhoneNumber);
    }

    /**
     * Private Copy constructor.
     *
     * @param outro
     */
    private Cliente(Cliente outro) {
        this(outro.getId(), outro.getNome(),
                outro.getNIF(),
                outro.getUsername(),
                outro.getPassword(),
                outro.getMorada(),
                outro.getEmail(),
                outro.getTelemovel());
    }

    /**
     * Give it back cell phone number attribute value.
     *
     * @return int
     */
    public int getTelemovel() {
        return telemovel;
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
     * Give it back morada attribute value.
     *
     * @return Morada object
     */
    public Morada getMorada() {
        return morada;
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
    public String getNome() {
        return nome;
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
    public void setTelemovel(int cellphoneNumber) {
        this.telemovel = cellphoneNumber;
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
    public void setMorada(Morada address) {
        this.morada = address;
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
    public void setNome(String name) {
        this.nome = name;
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
    public boolean valida() {
        if (getEmail() == null
                || getEmail().isEmpty()
                || !utils.ValidateEmail.isValidEmail(getEmail())) {
            return false;
        }

        if (getNIF() < 0) {
            return false;
        }

        if (getNome() == null || getNome().isEmpty()) {
            return false;
        }

        if (getTelemovel() < 0 || getTelemovel() > 999999999) {
            return false;
        }

        if (getUsername() == null || getUsername().isEmpty()) {
            return false;
        }

        if (getPassword() == null || getPassword().isEmpty()) {
            return false;
        }

        if (!getMorada().valida()) {
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
                + ", nome=" + nome + ""
                + ", NIF=" + NIF + ""
                + ", username=" + username + ""
                + ", password=" + password + ""
                + ", morada=" + morada + ""
                + ", email=" + email + ""
                + ", telemovel=" + telemovel + '}';
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
        return this.getNome().compareTo(other.getNome());
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
