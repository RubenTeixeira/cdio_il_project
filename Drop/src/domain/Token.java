/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import persistence.SQLConnection;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public interface Token {

    int getId();
    void setId(int id);
    String getCodigo();
    void setCodigo(String codigo);
    int getIdReserva();
    void setIdReserva(int idReserva);
    TransaccaoPrateleira novaTransaccao(SQLConnection manager);
    Prateleira getPrateleira(SQLConnection manager);
    void terminar(SQLConnection manager, TransaccaoPrateleira transaccao);
 
}
