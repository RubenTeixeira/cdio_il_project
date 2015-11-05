/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Gestao;
import java.sql.ResultSet;
import java.util.List;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */


public class ConsultarOcupacaoEntregasController {

    private static SQLConnection con;
    private ResultSet rs;
    private int idDropPoint ;
    private Gestao gestao;

    public ConsultarOcupacaoEntregasController() {
        this.gestao = new Gestao();
    }
    
    public List<String> iniciarConsultaEntregasRecolhasDroppoint()  {
        return gestao.listarDropPoint();
    }

    public void seleccionarDroppoint(int id) {
        this.idDropPoint = id;
    }

    public List<String> getListaRegistoEntregues() {
       return gestao.listarEntregas(this.idDropPoint);
    }

    public List<String> getListaRegistoRecolhidas() {
        return gestao.listarRecolhidas(this.idDropPoint);
    }

    public void getOcupacao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
