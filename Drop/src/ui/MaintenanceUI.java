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
    private MakeMaintenanceUI makeMaintenanceUI;
    List<Cabinet> lstOfCabinets;

    private int localDropID = 1;
    static String FILE;
    static Scanner in = new Scanner(System.in);

    public MaintenanceUI(String file) throws SQLException {
        FILE = file;
        this.controller = new MaintenanceDPController(file);

    }

    public void displayCabinets() {
        int num = 1;
        System.out.println("-------------Cabinets-----------\n");
        List<Cabinet> lstOfCabinets = controller.getListOfCabinetsNotInMaintenance(localDropID);
        if (!lstOfCabinets.isEmpty()) {
            for (Cabinet cabinet : lstOfCabinets) {
                System.out.println(num + ". " + cabinet.getName());
                num++;
            }
        }
    }

    public MaintenanceUI run() throws SQLException {

        int cabinetOp;

        displayCabinets();

        if (lstOfCabinets.isEmpty()) {
            return null;
        }

        int menu = 0;
        do {
            System.out.println("1 - Make maintenance\n2 - Suspend maintenance\n3 - Back");
            menu = in.nextInt();

            switch (menu) {
                case 1:
                    cabinetOp = in.nextInt();
                    Cabinet cabinet = lstOfCabinets.get(cabinetOp - 1);
                    controller.selectCabinet(cabinet);
                    if (controller.putInMaintenance()) {
                        makeMaintenanceUI = new MakeMaintenanceUI(FILE);
                        int run = makeMaintenanceUI.run();
                        if (run == -1) {
                            menu = 2;
                        } else {
                            controller.stopMaintenance();
                            displayCabinets();
                            if (lstOfCabinets.isEmpty()) {
                                return null;
                            }
                            break;
                        }
                    } else {
                        System.out.println("The operation failed\nTry again later!");
                        break;
                    }
                case 2:
                    controller.putInSuspendMode();
                    return this;
                case 3:
                    return null;
                default:
                    System.out.println("Option not aviable!");

            }
        } while (menu != 0);

        return null;
    }

    public void resume() throws SQLException {
        controller.putInMaintenance();
        makeMaintenanceUI.resume();
    }

}
