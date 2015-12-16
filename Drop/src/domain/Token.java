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
public class Token {
    
    private int id;
    private String generationDate;
    private String expirationDate;
    private String type;
    private int state; 
    private String code;  
    private int idReservation;

    public Token(int id, String generationDate, String expirationDate, String type, int state, String code, int idReservation) {
        this.id = id;
        this.generationDate=generationDate;
        this.expirationDate=expirationDate;
        this.type = type;
        this.state=state;
        this.code = code;        
        this.idReservation = idReservation;
    }

    public int getId() {
        return id;
    }

    public String getGenerationDate() {
        return generationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenerationDate(String generationDate) {
        this.generationDate = generationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public TransaccaoPrateleira novaTransaccao() {
        if (this.type.equalsIgnoreCase("estafeta")) {
            return new Entrega(this.idReservation,this.id);
        } else if (this.type.equalsIgnoreCase("cliente")) {
            return new Recolha(this.id);
        }
        return null;
    }
    
    
    
}
