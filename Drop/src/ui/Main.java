package ui;

import com.google.maps.model.LatLng;
import dal.AddressDAO;
import dal.DropPointDAO;
import dal.Table;
import domain.DropPoint;
import esinf.dropGraph.GraphDropPointNet;
import esinf.graph.Graph;
import gui.DropGUI;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import maps.domain.Branch;
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
//        SQLConnection instance = persistence.OracleDb.getInstance();
//        DropPointDAO dao = (DropPointDAO) instance.getDAO(Table.DROPPOINT);
//        AddressDAO adressDao = (AddressDAO) instance.getDAO(Table.ADDRESS);
//
//        esinf.dropGraph.GraphDropPointNet gra = new esinf.dropGraph.GraphDropPointNet();
//
//        HashMap<DropPoint, Float> m = new HashMap<>();
//
        
        
        
        
        GraphDropPointNet graphDropPointNet = new GraphDropPointNet(false);
        Point point = new Point(1, "Porto", "Porto");
        point.setCoordinate(new LatLng(41.1621376, -8.6568725));
        Point point1 = new Point(2, "Lisboa", "Lisboa");
        point1.setCoordinate(new LatLng(38.7436057, -9.2302439));
        Point point2 = new Point(3, "Minho", "Minho");
        point2.setCoordinate(new LatLng(41.5567729, -8.3991057));
        Point point3 = new Point(4, "Mirandela", "Mirandela");
        point3.setCoordinate(new LatLng(41.4780659, -7.213069));
        Point point4 = new Point(5, "Faro", "Faro");
        point4.setCoordinate(new LatLng(37.0177845, -7.9749177));
        
        graphDropPointNet.insertPoint(point);
        graphDropPointNet.insertPoint(point1);
        graphDropPointNet.insertPoint(point2);
        graphDropPointNet.insertPoint(point3);
        graphDropPointNet.insertPoint(point4);
        
        
        graphDropPointNet.addRoute(point, point1, new Branch(point, point1, 300, 6));
        graphDropPointNet.addRoute(point, point2, new Branch(point, point2, 100, 1));
        graphDropPointNet.addRoute(point, point3, new Branch(point, point3, 125, 2));
        graphDropPointNet.addRoute(point, point4, new Branch(point, point4, 460, 12));
        
        graphDropPointNet.addRoute(point1, point, new Branch(point1, point, 300, 6));
        graphDropPointNet.addRoute(point1, point2, new Branch(point1, point2, 350, 7));
        graphDropPointNet.addRoute(point1, point3, new Branch(point1, point3, 360, 7));
        graphDropPointNet.addRoute(point1, point4, new Branch(point1, point4, 215, 4));
        
        graphDropPointNet.addRoute(point2, point1, new Branch(point2, point1, 350, 6));
        graphDropPointNet.addRoute(point2, point, new Branch(point2, point, 100, 1));
        graphDropPointNet.addRoute(point2, point3, new Branch(point2, point3, 145, 2));
        graphDropPointNet.addRoute(point2, point4, new Branch(point2, point4, 550, 12));
        
        
        graphDropPointNet.addRoute(point3, point1, new Branch(point3, point1, 360, 7));
        graphDropPointNet.addRoute(point3, point, new Branch(point3, point, 125, 2));
        graphDropPointNet.addRoute(point3, point2, new Branch(point3, point2, 145, 2));
        //graphDropPointNet.addRoute(point3, point4, new Branch(point3, point4, 500, 12));
        
        graphDropPointNet.addRoute(point4, point1, new Branch(point4, point1, 215, 4));
        graphDropPointNet.addRoute(point4, point, new Branch(point4, point, 460, 12));
        graphDropPointNet.addRoute(point4, point2, new Branch(point4, point2, 550, 12));
        graphDropPointNet.addRoute(point4, point3, new Branch(point4, point3, 500, 12));
        
        List<DropPoint> list = new ArrayList<DropPoint>(); 
        DropPoint d1 = new DropPoint();
        d1.setId(1);
        d1.setName("Porto");
        
        DropPoint d2 = new DropPoint();
        d2.setId(2);
        d2.setName("Lisboa");
        
        DropPoint d3 = new DropPoint();
        d3.setId(3);
        d3.setName("Minho");
        
        DropPoint d4 = new DropPoint();
        d4.setId(4);
        d4.setName("Mirandela");
        
        DropPoint d5 = new DropPoint();
        d5.setId(5);
        d5.setName("Faro");
        
        
        
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);
        
        
        graphDropPointNet.setLstDrop(list);
        
        Map<DropPoint, Float> m = new HashMap<>();
        
        float i=3;
        
        for (DropPoint list1 : list.subList(0, 3)) {
            m.put(list1, i);
            i+=10;
        }
        
        
        List<Point> arrayList = new ArrayList<Point>();
        
        arrayList.add(point3);
        arrayList.add(point4);
        
        
        
        //List<DropPoint> pathLimitedByTime = graphDropPointNet.getPathLimitedByTime(m, 15);
        
        List<Point> pathLimitedByTime1 = graphDropPointNet.getPathLimitedByTime(point, arrayList, 20);
       
        for (Point q : pathLimitedByTime1) {
            System.out.println(q);
        }
        
        
        
        
//        List<DropPoint> listDropPoints = dao.getListDropPoints();
//        DropPoint get = listDropPoints.get(0);
//        DropPoint get1 = listDropPoints.get(1);
//        DropPoint get2 = listDropPoints.get(2);
//        Float fg = (float) 2.0;
//        Float fg1 = (float) -3.0;
//        Float fg12 = new Float(3.0);
//        m.put(get, fg);
//        m.put(get1, fg1);
//        m.put(get2, fg12);
//
//        List<DropPoint> buildPathWithPriority = gra.buildPathWithPriority(m);
//        System.out.println(buildPathWithPriority);
//        List<Point> buildPathShortestPath = gra.buildPathShortestPath(gra.getPoints().get(0), gra.getPoints().subList(1, 3));
//        for (Point buildPathShortestPath1 : buildPathShortestPath) {
//            System.out.println(gra.getDropPointByPoint(buildPathShortestPath1));
//        }

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
                case 3:
                    try {
                        new ListRepairUI();
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

}
//    private static void colaboradorAPP() throws SQLException {
//        new ColaboratorAPPGUI();
//    }
