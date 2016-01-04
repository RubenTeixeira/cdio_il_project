package ui;

import controller.BuyDropPointServiceController;
import java.sql.SQLException;
import persistence.OracleDb;

public class BuyDropPointServiceUI {

    private controller.BuyDropPointServiceController controller;

    public BuyDropPointServiceUI() throws SQLException {
        controller = new BuyDropPointServiceController(Main.CREDENTIALS_FILE);
        run();
    }

    private void run() throws SQLException {

        String menu = "---------------------Selccione das seguintes opções------------------\n"
                + "1. Cliente registado\n"
                + "2. Cliente por registar\n"
                + "3. Sair\n";
        int op;
        boolean isLogin = controller.clientWithLoginMade();

        if (!isLogin) {
            do {
                System.out.println(menu);
                System.out.print(":");
                op = utils.ReadFromKeyboard.read();

                switch (op) {
                    case 1:

                        do {

                            System.out.println("\nInsira Username:");
                            String user = utils.ReadFromKeyboard.readString();
                            System.out.println("Insira Password:");
                            String password = utils.ReadFromKeyboard.readString();
                            isLogin = controller.clientLogin(user, password);

                        } while (isLogin != true);

                        break;
                    case 2:
                        new ClientRegisterUI();
                        break;

                    default:
                        System.out.println("Opção Inválida");
                        break;
                }

            } while (op != 3 && isLogin != true);
        }

        if (controller.clientWithLoginMade()) {

            System.out.println("--------------LISTA DE DROPPOINTS----------------------------");
            System.out.println(controller.listDropPoints());

            System.out.println("Selecione ID de DropPoint:");
            controller.selectDropPoint(utils.ReadFromKeyboard.read());

            System.out.println("--------------TIPOS DE PRATELEIRAS--------------------------");
            System.out.println(controller.preferredTemperatureList());
            System.out.println("Selecione ID do tipo de prateleira:");
            controller.selectPreferredTemperature(utils.ReadFromKeyboard.read());

            System.out.println("--------------TIPOS DE DIMENSOES----------------------------");
            System.out.println(controller.preferredDimensionsList());
            System.out.println("Selecione ID da dimensao da prateleira:");
            controller.selectPreferredDimensions(utils.ReadFromKeyboard.read());

            System.out.println("Confirma reserva s/n?");
            String confirma = utils.ReadFromKeyboard.readString();

            if (confirma.equalsIgnoreCase("s") || confirma.equalsIgnoreCase("sim")) {
                if (controller.confirmRegister()) {
                    System.out.println("Registo com sucesso!");
                    System.out.println(controller.tokenClient());
                }

            }

        }

    }

}
