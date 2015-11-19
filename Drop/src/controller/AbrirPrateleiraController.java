/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.*;

/**
 *
 * @author 1140864
 */
public class AbrirPrateleiraController {

    private TransaccaoPrateleira trans;
    private Prateleira prateleira;
    private Gestao gestao;

    public AbrirPrateleiraController() {
        this.gestao = new Gestao();
    }

    public String iniciaAberturaPrateleira(String tok) {
        Token token = this.gestao.getToken(tok);

        if (token.getTipo().equalsIgnoreCase("estafeta")) {
            this.trans = new Entrega();
            this.prateleira = gestao.procurarPrateleira(trans, token);
            ((Entrega) this.trans).setIdReservation(token.getIdReserva());
            ((Entrega) this.trans).setIdPrat(this.prateleira.getId());
        } else if (token.getTipo().equalsIgnoreCase("cliente")) {
            this.trans = new Recolha();
            this.prateleira = gestao.procurarPrateleira(trans, token);
            int idEntrega = this.gestao.getIdEntregaCorrespondente(token);

            if (idEntrega == -1) {
                return null;
            }

            ((Recolha) this.trans).setIdEntrega(idEntrega);
        }
            this.trans.setIdToken(token.getId());
        return this.prateleira.toString();
    }

    public void abrePrateleira() {
        this.gestao.setDataAbertura(this.trans);
    }

    public void fechaPrateleira() {
        this.gestao.setDataFecho(this.trans);
    }
}
