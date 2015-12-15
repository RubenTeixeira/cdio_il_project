package controller;

import domain.Cliente;
import domain.Morada;
import domain.RegistoCliente;
import domain.RegistoMorada;
import persistence.SQLConnection;

public class RegistarClienteController {

    private RegistoCliente registoCliente;
    private RegistoMorada registoMorada;
    private Morada morada;

    public RegistarClienteController(SQLConnection con) {
        this.registoCliente = new RegistoCliente(con);
        this.registoMorada = new RegistoMorada(con);
    }

    public boolean novaMorada(String rua, String codigoPostal, String localidade) {
        morada = registoMorada.novaMorada(rua, codigoPostal, localidade);
        return registoMorada.registarMorada(morada);
    }

    public boolean novoCliente(int id, String nome, int NIF, String username, String password, String email, int telemovel) {
        Cliente novoCliente = registoCliente.novoCliente(id, nome, NIF, username, password, morada, email, telemovel);

        return registoCliente.registarCliente(novoCliente);
    }

}
