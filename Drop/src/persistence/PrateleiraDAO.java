/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Prateleira;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class PrateleiraDAO extends GenericDAO<Prateleira> {
    
    private final static String TABLENAME = "PRATELEIRA";

    public PrateleiraDAO(Connection con) {
        super(con, TABLENAME);
    }

    public Prateleira procurarPrateleiraEntrega(String token) {
        
        String query = "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA" +
                "  from prateleira p, reserva r, armario a" +
                "  where p.ocupado = 0" +
                "    and p.id_temperatura = r.ID_TEMPERATURA and p.id_tipo_dimensao = r.ID_TIPO_DIMENSAO" +
                "    and p.id_armario = a.id_armario" +
                "    and a.id_droppoint = 1 and a.id_droppoint = r.id_droppoint" +
                "    and exists(select *" +
                "                 from reserva r, token t" +
                "                where r.id_temperatura = p.id_temperatura and r.id_tipo_dimensao = p.id_tipo_dimensao" +
                "                  and r.id_reserva = t.id_reserva" +
                "                  and t.codigo = '"+token+"' and t.ATIVO = 1)" +
                "    and ROWNUM = 1";
        
        return procurarPrateleira(query);
    }
    
    public Prateleira procurarPrateleiraRecolha(String token) {
        
        String query = "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA" +
                "  from prateleira p, entrega e, reserva r, TOKEN T" +
                "  where p.ocupado = 1" +
                "  and p.ID_PRATELEIRA = e.ID_PRATELEIRA" +
                "  and e.ID_RESERVA = r.ID_RESERVA" +
                "  and r.ID_RESERVA = T.ID_RESERVA" +
                "  and T.CODIGO = '"+token+"' and T.ATIVO = 1";
        
        return procurarPrateleira(query);
        
    }
    
    private Prateleira procurarPrateleira(String query) {
        Prateleira prateleira = null;
        PreparedStatement stmnt;
        
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();
            if (res.next()) {
                prateleira = new Prateleira();
                prateleira.setId(res.getInt("ID_PRATELEIRA"));
                prateleira.setDesc(res.getString("NUMERO_PRATELEIRA"));
            }
        } catch (SQLException e) {
        }
        return prateleira;
    }


    @Override
    public boolean insertNew(Prateleira obj) {
        // Nao e necessario por enquanto
        return true;
    }

    @Override
    public boolean update(Prateleira obj) {
        String query = "";
        //...
        return true;
    }

    @Override
    public void delete(Prateleira obj) {
        //...
    }

    @Override
    public Prateleira get(int id) {
        // esqueleto...
        return new Prateleira();
    }

}
