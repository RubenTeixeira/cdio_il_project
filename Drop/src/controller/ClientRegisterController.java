package controller;

import domain.Client;
import domain.Address;
import dal.CostumerDAO;
import dal.Table;
import dal.AddressDAO;
import java.sql.SQLException;
import persistence.SQLConnection;

public class ClientRegisterController  {

    private CostumerDAO registoCliente;
    private AddressDAO registoMorada;
    private Address morada;
     private SQLConnection manager;
    
    public ClientRegisterController(String file) throws SQLException {
        this.manager = persistence.OracleDb.getInstance(file);
        this.registoCliente = (CostumerDAO) this.manager.getDAO(Table.COSTUMER);
        this.registoMorada = (AddressDAO) this.manager.getDAO(Table.ADDRESS);
    }

    public boolean newAddress(String rua, String codigoPostal, String localidade) throws SQLException {
        morada = registoMorada.newAddress(rua, codigoPostal, localidade);
        return registoMorada.registerAddress(morada);
    }

    public boolean newClient(int id, String nome, int NIF, String username, String password, String email, int telemovel) {
        Client novoCliente = registoCliente.newClient(id, nome, NIF, username, password, morada, email, telemovel);

        return registoCliente.clientRegister(novoCliente);
    }

}
