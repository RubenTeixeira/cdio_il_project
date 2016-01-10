package controller;

import domain.Client;
import domain.Address;
import dal.CostumerDAO;
import dal.Table;
import dal.AddressDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

public class ClientRegisterController {

    private CostumerDAO registoCliente;
    private AddressDAO registoMorada;
    private Address morada;
    private SQLConnection manager;

    public ClientRegisterController(String file) {
        this.manager = persistence.OracleDb.getInstance(file);
        try {
            this.registoCliente = (CostumerDAO) this.manager.getDAO(Table.COSTUMER);
            this.registoMorada = (AddressDAO) this.manager.getDAO(Table.ADDRESS);
        } catch (SQLException ex) {
            Logger.getLogger(ClientRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean newAddress(String street, String postalCode, String locality) throws SQLException {
        morada = registoMorada.newAddress(street, postalCode, locality);
        return registoMorada.registerAddress(morada);
    }

    public boolean newClient(int id,
            String nome,
            int NIF,
            String username,
            String password,
            String email,
            int telemovel) {
        Client novoCliente = registoCliente
                .newClient(id, nome, NIF, username, password, morada, email, telemovel);

        return registoCliente.clientRegister(novoCliente);
    }

}
