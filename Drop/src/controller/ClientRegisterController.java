package controller;

import domain.Cliente;
import domain.Address;
import domain.RegistoCliente;
import domain.RegistoMorada;
import persistence.SQLConnection;

public class ClientRegisterController {

    private RegistoCliente registoCliente;
    private RegistoMorada registoMorada;
    private Address morada;

    public ClientRegisterController(SQLConnection con) {
        this.registoCliente = new RegistoCliente(con);
        this.registoMorada = new RegistoMorada(con);
    }

    public boolean newAddress(String rua, String codigoPostal, String localidade) {
        morada = registoMorada.novaMorada(rua, codigoPostal, localidade);
        return registoMorada.registarMorada(morada);
    }

    public boolean newClient(int id, String nome, int NIF, String username, String password, String email, int telemovel) {
        Cliente novoCliente = registoCliente.novoCliente(id, nome, NIF, username, password, morada, email, telemovel);

        return registoCliente.registarCliente(novoCliente);
    }

}
