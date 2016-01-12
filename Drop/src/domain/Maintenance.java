/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
class Maintenance implements Plannable {
    
    private DropPoint dropPoint;
    private List<Cell> lstCells;
    private String date;

    public Maintenance(DropPoint dropPoint, String date) {
        this.dropPoint = dropPoint;
        this.date = date;
        this.lstCells = new ArrayList<>();
    }

    public Maintenance() {
        this.lstCells = new ArrayList<>();
    }

    public DropPoint getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(DropPoint dropPoint) {
        this.dropPoint = dropPoint;
    }

    public List<Cell> getLstCells() {
        return lstCells;
    }

    public void setLstCells(List<Cell> lstCells) {
        this.lstCells = lstCells;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
