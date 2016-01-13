/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class Maintenance implements Plannable {
    
    /**
     * The maintenance id
     */
    private int id;
    
    /**
     * The maintenance order index within its plan
     */
    private int index;
    
    /**
     * The maintenance dropoint
     */
    private DropPoint dropPoint;
    
    /**
     * The maintenance cells
     */
    private List<Integer> lstCells;
    
    /**
     * The maintenance start date
     */
    private Date startDate;
    
    /**
     * The maintenance finish date
     */
    private Date finishDate;

    /**
     * Constructs an Maintenance object with the specified parameters:
     * @param id the maintenance id
     * @param index
     * @param dropPoint the maintenance droppoint
     * @param startDate the maintenance start date
     * @param finishDate the maintenance finish date
     */
    public Maintenance(int id, int index, DropPoint dropPoint, Date startDate, Date finishDate) {
        this.id = id;
        this.index = index;
        this.dropPoint = dropPoint;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.lstCells = new ArrayList<>();
    }

    /**
     * Constructs an Maintenance object without parametrs
     */
    public Maintenance() {
        this.lstCells = new ArrayList<>();
    }

    /**
     * Returns the maintenance id
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the index of this maintenance within its plan
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the index
     * @param index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Sets a new maintenance id
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the maintenance droppoint
     * @return the droppoint
     */
    @Override
    public DropPoint getDropPoint() {
        return this.dropPoint;
    }

    /**
     * Sets the new maintenance droppoint
     * @param dropPoint the new droppoint
     */
    public void setDropPoint(DropPoint dropPoint) {
        this.dropPoint = dropPoint;
    }

    /**
     * Returns the maintenance list of cells 
     * @return the list of cells
     */
    public List<Integer> getLstCells() {
        return lstCells;
    }

    /**
     * Sets the new maitnenance list of cells
     * @param lstCells the new list of cells
     */
    public void setLstCells(List<Integer> lstCells) {
        this.lstCells = lstCells;
    }

    /**
     * Returns the maintenance start date
     * @return the start date
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Sets a new maintenance start date
     * @param startDate the new start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the maintenance finish date
     * @return the finish date
     */
    public Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * Sets the new maintenance finish date
     * @param finishDate the finish date
     */
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    
    /**
     * Adds a new cell id to the maintenance list of cells
     * @param id the id of the cell to add
     * @return true if adds correctly, false otherwise
     */
    public boolean addCellMaintenance(Integer id) {
        return this.lstCells.add(id);
    }
    
    /**
     * Removes a cell id from the maintenance list of cells
     * @param id the id of the cell to remove
     * @return true if removes correctly, false otherwise
     */
    public boolean removeCellMaintenance(Integer id) {
        return this.lstCells.remove(id);
    }
    
}
