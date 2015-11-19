package controller;

import domain.Cliente;
import domain.Gestao;
import domain.RegistoCliente;

public class ComprarServicoDPController {

    private Gestao gestao;
    private RegistoCliente registoCliente;
    private Cliente login;
    
    public ComprarServicoDPController() {
        this.gestao = new Gestao();
        registoCliente = new RegistoCliente();
    }

    public boolean loginCliente(String username, String password) {
        login = registoCliente.login(username, password);
        return (login != null);
    }
    
    public boolean clienteComLoginFeito(){
        return login != null;
    }

    public String listarDropPoints() {
        return gestao.listarDropPoint();
    }

    public void SelecionarDropPoint(int idDP) {

    }

    public void SelecionarPreferencias() {

    }

    public void confirmarRegisto() {

    }

}
