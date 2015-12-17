/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.MaintenancePickup;
import domain.Cell;
import domain.Token;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import persistence.DeliveryDAO;
import persistence.MaintenancePickupDAO;
import persistence.CellDAO;
import persistence.SQLConnection;
import persistence.Table;
import persistence.TokenDAO;

/**
 *
 * @author MarcoSousa
 */
public class MaintenancePickupController {

    private int dropID = 1;
    private SQLConnection manager;
    private Cell cell;
    private Token token;
    private TokenDAO tokenDAO;
    private CellDAO cellDAO;
    private DeliveryDAO deliveryDAO;
    private MaintenancePickupDAO maintenancePickupDAO;
    private MaintenancePickup maintenancePickup;

    public MaintenancePickupController(String file) {
        try {
            this.manager = persistence.OracleDb.getInstance(file);
            this.tokenDAO = (TokenDAO) this.manager.getDAO(Table.TOKEN);
            this.cellDAO = (CellDAO) this.manager.getDAO(Table.CELL);
            this.deliveryDAO = (DeliveryDAO) this.manager.getDAO(Table.DELIVERY);
            this.maintenancePickupDAO = (MaintenancePickupDAO) this.manager.getDAO(Table.MAINTENANCE_PICKUP);
        } catch (SQLException e) {

        }
    }

    public List<Cell> getListOfCellsWithDeliveriesOutOfDate(String tokenCode) {
        this.token = tokenDAO.getByCode(tokenCode);
        if(this.token != null) {
            return this.cellDAO.getListOfCellsWithDeliveriesOutOfDateOrderByTemperature(dropID, tokenCode);
        }
        return null;
    }

    public void selectCell(Cell cell) {
        this.cell = cell;
    }

    public void openCell() {
        int deliveryID = this.deliveryDAO.getDeliveryIdByCellId(cell.getId());
        this.maintenancePickup = this.maintenancePickupDAO.newMaintenancePickup();
        this.maintenancePickup.setDeliveryId(deliveryID);
        this.maintenancePickup.setTokenId(this.token.getId());
        Date data = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        this.maintenancePickup.setDateOpen(ft.format(data));
    }

    public boolean closeCell() {
        Date data = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        this.maintenancePickup.setDateClose(ft.format(data));
        int maintenanceID = this.maintenancePickupDAO.getNextId();
        this.maintenancePickup.setMaintenacePickupId(maintenanceID);
        if(maintenancePickup.validate()) {
            return this.maintenancePickupDAO.insertNew(maintenancePickup);
        }
        return false;
    }
}
