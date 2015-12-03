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
public class main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws SQLException
    {
        utils.ReadAndWriteFile.readFromFile("settings/settings.txt");

        versaoGraficaParaCliente();
        //versaoConsola();

    }

    public static void menu()
    {
        String menu = "---------MENU---------\n"
                + "1. Gestão DropPoint\n"
                + "2. Comprar serviço DropPoint\n"
                + "3. Abrir Prateleira\n"
                + "4. Sair";
        System.out.println(menu);
    }

    private static void versaoConsola()
    {
        int op = 0;

        do
        {
            menu();
            op = in.nextInt();
            switch (op)
            {
                case 1:
                    try
                    {
                        new ConsultarOcupacaoEntregaUI();
                    } catch (RuntimeException e)
                    {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e.getMessage());
                    }
                    break;

                case 2:
                    new ComprarServicoDPUI();
                    break;

                case 3:
                    new AbrirPrateleiraUI();
                    
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (op != 4);
    }

    private static void versaoGraficaParaCliente()
    {
        new DropGUI();
    }
}
