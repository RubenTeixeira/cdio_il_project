/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ConsultOccupationDeliveriesController;
import domain.DropPoint;
import java.util.List;

/**
 *
 * @author vascopinho
 */
public class ConsultOccupationDeliveriesUI {

    private controller.ConsultOccupationDeliveriesController controller;


    public ConsultOccupationDeliveriesUI() {
        controller = new ConsultOccupationDeliveriesController(Main.CREDENTIALS_FILE);
        run();
    }

    private void run() {

        List<DropPoint> deliveriesConsultationCollectionsDropPoint = controller.deliveriesConsultationCollectionsDropPoint();
        for (DropPoint dp : deliveriesConsultationCollectionsDropPoint) {
            System.out.println(dp.getId() + ". " + dp.getName());
        }
        System.out.println("Select DropPoint ID: \n");
        int droppoint = utils.ReadFromKeyboard.read();
        controller.selectDropPoint(droppoint);

        int op = 0;

        do {
            System.out.println("---------DropPoint: " + droppoint + "---------\n"
                    + "Options: \n"
                    + "1. List Deliveries\n"
                    + "2. List Pickups\n"
                    + "3. Check occupation\n"
                    + "0. Voltar\n");
            op = utils.ReadFromKeyboard.read();
            switch (op) {
                case 1:
                    System.out.println(controller.getListRegisterDelivered(null));
                    utils.ReadFromKeyboard.pressEnter();
                    break;

                case 2:
                    System.out.println(controller.getListRegistrationCollected(null));
                    utils.ReadFromKeyboard.pressEnter();
                    break;
                case 3:
                    System.out.println(controller.getOccupation(null));
                    utils.ReadFromKeyboard.pressEnter();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (op != 0);
    }

}
