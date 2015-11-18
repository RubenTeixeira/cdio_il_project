package ui;

import controller.ComprarServicoDPController;
import domain.Cliente;
import domain.Morada;

public class ComprarServicoDPUI {

    private controller.ComprarServicoDPController controller;

    public ComprarServicoDPUI() {
        controller = new ComprarServicoDPController();
        run();
    }

    private void run() {
        String menu = "Selccione das seguintes opções \n"
                + "1. Cliente registado"
                + "2. Cliente por registar"
                + "3. Sair";
        int op;

        do {
            op = utils.ReadFromKeyboard.read();
            System.out.println(menu);

            switch (op) {
                case 1:
                   // do {
                        
                   // } while (login() != true);

                    break;
                case 2:
                    new RegistarClienteUI();
                    break;

                default:
                    System.out.println("Opção Inválida");
                    break;
            }

        } while (op != 3);

    }

}
