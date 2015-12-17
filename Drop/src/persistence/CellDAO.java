/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.Cabinet;
import domain.Cell;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class CellDAO extends GenericDAO<Cell> {
    
    private final static String TABLENAME = "PRATELEIRA";

    public CellDAO(Connection con) {
        super(con, TABLENAME);
    }

    public Cell findCellForDelivery(String token) {
        
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
        
        return findCell(query);
    }
    
    public Cell findCellForPickUp(String token) {
        
        String query = "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA" +
                "  from prateleira p, entrega e, reserva r, TOKEN T" +
                "  where p.ocupado = 1" +
                "  and p.ID_PRATELEIRA = e.ID_PRATELEIRA" +
                "  and e.ID_RESERVA = r.ID_RESERVA" +
                "  and r.ID_RESERVA = T.ID_RESERVA" +
                "  and T.CODIGO = '"+token+"' and T.ATIVO = 1";
        
        return findCell(query);
        
    }
    
    /**
     * Return a list of open cell's
     *
     * @param abinet
     * @return Deque Cell Objects
     * @throws SQLException
     */
    public Deque<Cell> cellsToOpen(Cabinet cabinet) throws SQLException {
        Deque<Cell> data = new LinkedList<>();
        //String idCabine = String.valueOf(idCabinet.getId());

//        String query = "begin "
//                + "CELLSTOMAINTENANCE(" + idCabine + ") "
//                + "end;/";
        
//        PreparedStatement stmnt = this.con.prepareStatement(query);
        
        String qry = "cellstomaintenance("+cabinet.getId()+")";
        CallableStatement stmnt = con.prepareCall(qry);

        ResultSet result = stmnt.executeQuery();

        while (result.next()) {
            Cell newCell = new Cell();
            newCell.setId(result.getInt(0));
            String description = "Cell number: " + String.valueOf(result.getInt(1));
            newCell.setDescription(description);
            data.push(newCell);
        }

        return data.isEmpty() ? null : data;
    }
    
    
    public boolean openCell(Cell cell){
        return true;
    }
    
    public boolean insertReport(Cell cell){
        return true;
    }
    
    public boolean closeCell(Cell cell){
        return true;
    }
    
    
    private Cell findCell(String query) {
        Cell prateleira = null;
        PreparedStatement stmnt;
        
        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();
            if (res.next()) {
                prateleira = new Cell();
                prateleira.setId(res.getInt("ID_PRATELEIRA"));
                prateleira.setDescription(res.getString("NUMERO_PRATELEIRA"));
            }
        } catch (SQLException e) {
        }
        return prateleira;
    }

        public List<Cell> getListOfCellsWithDeliveriesOutOfDateOrderByTemperature(int dropID, String tokenCode) {

        List<Cell> listCells = new ArrayList<>();

        String qry = "SELECT p.ID_PRATELEIRA, p.NUMERO_PRATELEIRA, c.TEMP_MIN"
                + " FROM armario a, prateleira p , token t, entrega e, classe_temperatura c"
                + " WHERE "+ dropID +" = a.ID_DROPPOINT"
                + " AND a.ID_ARMARIO = p.ID_ARMARIO"
                + " AND p.OCUPADO = 1"
                + " AND p.ID_PRATELEIRA = e.ID_PRATELEIRA"
                + " AND e.ID_TOKEN_COLABORADOR = t.ID_TOKEN"
                + " AND t.CODIGO = '"+ tokenCode +"'"
                + " AND c.ID_TEMPERATURA = p.ID_TEMPERATURA"
                + " ORDER BY c.TEMP_MIN";
        PreparedStatement stmnt;
        try {
            stmnt = this.con.prepareStatement(qry);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                Cell cell = new Cell();
                cell.setId(rs.getInt("ID_PRATELEIRA"));
                cell.setDescription(rs.getString("NUMERO_PRATELEIRA"));
                listCells.add(cell);
            }
        } catch (SQLException ex) {
        }
        return listCells;
    }

    @Override
    public boolean insertNew(Cell obj) {
        // Nao e necessario por enquanto
        return true;
    }

    @Override
    public boolean update(Cell obj) {
        String query = "";
        //...
        return true;
    }

    @Override
    public void delete(Cell obj) {
        //...
    }

    @Override
    public Cell get(int id) {
        // esqueleto...
        return new Cell();
    }

}
