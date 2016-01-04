package controller;

import domain.Client;
import domain.Gestao;
import dal.ClientDAO;
import dal.Table;
import java.sql.SQLException;
import java.util.List;
import persistence.SQLConnection;

public class BuyDropPointServiceController {

    private Gestao gestao;
    private ClientDAO registoCliente;
    private Client login;
    private int idDropPoint;
    private int idPrerenciasTemp;
    private int idPrerenciasDim;
    private int idToken;
    private SQLConnection manager;

    public BuyDropPointServiceController(String file) throws SQLException {
        this.manager = persistence.OracleDb.getInstance(file);
        this.gestao = new Gestao();
        registoCliente = (ClientDAO) this.manager.getDAO(Table.CLIENTE);

    }

    public boolean clientLogin(String username, String password) {
        login = registoCliente.login(username, password);
        return (login != null);
    }

    public boolean clientWithLoginMade() {
        return login != null;
    }

    public String listDropPoints() {
        return gestao.listarDropPoint();
    }

    public void selectDropPoint(int idDP) {
        this.idDropPoint = idDP;
    }

    public List<String> preferredTemperatureList() {
        return this.gestao.ListarPreferenciasTemperatura();
    }

    public void selectPreferredTemperature(int temp) {
        this.idPrerenciasTemp = temp;
    }

    public List<String> preferredDimensionsList() {
        return this.gestao.ListarPreferenciasDimensao();
    }

    public void selectPreferredDimensions(int dim) {
        this.idPrerenciasDim = dim;
    }

    public boolean confirmRegister() {
        idToken = gestao.reservaPrateleira(login.getId(), idDropPoint, idPrerenciasTemp, idPrerenciasDim);

        return idToken != 0;
    }

    public String tokenClient() {
        return gestao.tokenReferentReservaId(idToken);
    }

}
