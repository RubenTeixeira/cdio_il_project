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

    public Cliente() {
    }

    public Cliente(int id, String nome, int NIF, String username, String password, Morada morada, String email, int telemovel) {
        this.setId(id);
        this.setNome(nome);
        this.setNIF(NIF);
        this.setUsername(username);
        this.setPassword(password);
        this.setMorada(morada);
        this.setEmail(email);
        this.setTelemovel(telemovel);
    }

    public Cliente(Cliente outro) {
        this(outro.getId(), outro.getNome(),
                outro.getNIF(),
                outro.getUsername(),
                outro.getPassword(),
                outro.getMorada(),
                outro.getEmail(),
                outro.getTelemovel());
    }

    public int getTelemovel() {
        return telemovel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Morada getMorada() {
        return morada;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getNIF() {
        return NIF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMorada(Morada morada) {
        this.morada = morada;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public boolean valida() {
        if (getEmail() == null || getEmail().isEmpty()) {
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
        if (getMorada() == null || getMorada().getRua().isEmpty() || getMorada().getCodigoPostal().isEmpty() || getMorada().getLocalidade().isEmpty()) {
            return false;
        }
        return true;
    }

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

    @Override
    public int compareTo(Cliente o) {
        return this.getNome().compareTo(o.getNome());
    }

    @Override
    public Cliente clone() {
        return new Cliente(this);
    }

}
