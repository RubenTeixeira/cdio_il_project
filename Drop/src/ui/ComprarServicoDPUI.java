package ui;

import controller.ComprarServicoDPController;
import java.sql.SQLException;
import persistence.OracleDb;

public class ComprarServicoDPUI {

    private controller.ComprarServicoDPController controller;

    public ComprarServicoDPUI() throws SQLException{
        controller = new ComprarServicoDPController(OracleDb.getInstance());
        run();
    }

    private void run() throws SQLException{

        String menu = "---------------------Selccione das seguintes opções------------------\n"
                + "1. Cliente registado\n"
                + "2. Cliente por registar\n"
                + "3. Sair\n";
        int op;
        boolean isLogin = controller.clienteComLoginFeito();

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
                            isLogin = controller.loginCliente(user, password);

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

        if (controller.clienteComLoginFeito()) {

            System.out.println("--------------LISTA DE DROPPOINTS----------------------------");
            System.out.println(controller.listarDropPoints());

            System.out.println("Selecione ID de DropPoint:");
            controller.SelecionarDropPoint(utils.ReadFromKeyboard.read());

            System.out.println("--------------TIPOS DE PRATELEIRAS--------------------------");
            System.out.println(controller.listarPreferenciasTemp());
            System.out.println("Selecione ID do tipo de prateleira:");
            controller.SelecionarPreferenciasTemp(utils.ReadFromKeyboard.read());

            System.out.println("--------------TIPOS DE DIMENSOES----------------------------");
            System.out.println(controller.listarPreferenciasDim());
            System.out.println("Selecione ID da dimensao da prateleira:");
            controller.SelecionarPreferenciasDim(utils.ReadFromKeyboard.read());

            System.out.println("Confirma reserva s/n?");
            String confirma = utils.ReadFromKeyboard.readString();

            if (confirma.equalsIgnoreCase("s") || confirma.equalsIgnoreCase("sim")) {
                if (controller.confirmarRegisto()) {
                    System.out.println("Registo com sucesso!");
                    System.out.println(controller.tokenCliente());
                }

            }

        }

        controller.closeConection();

    }

}
