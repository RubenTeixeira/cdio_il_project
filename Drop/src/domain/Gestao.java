/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
    public String ListarPreferenciasTemperatura() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_TEMPERATURA");
        String op = "";

        try {

            while (executeQuery.next()) {

                op += "Prateleira ID: " + executeQuery.getString("ID_TEMPERATURA") + " "
                        + executeQuery.getString("DESCRICAO") + " com temperaturas entre ["
                        + executeQuery.getString("TEMP_MAX") + "|" + executeQuery.getString("TEMP_MIN") + "]\n";

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
    public String ListarPreferenciasDimensao() {
        ResultSet executeQuery = bd.executeQuery("SELECT * FROM CLASSE_DIMENSAO");
        String op = "";

        try {

            while (executeQuery.next()) {

                op += "Prateleira ID: " + executeQuery.getString("ID_TIPO_DIMENSAO") + " do tipo "
                        + executeQuery.getString("DESCRICAO") + " ,com dimensoes de altura, lasgura,comprimento respectivamente de"
                        + executeQuery.getString("ALTURA") + "x" + executeQuery.getString("LARGURA") + "x"
                        + executeQuery.getString("COMPRIMENTO") + "\n";

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
        //FICA POR REVER NEXT ID
        String m = "SELECT * FROM RESERVA ORDER BY ID_RESERVA";
        ResultSet executeQuery = bd.executeQuery(m);
        int lastId = 0;
        try {
            while (executeQuery.next()) {
                lastId = Integer.valueOf(executeQuery.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "INSERT INTO Reserva(ID_RESERVA,ID_CLIENTE,ID_DROPPOINT,ID_TEMPERATURA,ID_TIPO_DIMENSAO) VALUES (?,?,?,?,?)";
        PreparedStatement prepareStatement = bd.prepareStatement(query);

        try {
            prepareStatement.setInt(1, (lastId + 1));
            prepareStatement.setInt(2, idCliente);
            prepareStatement.setInt(3, idDropPoint);
            prepareStatement.setInt(4, idTemperatura);
            prepareStatement.setInt(5, idDimensao);

            prepareStatement.execute();

            return lastId + 1;

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
//        //FICA POR REVER NEXT ID
//        String m = "SELECT * FROM TOKEN";
//        ResultSet executeQuery = bd.executeQuery(m);
//        int lastId = 0;
//        try {
//            while (executeQuery.next()) {
//                lastId = Integer.valueOf(executeQuery.getString(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Gestao.class.getName()).log(Level.SEVERE, null, ex);
//        }
        /////////////////////////////////////
        ////////// A REVER GERACAO DE TOKENS/////////////
        
//        String select = "Insert into TOKEN(id_token,data_geracao,data_validade,id_tipo_token,ativo,id_reserva,codigo) VALUES (?,?,?,?,?,?,?)";
//       // PreparedStatement prepareStatement = bd.prepareStatement(select);
       String token = "";

        token += "O seu token é " + Calendar.getInstance().toString().hashCode();
        return token;
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
     *
     * @param query SQL Select statement
     * @return Prateleira
     */
    private Prateleira getPrateleira(String query) {
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


    


    public void closeConection() {
        bd.closeConnection();
    }

}
