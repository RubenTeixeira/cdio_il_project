package ui;

import dal.AddressDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.DropPoint;
import gui.DropGUI;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.domain.Point;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class Main {

    static Scanner in = new Scanner(System.in);
    private static MaintenanceUI maintenanceUI;
    public static final String CREDENTIALS_FILE = "settings/settings.txt";

    public static void main(String[] args) throws SQLException {
        //utils.ReadAndWriteFile.readFromFile("settings/settings.txt");
        SQLConnection instance = persistence.OracleDb.getInstance();
        DropPointDAO dao = (DropPointDAO) instance.getDAO(Table.DROPPOINT);
        AddressDAO adressDao = (AddressDAO) instance.getDAO(Table.ADDRESS);

        esinf.dropGraph.GraphDropPointNet gra = new esinf.dropGraph.GraphDropPointNet();

        HashMap<DropPoint, Float> m = new HashMap<>();

        List<DropPoint> listDropPoints = dao.getListDropPoints();
        DropPoint get = listDropPoints.get(0);
        DropPoint get1 = listDropPoints.get(1);
        DropPoint get2 = listDropPoints.get(2);
        Float fg = (float) 2.0;
        Float fg1 = (float) -3.0;
        Float fg12 = new Float(3.0);
        m.put(get, fg);
        m.put(get1, fg1);
        m.put(get2, fg12);

        List<DropPoint> buildPathWithPriority = gra.buildPathWithPriority(m);
        System.out.println(buildPathWithPriority);
        List<Point> buildPathShortestPath = gra.buildPathShortestPath(gra.getPoints().get(0), gra.getPoints().subList(1, 3));
        for (Point buildPathShortestPath1 : buildPathShortestPath) {
            System.out.println(gra.getDropPointByPoint(buildPathShortestPath1));
        }

        //gra.show(gra.getPoints(), gra.getPoints(), gra.getPoints());
        
        //Edge route = RequestAPI.getEdgeWithDistance(41.1796524, -8.1729746, 41.181332, -8.1731463);
        //System.out.println(route.getDistance());
        //versaoGraficaParaCliente();
        //versaoConsola();
        //colaboradorConsola();
        //colaboradorAPP();
        //gestorConsola();
    }

    public static void menu() {
        String menu = "---------MENU---------\n"
                + "1. Open Cell\n"
                + "2. Maintenance Assistant\n"
                + "0. Exit";
        System.out.println(menu);
    }

    private static void versaoConsola() throws SQLException {
        int op;

        do {
            menu();
            op = in.nextInt();
            switch (op) {
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

    private static void versaoGraficaParaCliente() {
        new DropGUI(CREDENTIALS_FILE);
    }

    private static void colaboradorConsola() throws SQLException {

        System.out.println("--------------Maintenance Assistant------------");
        String menu = "1. Start Maintenance\n"
                + "2. Pickup past Deliveries\n"
                + "3. Make Maintenance\n"
                + "4. Resume Maintenance\n"
                + "0. Go back";
        int op;
        do {
            System.out.println(menu);
            op = in.nextInt();
            switch (op) {
                case 1:
                    maintenanceUI = new MaintenanceUI(CREDENTIALS_FILE);
                    MaintenanceUI run = maintenanceUI.run();
                    if (run != null) {
                        // CHAMAR O QUE FOR Necessario;
                    }
                    break;
                case 2:
                    new MaintenancePickupUI();
                    break;
                case 3:
                    new MakeMaintenanceUI(CREDENTIALS_FILE);
                    break;
                case 4:
                    if (maintenanceUI != null) {
                        maintenanceUI.resume();
                    } else {
                        System.out.println("Nothing in resume mode!");
                    }
                case 0:
                    versaoConsola();
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

        } while (op != 0);
    }

    private static void gestorConsola() {
        System.out.println("-----------------Manager---------------");
        String menu = "1. List deliveries, pickups and occupation rate\n"
                + "2. List maintenances\n"
                + "3. List repairs\n"
                + "0. Exit";
        int op = 0;
        do {
            System.out.println(menu);
            op = in.nextInt();
            switch (op) {
                case 1:
                    try {
                        new ConsultOccupationDeliveriesUI();
                    } catch (RuntimeException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        new ListMaintenanceUI();
                    } catch (RuntimeException e) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e.getMessage());
                    }
                    break;
                case 0:
                    exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }

        } while (op != 0);
    }

    private static void colaboradorAPP() throws SQLException {
        new ColaboratorAPPGUI();
    }

}


