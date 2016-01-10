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

    private void run()  {
       
        
        List<DropPoint> deliveriesConsultationCollectionsDropPoint = controller.deliveriesConsultationCollectionsDropPoint();
        System.out.println(deliveriesConsultationCollectionsDropPoint);

        System.out.println("Seleccione id do DropPoint: \n");
        int droppoint = utils.ReadFromKeyboard.read();
        controller.selectDropPoint(droppoint);

        int op = 0;

        do {
            System.out.println("---------DropPoint: " + droppoint + "---------\n"
                    + "Seleccione uma das opções: \n"
                    + "1. Consultar Entregas\n"
                    + "2. Consultas Recolhas\n"
                    + "3. Consultar Ocupação\n"
                    + "4. Voltar\n");
            op = utils.ReadFromKeyboard.read();
            switch (op) {
                case 1:
                    System.out.println(controller.getListRegisterDelivered());
                    utils.ReadFromKeyboard.pressEnter();
                    break;

                case 2:
                    System.out.println(controller.getListRegistrationCollected());
                    utils.ReadFromKeyboard.pressEnter();
                    break;
                case 3:
                    System.out.println(controller.getOccupation());
                    utils.ReadFromKeyboard.pressEnter();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 4);
    }

}
