/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Gestao;
import domain.Prateleira;
import java.util.List;

/**
 *
 * @author 1140864
 */
public class AbrirPrateleiraController {

    private Prateleira prateleira;

    private Gestao gestao;

    public AbrirPrateleiraController() {
        this.gestao = new Gestao();
    }

    public String iniciaAberturaPrateleira(String token) {

        this.prateleira = gestao.procurarPrateleiras(token);
        return this.prateleira.toString();
    }

    public void abrePrateleira() {
        this.gestao.setDataAbertura(prateleira.getId());
    }

    public void fechaPrateleira() {
        this.gestao.setDataFecho(prateleira.getId());
    }
}
