package ui;

import gui.DropGUI;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ReadFromKeyboard;

/**
 *
 * @author vascopinho
 */
public class main {

    static List<String> file;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        file = utils.ReadAndWriteFile.readFromFile("settings/settings.txt");

        //versaoGraficaParaCliente();
        //versaoConsola();
        colabroradorConsola();
    }

    public static void menu() {
        String menu = "---------MENU---------\n"
                + "1. Abrir Prateleira\n"
                + "2. Colaborador"
                + "3. Sair";
        System.out.println(menu);
    }

    private static void versaoConsola() {
        int op = 0;

        do {
            menu();
            op = in.nextInt();
            switch (op) {
                case 1:
                    new OpenCellUI();

                    break;
                case 2:
                    colabroradorConsola();
                    break;
                case 3:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 4);
    }

    private static void versaoGraficaParaCliente() {
        new DropGUI();
    }

    private static void colabroradorConsola() {
        System.out.println("--------------------Colabrorador------------");
        String menu = "1. Colocar em manutençao\n"
                + "2. Efectuar manutenção"
                + "3. Sair";
        int op = 0;
        do {
            System.out.println(menu);
            op = ReadFromKeyboard.read();
            switch (op) {
                case 1:
                    break;
                case 2: {
                    try {
                        new MakeMaintenanceUI(file);
                    } catch (SQLException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (op != 0);
    }

    private static void gestorConsola() {
        System.out.println("--------------------Gestor------------");
        String menu = "1. Gestão DropPoint\n";
        int op = 0;
        do {
            System.out.println(menu);
            switch (op) {
                case 1:
                    try {
                        new ConsultarOcupacaoEntregaUI();
                    } catch (RuntimeException e) {
                        Logger.getLogger(main.class.getName()).log(Level.WARNING, "Something went wrong.", e.getMessage());
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (op != 0);
    }

}
