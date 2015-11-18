package domain;

public class Morada {

    private String rua;
    private String codigoPostal;
    private String localidade;

    public Morada(String rua, String codigoPostal, String localidade) {
        this.rua = rua;
        this.codigoPostal = codigoPostal;
        this.localidade = localidade;
    }

    public String getRua() {
        return rua;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

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

    @Override
    public String toString() {
        return String.format("Rua: %s\n"
                + "Código Postal: %s\n"
                + "Localidade: %s\n", getRua(), getCodigoPostal(), getLocalidade());

    }

}
