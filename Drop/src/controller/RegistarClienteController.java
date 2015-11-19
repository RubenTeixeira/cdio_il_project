package controller;

import domain.Cliente;
import domain.Gestao;
import domain.Morada;
import domain.RegistoCliente;

public class RegistarClienteController {

    private Gestao gestao;
    private RegistoCliente registoCliente;

    public RegistarClienteController() {
        this.gestao = new Gestao();
        this.registoCliente = new RegistoCliente();
    }

    public boolean novoCliente(int id, String nome, int NIF, String username, String password, Morada morada, String email, int telemovel) {

        Cliente novoCliente = registoCliente.novoCliente(id, nome, NIF, username, password, morada, email, telemovel);
        registoCliente.registarCliente(novoCliente);
        return (novoCliente != null);
    }

}
