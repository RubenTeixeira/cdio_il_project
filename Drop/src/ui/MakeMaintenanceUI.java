/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.MakeMaintenanceController;
import domain.Cabinet;
import domain.Cell;
import domain.DropPoint;
import domain.IncidentType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import utils.ReadFromKeyboard;

/**
 *
 * @author nuno
 */
class MakeMaintenanceUI {

    private MakeMaintenanceController controller;
    private Deque<Cell> cellsToOpen;

    public MakeMaintenanceUI(String file) throws SQLException {
        controller = new MakeMaintenanceController(file);

    }

    public int run() throws SQLException {
        System.out.println("Select Cabinet ID:");

        List<Cabinet> listCabinetsInMaintenance = new ArrayList<>();

        List<DropPoint> listDropPoints = controller.listDropPoints();
        listDropPoints.stream().forEach((drop) -> {
            listCabinetsInMaintenance.addAll(controller.listCabinetsInMaintenance(drop.getId()));
        });

        int i = 1;
        for (Cabinet cabinet : listCabinetsInMaintenance) {
            System.out.println("Cabinet " + i + " with id: " + cabinet.getId() + " and name: " + cabinet.getName());
            i++;
        }

        int op = ReadFromKeyboard.read();
        controller.selectCabinet(listCabinetsInMaintenance.get(op - 1));

        cellsToOpen = controller.cellsToOpen();
        return runMaintenance(cellsToOpen);
    }

    public int runMaintenance(Deque<Cell> cellsToOpen) throws SQLException {
        int i;
        int op;
        Iterator<Cell> iterator = cellsToOpen.iterator();
        while (iterator.hasNext()) {
            Cell next = iterator.next();
            System.out.println("Open " + next.getDescription() + " Confirm y/n?");
            String confirm = ReadFromKeyboard.readString();
            if (confirm.equalsIgnoreCase("Y")) {
                if (controller.openCell(next)) {
                    System.out.println("Cell is open!");
                    System.out.println("Insert report:");
                    String report = ReadFromKeyboard.readString();
                    if (!controller.insertReport(report)) {
                        System.out.println("Not possible register report!");
                    }
                    do {
                        System.out.println("Is cell operational? (0-No, 1-Yes)");
                        op = ReadFromKeyboard.read();
                    } while (op != 0 && op != 1);
                    if (op == 0) {
                        controller.createIncident();
                        System.out.println("Choose the incident type:");
                        List<IncidentType> listIncidType = controller.getIncidentsType();
                        i = 0;
                        for (IncidentType iType : listIncidType) {
                            System.out.println(iType.getIncidentType_id() + " : " + iType.getDescription());
                            i++;
                        }
                        int i_type;
                        do {
                            i_type = ReadFromKeyboard.read();
                        } while (i_type <= 0 || i_type > i);
                        if (!controller.selectIncidentType(i_type)) {
                            System.out.println("Not possible register the incident!");
                        }
                    }
                    System.out.println("Cell is closed y/n?");
                    confirm = ReadFromKeyboard.readString();
                    if (confirm.equalsIgnoreCase("y")) {
                        if (controller.closeCell()) {
                            System.out.println("Successfully processed!");
                            cellsToOpen.remove(next);
                            System.out.println("Suspend Maintenabce? y/n");
                            confirm = ReadFromKeyboard.readString();
                            if (confirm.equalsIgnoreCase("y")) {
                                return -1;
                            }
                        }
                    }

                }
            }
        }
        return 0;
    }

    public void resume() throws SQLException {
        runMaintenance(cellsToOpen);
    }

}
