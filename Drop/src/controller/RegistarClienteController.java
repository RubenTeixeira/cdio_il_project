package controller;

import domain.Cliente;
import domain.Morada;
import domain.RegistoCliente;
import domain.RegistoMorada;

public class RegistarClienteController {

   
    private RegistoCliente registoCliente;
    private RegistoMorada registoMorada; 
    private Morada morada;

    public RegistarClienteController() {
        this.registoCliente = new RegistoCliente();
        this.registoMorada = new RegistoMorada(registoCliente.getConnection());
    }
    
    public boolean novaMorada(String rua, String codigoPostal, String localidade){
        morada = registoMorada.novaMorada(rua, codigoPostal, localidade);
        return registoMorada.registarMorada(morada);
    }
    

    public boolean novoCliente(int id, String nome, int NIF, String username, String password, String email, int telemovel) {
        Cliente novoCliente = registoCliente.novoCliente(id, nome, NIF, username, password, morada, email, telemovel);
        registoCliente.registarCliente(novoCliente);
        return (novoCliente != null);
    }

}
