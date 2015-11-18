/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author vascopinho
 */
public class ReadFromKeyboard {

    public static Scanner in = new Scanner(System.in);

    public static int read() {
        return in.nextInt();
    }

    public static String readString() {
        return in.next();
    }

    public static void pressEnter() {
        System.out.println("Carregue ENTER para continuar");
        in.nextLine();
        in.nextLine();
    }

}
