/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistence.SQLConnection;

/**
 *
 * @author vascopinho
 */
public class Gestao {

    private SQLConnection bd;

    /**
     * Construtor sem parâmetros. Inicializa e agrega objecto do tipo
     * SQLConnection
     */
    public Gestao() {
        this.bd = persistence.OracleDb.getInstance();
    }

    /**
     * Permite listar DropPoints
     *
     * @return Lista do tipo String dos DropPoints do Sistema
     */
    public List<String> listarDropPoint() {
        ResultSet executeQuery = bd.executeQuery("SELECT DropPoint.idDropPoint, Morada.rua FROM DropPoint INNER JOIN Morada ON Morada.idMorada=DropPoint.idDropPoint");
        List<String> aux = new ArrayList<>();
        try {

            while (executeQuery.next()) {
                String temp = "DropPoint ID:" + String.valueOf(executeQuery.getString("idDropPoint"))
                        + " com morada:" + executeQuery.getString("rua") + "\n";
                aux.add(temp);

            }
            if (aux.isEmpty()) {
                aux.add("Não Existe.");
            }

            return aux;

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Permite listar as entregas de um DropPoint
     *
     * @param idDropPoint
     * @return Lista do tipo String das entregas
     */
    public List<String> listarEntregas(int idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = bd.executeQuery("select e.DATAENT, p.idprateleira, e.idtoken "
                + "  from entrega e, prateleira p, armario a, droppoint d"
                + " where e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint);
        try {
            while (executeQuery.next()) {
                String str = "";

                str = "Data de entrega: " + executeQuery.getString("dataent") + ". ID prateleira: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";
                aux.add(str);
            }
            if (aux.isEmpty()) {
                aux.add("Não Existe.");
            }

            return aux;
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Permite listar as Recolhas de um DropPoint
     *
     * @param idDropPoint
     * @return Lista do tipo String das recolhas
     */
    public List<String> listarRecolhidas(int idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = bd.executeQuery("select r.DATAREC, p.idprateleira, r.idtoken "
                + "  from recolha r, entrega e, prateleira p, armario a, droppoint d"
                + " where r.identrega = e.idtoken"
                + "   and e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint);
        try {
            while (executeQuery.next()) {
                String str = "";

                str = "Data de entrega: " + executeQuery.getString("DATAREC") + ". ID prateleira: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";
                aux.add(str);
            }

            if (aux.isEmpty()) {
                aux.add("Não Existe.");
            }

            return aux;

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public String consultarOcupacao(int idDropPoint) {
        ResultSet executeQuery = bd.executeQuery("SELECT COUNT(idPrateleira) FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (idarmario)) USING (idDroppoint)) WHERE iddroppoint = " + idDropPoint);
        String total = "";
        try {
            while (executeQuery.next()) {

                total += executeQuery.getString("COUNT(idPrateleira)");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet executeQuery2 = bd.executeQuery("SELECT COUNT(idPrateleira) FROM (SELECT iddroppoint, idPrateleira FROM (DROPPOINT JOIN (Armario JOIN Prateleira USING (idarmario)) USING (idDroppoint)) WHERE iddroppoint =" + idDropPoint + ")"
                + "INNER JOIN "
                + "(SELECT idprateleira FROM Prateleira NATURAL JOIN Entrega WHERE idprateleira NOT IN ("
                + "      SELECT idprateleira FROM\n"
                + "        Entrega JOIN Recolha ON Entrega.idToken = Recolha.idEntrega)) USING (idprateleira)");
        try {
            while (executeQuery2.next()) {
                String ocupada = "";

                ocupada += executeQuery2.getString("COUNT(idPrateleira)");
                return "O DropPoint com id " + idDropPoint + " tem " + ocupada + " prateleiras ocupadas, de um total de " + total + ".";

            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
