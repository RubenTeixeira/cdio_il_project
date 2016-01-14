/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ListRepairController;
import domain.DropPoint;
import java.util.List;

/**
 *
 * @author vascopinho
 */
class ListRepairUI {

    private controller.ListRepairController controller;

    public ListRepairUI() {
        controller = new ListRepairController(Main.CREDENTIALS_FILE);
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

        List<String> lRep = controller.getRepairCompleted();
        if (!lRep.isEmpty()) {
            for (String rep : lRep) {
                System.out.println(rep);
            }
        } 
        else { System.out.println("No records");
        
        }
    }

}
