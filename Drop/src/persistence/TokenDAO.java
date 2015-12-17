/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Token;
import domain.TokenClient;
import domain.TokenCourier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class TokenDAO extends GenericDAO<Token> {

    private final static String TABLENAME = "TOKEN";

    public TokenDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Method creates an object Token with the atributes of the token with the same code sent by parametre
     * @param code token's code which is send by parametre
     * @return Token type object
     */
    public Token getByCode(String code) {
        Token tokenObj = null;
        String qry = "select t.id_token, t.data_geracao, t.data_validade, a.descricao, t.ativo, t.codigo, t.id_reserva from token t, tipo_token a"
                + " where t.id_tipo_token = a.id_tipo_token"
                + " and t.codigo = '" + code + "'";
        System.out.println("QUERY");
        System.out.println(qry);
        ResultSet rs = executeQuery(qry);
        if (rs != null) {
            try {
                rs.next();
                tokenObj = createToken(rs.getInt("id_token"), rs.getString("data_geracao"), rs.getString("data_validade"), rs.getString("descricao"), rs.getInt("ativo"), rs.getString("codigo"), rs.getInt("id_reserva"));
            } catch (SQLException e) {
            }
        }
        return tokenObj;
    }

    /**
     * Retrieves incremental ID for this object correponding Table
     * @return int ID
     */
    public int getNextId() {
        String query = "select nvl(max(id_token),0)+1 as id from token";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public boolean insertNew(Token obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method updates a token sent by parametre in the Data Base
     * @param obj object Token
     * @return boolean variable true if the update was successfull otherwise returns false
     */
    @Override
    public boolean update(Token obj) {
        String aux[]=obj.getGenerationDate().split("\\.");
        String generationDate = aux[2]+"-"+aux[1]+"-20"+aux[0];
        String aux2[]=obj.getExpirationDate().split("\\.");
        String expirationDate = aux2[2]+"-"+aux2[1]+"-20"+aux2[0];
        String qry = "update Token set data_geracao = TO_DATE('"+generationDate+"', 'dd-mm-yyyy'),"
                + " data_validade = TO_DATE('"+expirationDate+"', 'dd-mm-yyyy'),"
                + " ativo = "+obj.getState()+", id_reserva = "+obj.getReservationId()+", codigo = '"+obj.getCode()+"'\n"
                  + "  where id_token = "+obj.getId();
        //String qry = "update Token t set id_token = "+obj.getId()+", data_geracao = "+obj.getGenerationDate()+", data_validade = "+obj.getExpirationDate()+", id_tipo_token = "+obj.getType()+", ativo = "+obj.getState()+", id_reserva = "+obj.getIdReservation()+", codigo = "+obj.getCode()+"\n"
        //        + "  where t.CODIGO = '"+obj.getCode()+"'";
        try {
            PreparedStatement stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public void delete(Token obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Token get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Private method used to instantiate one of Token class implementations. Depends on its Description
     * @param id Token id
     * @param generationDate
     * @param expirationDate
     * @param description represanted in the DB under TipoToken as description (Cliente, Estafeta (...))
     * @param state
     * @param code
     * @param idReservation
     * @return Token implementation class object (TokenClient, TokenCourier (...))
     */
    private Token createToken(int id, String generationDate, String expirationDate, String description, int state, String code, int idReservation) {
        Token token;
        
        switch (description.toLowerCase()) {
            
            case "cliente":
                token = new TokenClient(id, generationDate, expirationDate, state, code, idReservation);
                break;
            case "estafeta":
                token = new TokenCourier(id, generationDate, expirationDate, state, code, idReservation);
                break;
            default:
                return null;
        }
        return token;
    }

}
