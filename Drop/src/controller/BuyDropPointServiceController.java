package controller;

import domain.Client;
import domain.Management;
import dal.CostumerDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.DropPoint;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

public class BuyDropPointServiceController {

    private Management managment;
    private CostumerDAO registoCliente;
    private DropPointDAO dropPoint;
    private Client login;
    private int idDropPoint;
    private int idPrerenciasTemp;
    private int idPrerenciasDim;
    private int idToken;
    private SQLConnection manager;

    public BuyDropPointServiceController(String file)  {
        this.manager = persistence.OracleDb.getInstance(file);
        this.managment = new Management();
        try {
            dropPoint = (DropPointDAO) this.manager.getDAO(Table.DROPPOINT);
            registoCliente = (CostumerDAO) this.manager.getDAO(Table.COSTUMER);
        } catch (SQLException ex) {
            Logger.getLogger(BuyDropPointServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean clientLogin(String username, String password) {
        login = registoCliente.login(username, password);
        return (login != null);
    }

    public boolean clientWithLoginMade() {
        return login != null;
    }

    public List<DropPoint> listDropPoints() {
        return this.dropPoint.getListDropPoints();
    }

    public void selectDropPoint(int idDP) {
        this.idDropPoint = idDP;
    }

    public List<String> preferredTemperatureList() {
        return this.managment.ListPreferencesTemperature();
    }

    public void selectPreferredTemperature(int temp) {
        this.idPrerenciasTemp = temp;
    }

    public List<String> preferredDimensionsList() {
        return this.managment.ListPreferencesDimension();
    }

    public void selectPreferredDimensions(int dim) {
        this.idPrerenciasDim = dim;
    }

    public boolean confirmRegister() {
        idToken = managment
                .reserveCell(login.getId(), idDropPoint,
                        idPrerenciasTemp, idPrerenciasDim);

        return idToken != 0;
    }

    public String tokenClient() {
        return managment.tokenReferentReservaId(idToken);
    }

}
