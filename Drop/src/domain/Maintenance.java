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
     * Maintenance corresponding Plan ID
     */
    private int planID;
    
    /**
     * DropPoint ID
     */
    private int dropPointID;
    
    /**
     * Constructs an Maintenance object with the specified parameters:
     * @param index
     * @param dropPoint the maintenance droppoint
     * @param startDate the maintenance start date
     * @param finishDate the maintenance finish date
     * @param planID
     */
    public Maintenance(int index, DropPoint dropPoint, Date startDate, Date finishDate, int planID) {
        this.index = index;
        this.dropPoint = dropPoint;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.planID = planID;
        this.lstCells = new ArrayList<>();
    }
    
    public Maintenance(int id, int index, DropPoint dropPoint, Date startDate, Date finishDate, int planID) {
        this(index, dropPoint, startDate, finishDate, planID);
        this.id = id;
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
     * return the DropPoint ID
     * @return DropPoint ID
     */
    public int getDropPointID()
    {
        if(dropPoint==null){
            return this.dropPointID;
        }else{
            return dropPoint.getId();
        }
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
     * Sets de DropPoint ID
     * @param dropPointID DropID
     */
    public void setDropPointID(int dropPointID)
    {
        if(dropPoint==null){
            this.dropPointID = dropPointID;
        }
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

    public int getPlanID() {
        return planID;
    }

    public void setPlanID(int planID) {
        this.planID = planID;
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
    
    @Override
    public String toString()
    {
        return "Manutenção: " + this.getId()+" DropPoint: "+this.dropPointID;
    }
}
