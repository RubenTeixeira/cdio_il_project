/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public abstract class TokenImpl implements Token {
    
    private int id;
    private String codigo;
    private int idReserva;

    public TokenImpl(int id, String codigo, int idReserva) {
        this.id = id;
        this.codigo = codigo;
        this.idReserva = idReserva;
    }

    public TokenImpl() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return codigo;
    }

    @Override
    public void setCode(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public int getReservationId() {
        return idReserva;
    }

    @Override
    public void setReservationId(int idReserva) {
        this.idReserva = idReserva;
    }

}
