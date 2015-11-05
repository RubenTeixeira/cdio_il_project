/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vascopinho
 */
public class main {
    
    static Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) throws SQLException{
        List<String> readFromFile = utils.ReadAndWriteFile.readFromFile("settings.txt");
        System.out.println(readFromFile);
        int op = 0;
       
        do{
            menu();
            op = in.nextInt();
            switch(op){
                case 1:
                    try {
                        new ConsultarOcupacaoEntregaUI();
                    } catch (RuntimeException e) {
                            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, e);
                    }
                    break;
                
                case 2:
                    exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }while(op != 2);
        
    }
    
    public static void menu(){
        String menu="---------MENU---------\n"
                + "1. Gestão DropPoint\n"
                + "2. Sair";
        System.out.println(menu);
    }
}
