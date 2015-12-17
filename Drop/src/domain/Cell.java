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
public class Cell {
    
    private int id;
    private String description;

    public Cell(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Cell() {
        this.id = 0;
        this.description = "";
    }

    /* Getter methods*/
    public int getId() {return id;}
    public void setDescription(String description) {this.description = description;}
    /* Setter methods*/
    public String getDescription() {return description;}
    public void setId(int id) {this.id = id;}
    
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
        final Cell other = (Cell) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return this.description;
    }
    
}
