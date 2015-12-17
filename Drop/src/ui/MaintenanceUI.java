package ui;

import controller.MaintenanceDPController;
import domain.Cabinet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author 1130874
 */
public class MaintenanceUI {

    private MaintenanceDPController controller;
    private int localDropID = 1;
    static Scanner in = new Scanner(System.in);

    public MaintenanceUI() throws SQLException
    {
        this.controller = new MaintenanceDPController("settings/settings.txt");
        run();
    }

    private void run() throws SQLException
    {
        int num = 1;
        System.out.println("-------------Armarios-----------\n");
        List<Cabinet> lstOfCabinets = controller.getListOfCabinetsNotInMaintenance(localDropID);
        if (!lstOfCabinets.isEmpty())
        {
            for (Cabinet cabinet : lstOfCabinets)
            {
                System.out.println(num + ". " + cabinet.getName());
                num++;
            }
            System.out.println("0. Voltar");
            System.out.println("Selecione Armario:");
            int op = in.nextInt();
            if (op != 0)
            {
                Cabinet cabinet = lstOfCabinets.get(op - 1);
                controller.selectCabinet(cabinet);
                if (controller.putInMaintenance())
                {
                    new MakeMaintenanceUI(cabinet);
                } else
                {
                    System.out.println("Erro ao Realizar operação\nTente Novamente mais tarde!");
                    System.out.println(" ");
                }
            }
        } else
        {
            System.out.println("Armarios ja em Manutenção...\nPor favor tente mais tarde...");
            System.out.println(" ");
        }
    }
}

