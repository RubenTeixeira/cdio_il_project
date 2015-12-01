/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Token;
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
    
    public Token getByCodigo(String codigo)  {
        Token tokenObj = null;
        String qry = "select t.id_token, a.descricao, t.id_reserva from token t, tipo_token a"
                + " where t.id_tipo_token = a.id_tipo_token"
                + " and t.codigo = '" + codigo + "'";
        System.out.println("QUERY");
        System.out.println(qry);
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                tokenObj = new Token(rs.getInt("id_token"), codigo, rs.getString("descricao"), rs.getInt("id_reserva"));
            }
        } catch (SQLException e) {
        }
        return tokenObj;
    }

    @Override
    public boolean insertNew(Token obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Token obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Token obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Token get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
