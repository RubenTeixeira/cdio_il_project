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

        String menu = "---------------------Selccione das seguintes opções------------------\n"
                + "1. Cliente registado\n"
                + "2. Cliente por registar\n"
                + "3. Sair\n";
        int op;
        boolean isLogin = controller.clienteComLoginFeito();

        if (!isLogin) {
            do {
                System.out.println(menu);
                op = utils.ReadFromKeyboard.read();

                switch (op) {
                    case 1:

                        do {

                            System.out.println("Insira Username:");
                            String user = utils.ReadFromKeyboard.readString();
                            System.out.println("Insira Password:");
                            String password = utils.ReadFromKeyboard.readString();
                            isLogin = controller.loginCliente(user, password);

                        } while (isLogin != true);

                        break;
                    case 2:
                        new RegistarClienteUI();
                        break;

                    default:
                        System.out.println("Opção Inválida");
                        break;
                }

            } while (op != 3 && isLogin != true);
        }

    }

}
