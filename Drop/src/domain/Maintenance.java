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
public class Maintenance implements Plannable {
    
    private int id;
    private DropPoint dropPoint;
    private List<Integer> lstCells;
    private String startDate;
    private String finishDate;

    public Maintenance(int id, DropPoint dropPoint, String startDate, String finishDate) {
        this.id = id;
        this.dropPoint = dropPoint;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.lstCells = new ArrayList<>();
    }

    public Maintenance() {
        this.lstCells = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public DropPoint getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(DropPoint dropPoint) {
        this.dropPoint = dropPoint;
    }

    public List<Integer> getLstCells() {
        return lstCells;
    }

    public void setLstCells(List<Integer> lstCells) {
        this.lstCells = lstCells;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    
    public boolean addCellMaintenance(Integer id) {
        return this.lstCells.add(id);
    }
    
    public boolean removeCellMaintenance(Integer id) {
        return this.lstCells.remove(id);
    }
    
}
