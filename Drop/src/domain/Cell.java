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
    private int isOperational;

    /**
     * Constructor with two parametres: id and description
     *
     * @param id
     * @param description
     */
    public Cell(int id, String description) {
        this.id = id;
        this.description = description;
    }

    /**
     * Constructor with no parametres
     */
    public Cell() {
        this.id = 0;
        this.description = "";
    }

    /* Getter methods*/
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int isOperational() {
        return isOperational;
    }

    /* Setter methods*/
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsOperational(int isOperational) {
        this.isOperational = isOperational;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    /**
     * Compares if the parametre is equal to the previous instance of Cell
     *
     * @param obj
     * @return
     */
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

    /**
     * Returns a string with the values of the parametres of a Cell instance
     * @return 
     */
    @Override
    public String toString() {
        return "id: " + this.id
                + "description: " + this.description;
    }

}
