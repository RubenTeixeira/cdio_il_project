package domain;

public class Cliente {

    private String email;
    private int telemovel;
    private String username;
    private String password;
    private Morada morada;

    public Cliente(String email, int telemovel, String username, String password, Morada morada) {
        this.email = email;
        this.telemovel = telemovel;
        this.username = username;
        this.password = password;
        this.morada = morada;
    }

    public String getEmail() {
        return email;
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

    public boolean valida() {
        if (getEmail() == null || getEmail().isEmpty()) {
            return false;
        }
        if (getTelemovel() < 0 || getTelemovel()>999999999) {
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
        return String.format("Email: %s\n"
                + "Telemovel: %d\n"
                + "Username: %s\n"
                + "Password: %s\n"
                + "Morada:\n"
                + " -rua: %s\n"
                + " -código postal: %s\n"
                + " -localidade: %s", email, telemovel, username, password, morada.getRua(), morada.getCodigoPostal(), morada.getLocalidade());
    }

}
