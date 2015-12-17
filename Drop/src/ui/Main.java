package ui;

import gui.DropGUI;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vascopinho
 */
public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException
    {
        utils.ReadAndWriteFile.readFromFile("settings/settings.txt");

        //versaoGraficaParaCliente();
        versaoConsola();
        //colabroradorConsola();

    }

    public static void menu()
    {
        String menu = "---------MENU---------\n"
                + "1. Abrir Prateleira\n"
                + "2. Colaborador\n"
                + "0. Sair";
        System.out.println(menu);
    }

    private static void versaoConsola() throws SQLException
    {
        int op;

        do
        {
            menu();
            op = in.nextInt();
            switch (op)
            {
                case 1:
                    new OpenCellUI();

                    break;
                case 2:
                    colabroradorConsola();
                    break;
                case 0:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 0);
    }

    private static void versaoGraficaParaCliente()
    {
        new DropGUI();
    }

    private static void colabroradorConsola() throws SQLException
    {
        System.out.println("--------------------Colabrorador------------");
        String menu = "1. Iniciar Manutenção\n"
                + "2. Recolher Entregas Expiradas\n"
                + "3. Efectuar Manutenção\n"
                + "0. Voltar";
        int op;
        do
        {
            System.out.println(menu);
            op = in.nextInt();
            switch (op)
            {
                case 1:
                    new MaintenanceUI();
                    break;
                case 2:
                   new MaintenancePickupUI();
                    break;
                case 3:
                    new MakeMaintenanceUI(utils.ReadAndWriteFile.readFromFile("settings/settings.txt"));
                    break;
                case 0:
                    versaoConsola();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (op != 0);
    }

    private static void gestorConsola()
    {
        System.out.println("--------------------Gestor------------");
        String menu = "1. Gestão DropPoint\n"
                + "0. Sair";
        int op = 0;
        do
        {
            System.out.println(menu);
            switch (op)
            {
                case 1:
                    try
                    {
                        new ConsultarOcupacaoEntregaUI();
                    } catch (RuntimeException e)
                    {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e.getMessage());
                    }
                    break;
                case 2:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (op != 0);
    }

}
