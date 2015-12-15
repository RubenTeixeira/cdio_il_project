package controller;

import domain.Cliente;
import domain.Gestao;
import domain.RegistoCliente;
import java.util.List;
import persistence.SQLConnection;

public class ComprarServicoDPController {

    private Gestao gestao;
    private RegistoCliente registoCliente;
    private Cliente login;
    private int idDropPoint;
    private int idPrerenciasTemp;
    private int idPrerenciasDim;
    private int idToken;

    public ComprarServicoDPController(SQLConnection con) {
        this.gestao = new Gestao();
        registoCliente = new RegistoCliente(this.gestao.getBd());
       
    }

    public boolean loginCliente(String username, String password) {
        login = registoCliente.login(username, password);
        return (login != null);
    }

    public boolean clienteComLoginFeito() {
        return login != null;
    }

    public String listarDropPoints() {
        return gestao.listarDropPoint();
    }

    public void SelecionarDropPoint(int idDP) {
        this.idDropPoint = idDP;
    }

    public List<String> listarPreferenciasTemp() {
        return this.gestao.ListarPreferenciasTemperatura();
    }

    public void SelecionarPreferenciasTemp(int temp) {
        this.idPrerenciasTemp = temp;
    }

    public List<String> listarPreferenciasDim() {
        return this.gestao.ListarPreferenciasDimensao();
    }

    public void SelecionarPreferenciasDim(int dim) {
        this.idPrerenciasDim = dim;
    }

    public boolean confirmarRegisto() {
        idToken = gestao.reservaPrateleira(login.getId(), idDropPoint, idPrerenciasTemp, idPrerenciasDim);

        return idToken != 0;
    }

    public String tokenCliente() {
        return gestao.tokemReferentReservaId(idToken);
    }

    public void closeConection() {
        gestao.closeConection();
        registoCliente.closeConection();
    }

}
