/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ConsultarOcupacaoEntregasController;
import java.util.List;

/**
 *
 * @author vascopinho
 */
public class ConsultarOcupacaoEntregaUI {

    private controller.ConsultarOcupacaoEntregasController controller;

    public ConsultarOcupacaoEntregaUI() {
        controller = new ConsultarOcupacaoEntregasController();
        run();
    }

    private void run()  {
       
        
        String iniciarConsultaEntregasRecolhasDroppoint = controller.iniciarConsultaEntregasRecolhasDroppoint();
        System.out.println(iniciarConsultaEntregasRecolhasDroppoint);

        System.out.println("Seleccione id do DropPoint: \n");
        int droppoint = utils.ReadFromKeyboard.read();
        controller.seleccionarDroppoint(droppoint);

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
                    System.out.println(controller.getListaRegistoEntregues());
                    utils.ReadFromKeyboard.pressEnter();
                    break;

                case 2:
                    System.out.println(controller.getListaRegistoRecolhidas());
                    utils.ReadFromKeyboard.pressEnter();
                    break;
                case 3:
                    System.out.println(controller.getOcupacao());
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
