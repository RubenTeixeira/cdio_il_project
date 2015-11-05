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
       
        
        List<String> iniciarConsultaEntregasRecolhasDroppoint = controller.iniciarConsultaEntregasRecolhasDroppoint();
        System.out.println(iniciarConsultaEntregasRecolhasDroppoint);

        System.out.println("Seleccione id do DropPoint: \n");
        controller.seleccionarDroppoint(utils.ReadFromKeyboard.read());

        int op = 0;

        do {
            System.out.println("Seleccione uma das opções: \n"
                    + "1. Consultar Entregas\n"
                    + "2. Consultas Recolhas\n"
                    + "3. Consultar Ocupação\n"
                    + "4. Sair\n");
            op = utils.ReadFromKeyboard.read();
            switch (op) {
                case 1:
                    controller.getListaRegistoEntregues();
                    break;

                case 2:
                    controller.getListaRegistoRecolhidas();
                    break;
                case 3:
                    controller.getOcupacao();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 4);
    }

}
