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
public class Prateleira {
    
    private int id;
    private String desc;

    public Prateleira(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Prateleira() {
        this.id = 0;
        this.desc = "";
    }

    /* Getter methods*/
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    /* Setter methods*/
    public String getDesc() {return desc;}
    public void setDesc(String desc) {this.desc = desc;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prateleira other = (Prateleira) obj;
        return this.id == other.id;
    }
    
    
    
    
}
