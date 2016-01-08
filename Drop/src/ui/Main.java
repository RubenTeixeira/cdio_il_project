package ui;

import gui.ColaboratorAPPGUI;
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
    public static final String CREDENTIALS_FILE = "settings/settings.txt";

    public static void main(String[] args) throws SQLException
    {
        //utils.ReadAndWriteFile.readFromFile("settings/settings.txt");

        //versaoGraficaParaCliente();
        versaoConsola();
        //colabroradorConsola();
        //colaboradorAPP();

    }

    public static void menu()
    {
        String menu = "---------MENU---------\n"
                + "1. Open Cell\n"
                + "2. Maintenance Assistant\n"
                + "0. Exit";
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
                    colaboradorConsola();
                    break;
                case 0:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        } while (op != 0);
    }

    private static void versaoGraficaParaCliente()
    {
        new DropGUI(CREDENTIALS_FILE);
    }

    private static void colaboradorConsola() throws SQLException
    {
        System.out.println("--------------Maintenance Assistant------------");
        String menu = "1. Start Maintenance\n"
                + "2. Pickup past Deliveries\n"
                + "3. Make Maintenance\n"
                + "0. Go back";
        int op;
        do
        {
            System.out.println(menu);
            op = in.nextInt();
            switch (op)
            {
                case 1:
                    new MaintenanceUI(CREDENTIALS_FILE);
                    break;
                case 2:
                   new MaintenancePickupUI();
                    break;
                case 3:
                    new MakeMaintenanceUI(CREDENTIALS_FILE);
                    break;
                case 0:
                    versaoConsola();
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

        } while (op != 0);
    }

    private static void gestorConsola()
    {
        System.out.println("-----------------Manager---------------");
        String menu = "1. Droppoint Management\n"
                + "0. Exit";
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
                    System.out.println("Invalid Option");
                    break;
            }

        } while (op != 0);
    }

    private static void colaboradorAPP()
    {
        new ColaboratorAPPGUI();
    }

}
