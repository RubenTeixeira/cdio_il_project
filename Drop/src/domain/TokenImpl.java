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
    private String generationDate;
    private String expirationDate;
    private int state;
    private String code;
    private int idReservation;

    /**
     * SuperClass Constructor
     * @param id Token id
     * @param generationDate Date from which the token was generated 
     * @param expirationDate Date from which the token will be considered expired
     * @param state Token state. Can be active (1) or inactive (0)
     * @param code Actual token. May vary in the future
     * @param idReservation Rservation ID corresponding to this token
     */
    public TokenImpl(int id, String generationDate, String expirationDate, int state, String code, int idReservation) {
        this.id = id;
        this.generationDate = generationDate;
        this.expirationDate = expirationDate;
        this.state = state;
        this.code = code;
        this.idReservation = idReservation;
    }

    public TokenImpl() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getGenerationDate() {
        return generationDate;
    }

    @Override
    public String getExpirationDate() {
        return expirationDate;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getReservationId() {
        return idReservation;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setGenerationDate(String generationDate) {
        this.generationDate = generationDate;
    }

    @Override
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void setCode(String codigo) {
        this.code = codigo;
    }

    @Override
    public void setReservationId(int idReserva) {
        this.idReservation = idReserva;
    }

}
