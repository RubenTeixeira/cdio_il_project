/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.ConsultarOcupacaoEntregasController;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author vascopinho
 */
public class ConsultarOcupacaoEntregaUI {
    
    public static void main(String[] args) throws SQLException{
        ConsultarOcupacaoEntregasController consultarOcupacaoEntregasController = new controller.ConsultarOcupacaoEntregasController();
        List<String> iniciarConsultaEntregasRecolhasDroppoint = consultarOcupacaoEntregasController.iniciarConsultaEntregasRecolhasDroppoint();
        System.out.println(iniciarConsultaEntregasRecolhasDroppoint);
    }
    
    
}
