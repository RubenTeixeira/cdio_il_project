/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ListMaintenanceController;
import domain.DropPoint;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vascopinho
 */
class ListMaintenanceUI {

    private controller.ListMaintenanceController controller;

    public ListMaintenanceUI() {
        controller = new ListMaintenanceController(Main.CREDENTIALS_FILE);
        run();
    }

    private void run() {

        List<DropPoint> deliveriesConsultationCollectionsDropPoint = controller.listDropPoints();
        for (DropPoint dp : deliveriesConsultationCollectionsDropPoint) {
            System.out.println(dp.getId() + ". " + dp.getName());
        }

        System.out.println("Seleccione id do DropPoint: \n");
        int droppoint = utils.ReadFromKeyboard.read();
        controller.selectDropPoint(droppoint);
        
        List<String> lMain = controller.getMaintenanceList();
        for (String main : lMain) {
            System.out.println(main);
        }

    }

}
