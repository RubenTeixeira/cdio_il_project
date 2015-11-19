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
    public String listarDropPoint() {
        ResultSet executeQuery = bd.executeQuery("SELECT DropPoint.idDropPoint, Morada.rua FROM DropPoint INNER JOIN Morada ON Morada.idMorada=DropPoint.idDropPoint");
        List<String> aux = new ArrayList<>();
        try {
            String str = "";
            while (executeQuery.next()) {
                str += "DropPoint ID:" + String.valueOf(executeQuery.getString("idDropPoint"))
                        + " com morada:" + executeQuery.getString("rua") + "\n";
//                aux.add(temp);

            }
            if (aux.isEmpty()) {
                aux.add("Não Existe.");
            }

            return str;

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
    public String listarEntregas(int idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = bd.executeQuery("select e.DATAENT, p.idprateleira, e.idtoken "
                + "  from entrega e, prateleira p, armario a, droppoint d"
                + " where e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint);

        try {
            String str = "";
            while (executeQuery.next()) {

                str += "Data de entrega: " + executeQuery.getString("dataent") + ". ID prateleira: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";
                aux.add(str);
            }
            if (str.isEmpty()) {
                str = "Não Existe.";
            }

            return str;
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
    public String listarRecolhidas(int idDropPoint) {
        List<String> aux = new ArrayList<>();

        ResultSet executeQuery = bd.executeQuery("select r.DATAREC, p.idprateleira, r.idtoken "
                + "  from recolha r, entrega e, prateleira p, armario a, droppoint d"
                + "  where r.identrega = e.idtoken"
                + "   and e.idprateleira = p.idprateleira"
                + "   and p.idarmario = a.idarmario"
                + "   and a.iddroppoint = d.iddroppoint"
                + "   and d.iddroppoint = " + idDropPoint);
        try {
            String str = "";
            while (executeQuery.next()) {

                str += "Data de recolha: " + executeQuery.getString("DATAREC") + ". ID prateleira: " + executeQuery.getString("idprateleira") + " Token: " + executeQuery.getString("idtoken") + "\n";

            }

            if (str.isEmpty()) {
                str = "Não Existe.";
            }

            return str;

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
                + "     INNER JOIN "
                + "     (SELECT idprateleira FROM Prateleira NATURAL JOIN Entrega WHERE idprateleira NOT IN ("
                + "     SELECT idprateleira FROM"
                + "     Entrega JOIN Recolha ON Entrega.idToken = Recolha.idEntrega)) USING (idprateleira)");
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
    
    public Prateleira procurarPrateleira(TransaccaoPrateleira trans, Token token){
        
        return getPrateleira(trans.getQueryPrateleira(token));
    }

    /**
     * Actual shell retrieval
     * @param query SQL Select statement
     * @return Prateleira
     */
    private Prateleira getPrateleira(String query) {
        System.out.println("Perguntando à db:");
        System.out.println(query);
        Prateleira prat = null;
        
        try {
            ResultSet rs = this.bd.executeQuery(query);
            if (rs.next()) {
                prat = new Prateleira();
                prat.setId(rs.getInt("ID_PRATELEIRA"));
                prat.setDesc(rs.getString("NUMERO_PRATELEIRA"));
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prat;
    }
    
    public void setDataAbertura(TransaccaoPrateleira trans) {
        trans.setDateOpen();
    }

    public void setDataFecho(TransaccaoPrateleira trans) {
        trans.setDateClose();
        fecharTransaccao(trans);
    }
    
    private boolean fecharTransaccao(TransaccaoPrateleira trans) {
        String qry = trans.getQueryGetId();
        ResultSet rs = this.bd.executeQuery(qry);
        
        try {
            if (rs.next())
                trans.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!trans.valido())
            return false;
        
        qry = trans.getQueryInsert();
        System.out.println("Going to insert with:");
        System.out.println(qry);
        ResultSet rs2 = this.bd.executeQuery(qry);
        
        return rs2 != null;
    }
 
    public Token getToken(String token) {
        Token tokenObj = null;
        String qry = "select t.id_token, a.descricao, t.id_reserva from token t, tipo_token a"
                + " where t.id_tipo_token = a.id_tipo_token"
                + " and t.codigo = '"+token+"'";
        ResultSet rs = this.bd.executeQuery(qry);
        try {
            if (rs.next())
                tokenObj = new Token(rs.getInt("id_token"), token, rs.getString("descricao"), rs.getInt("id_reserva"));
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tokenObj;
    }

    public int getIdEntregaCorrespondente(Token token) {
        String qry = "select e.id_entrega from token t, reserva r, entrega e"
                    + "     where t.id_reserva = r.id_reserva"
                    + "         and r.id_reserva = e.id_reserva"
                    + "         and t.codigo = '"+token.getCodigo()+"'";
        
        ResultSet rs = this.bd.executeQuery(qry);
        
        try {
            if (rs.next())
                return rs.getInt("id_entrega");
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }


    
}
