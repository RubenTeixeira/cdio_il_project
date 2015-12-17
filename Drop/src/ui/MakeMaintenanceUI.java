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

    public MakeMaintenanceUI(List<String> file) throws SQLException {
        controller = new MakeMaintenanceController(file);
        run();
    }

    private void run() throws SQLException {
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

        Deque<Cell> cellsToOpen = controller.cellsToOpen();
        Iterator<Cell> iterator = cellsToOpen.iterator();

        while (iterator.hasNext()) {
            Cell next = iterator.next();
            System.out.println("Open " + next.getDescription() + " Confirm s/n?");
            String confirm = ReadFromKeyboard.readString();
            if (confirm.equalsIgnoreCase("S")) {
                if (controller.openCell(next)) {
                    System.out.println("Cell is open!");

                    System.out.println("Insert report:");
                    String report = ReadFromKeyboard.readString();
                    if (!controller.insertReport(report)) {
                        System.out.println("Not possible register report!");
                    }

                    System.out.println("Cell is closed s/n?");
                    confirm = ReadFromKeyboard.readString();
                    if (confirm.equalsIgnoreCase("s")) {
                        if (controller.closeCell()) {
                            System.out.println("Successfully processed!");
                        }
                    }

                }
            }
        }
    }
}
