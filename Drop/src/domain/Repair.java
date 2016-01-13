/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Objects;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class Repair implements Plannable {
    
    private int incidentID;
    private int index;
    private DropPoint dropPoint;
    private String repairDate;
    private String observations;
    private String partsUsed;

    public Repair(int incidentID) {
        this.incidentID = incidentID;
    }

    public int getIncidentID() {
        return incidentID;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public DropPoint getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(DropPoint dropPoint) {
        this.dropPoint = dropPoint;
    }
    
    public void setIncidentID(int incidentID) {
        this.incidentID = incidentID;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getPartsUsed() {
        return partsUsed;
    }

    public void setPartsUsed(String partsUsed) {
        this.partsUsed = partsUsed;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.incidentID;
        hash = 41 * hash + Objects.hashCode(this.repairDate);
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
        final Repair other = (Repair) obj;
        if (this.incidentID != other.incidentID) {
            return false;
        }
        return this.repairDate.equals(other.repairDate);
    }

    @Override
    public String toString() {
        return "Incident number: " + incidentID + "; Repair Date: " + repairDate;
    }
    
    
    
}
