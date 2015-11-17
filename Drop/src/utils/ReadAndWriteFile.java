/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vascopinho
 */
public class ReadAndWriteFile {
    public static Scanner read;
    
    public static List<String> readFromFile(String file){
        try {
            read = new Scanner(new FileReader(file));
            while(read.hasNext()){
                String nextLine = read.nextLine();
                String[] split = nextLine.split(";");
                String user = split[0];
                String pwd = split[1];
                String url = split[2];
                String sid = split[3];
                
                List<String> settings = new ArrayList<>();
                settings.add(user);
                settings.add(pwd);
                settings.add(url);
                settings.add(sid);
                
                return settings;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadAndWriteFile.class.getName()).log(Level.SEVERE, "Ficheiro não encontrado", ex);
        }
        
      return null;
    }
    
    
}
