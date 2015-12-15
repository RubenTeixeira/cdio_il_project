/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
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
    
    public Gestao(SQLConnection con) {
        this.setBd(con);
    }

    public SQLConnection getBd() {
        return bd;
    }

    public void setBd(SQLConnection bd) {
        this.bd = bd;
    }

    /**
     * Permite listar DropPoints
     *
     * @return Lista do tipo String dos DropPoints do Sistema
     */
    public String listarDropPoint() {
        ResultSet executeQuery = bd.executeQuery("SELECT DropPoint.id_DropPoint, Morada.rua FROM DropPoint INNER JOIN Morada ON Morada.idmorada=DropPoint.id_DropPoint");
        List<String> aux = new ArrayList<>();
        try {
            String str = "";
            while (executeQuery.next()) {
                str += "DropPoint ID:" + String.valueOf(executeQuery.getString("id_DropPoint"))
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
     * Devolve lista do tipo de prateleiras refrigeradas.
     *
     * @return lista
     */
    public List<String> ListarPreferenciasTemperatura() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_TEMPERATURA");
        List<String> op = new ArrayList<>();

        try {

            while (executeQuery.next()) {

                op.add("Prateleira ID: " + executeQuery.getString("ID_TEMPERATURA") + " "
                        + executeQuery.getString("DESCRICAO") + "["
                        + executeQuery.getString("TEMP_MAX") + "|" + executeQuery.getString("TEMP_MIN"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Não foi possivel buscar os tipo de prateleira", ex);
        }

        return op;
    }

    /**
     * Devolve lista do tipo de dimensoes disponiveis.
     *
     * @return String
     */
    public List<String> ListarPreferenciasDimensao() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_DIMENSAO");
        List<String> op = new ArrayList<>();

        try {

            while (executeQuery.next()) {

                op.add("Prateleira ID: " + executeQuery.getString("ID_TIPO_DIMENSAO") + " do tipo "
                        + executeQuery.getString("DESCRICAO") 
                        + executeQuery.getString("ALTURA") + "x" + executeQuery.getString("LARGURA") + "x"
                        + executeQuery.getString("COMPRIMENTO"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Não foi possivel buscar as dimensões.", ex);
        }

        return op;
    }

    /**
     * Dados os parametros, este adiciona a reserva do cliente.
     *
     * @param idCliente
     * @param idDropPoint
     * @param idTemperatura
     * @param idDimensao
     * @return int
     */
    public int reservaPrateleira(int idCliente, int idDropPoint, int idTemperatura, int idDimensao) {
        String m = "select nvl(max(ID_RESERVA),0)+1 FROM RESERVA";
        ResultSet executeQuery = bd.executeQuery(m);
        int lastId = 0;
        
        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "INSERT INTO Reserva(ID_RESERVA,ID_DROPPOINT,ID_CLIENTE,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (?,?,?,?,?)";
        PreparedStatement prepareStatement = bd.prepareStatement(query);

        try {
            
            prepareStatement.setInt(1, lastId);
            prepareStatement.setInt(2, idDropPoint);
            prepareStatement.setInt(3, idCliente);
            prepareStatement.setInt(4, idTemperatura);
            prepareStatement.setInt(5, idDimensao);

            prepareStatement.execute();

            return lastId;

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Não foi possivel fazer o registo da reserva", ex);
        }
        return 0;
    }

    /**
     * Dado o id da reserva, este procura seu token ao qual e corresponde
     * devolvendo codigo.
     *
     * @param idReserva
     * @return String
     */
    public String tokemReferentReservaId(int idReserva) {
        String m = "select nvl(max(id_token),0)+1 FROM TOKEN";
        ResultSet executeQuery = bd.executeQuery(m);
        int lastId = 0;
        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String select = "Insert into TOKEN(id_token,data_geracao,data_validade,id_tipo_token,ativo,id_reserva,codigo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement prepareStatement = bd.prepareStatement(select);

        try {
            
            Calendar instance = Calendar.getInstance();
            
            Date now = new Date(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_WEEK));
            Date validade = new Date(instance.get(Calendar.YEAR), (instance.get(Calendar.MONTH)+1), instance.get(Calendar.DAY_OF_WEEK));
            String token = UUID.randomUUID().toString().substring(0, 8);
            prepareStatement.setInt(1, lastId);
            prepareStatement.setDate(2, now);
            prepareStatement.setDate(3, validade);
            prepareStatement.setInt(4, 1);
            prepareStatement.setInt(5, 1);
            prepareStatement.setInt(6, idReserva);
            prepareStatement.setString(7, token);

            prepareStatement.execute();

            String token1=UUID.randomUUID().toString().substring(0, 8);
            prepareStatement.setInt(1,lastId+1);
            prepareStatement.setInt(4, 2);
            prepareStatement.setString(7, token1);

            prepareStatement.execute();
            
            return token;

        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, "Não foi possivel efectuar a reserva.", ex);
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


    public void closeConection() {
        bd.closeConnection();
    }

}
