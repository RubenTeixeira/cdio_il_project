/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dal.GenericDAO;
import domain.Cabinet;
import domain.Cell;
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
    public static final int OPEN = 0;
    public static final int CLOSE = 1;

    public CellDAO(Connection con) {
        super(con, TABLENAME);
    }

    /**
     * Finds an available Cell for a new Delivery
     *
     * @param token Token associated with a given Reservation
     * @return available Cell
     */
    public Cell findCellForDelivery(String token) {

        String query = "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA"
                + "  from prateleira p, reserva r, armario a"
                + "  where p.ocupado = 0"
                + "    and p.id_temperatura = r.ID_TEMPERATURA and p.id_tipo_dimensao = r.ID_TIPO_DIMENSAO"
                + "    and p.id_armario = a.id_armario"
                + "    and a.id_droppoint = 1 and a.id_droppoint = r.id_droppoint"
                + "    and exists(select *"
                + "                 from reserva r, token t"
                + "                where r.id_temperatura = p.id_temperatura and r.id_tipo_dimensao = p.id_tipo_dimensao"
                + "                  and r.id_reserva = t.id_reserva"
                + "                  and t.codigo = '" + token + "' and t.ATIVO = 1)"
                + "    and ROWNUM = 1";

        return findCell(query);
    }

    /**
     * Finds the Cell containing the corresponding package for the PickUp
     *
     * @param token Token associated with a given Reservation already delivered
     * @return occupied Cell
     */
    public Cell findCellForPickUp(String token) {

        String query = "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA"
                + "  from prateleira p, entrega e, reserva r, TOKEN T"
                + "  where p.ocupado = 1"
                + "  and p.ID_PRATELEIRA = e.ID_PRATELEIRA"
                + "  and e.ID_RESERVA = r.ID_RESERVA"
                + "  and r.ID_RESERVA = T.ID_RESERVA"
                + "  and T.CODIGO = '" + token + "' and T.ATIVO = 1";

        return findCell(query);

    }

    /**
     * Returns a list of open cell's
     *
     * @param cabinet
     * @return Deque Cell Objects
     * @throws SQLException
     */
    public Deque<Cell> cellsToOpen(Cabinet cabinet) throws SQLException {
        Deque<Cell> data = new LinkedList<>();

        String query = "select p.ID_PRATELEIRA, d.ID_DROPPOINT, p.NUMERO_PRATELEIRA, a.ID_ARMARIO from PRATELEIRA p "
                + "INNER JOIN ARMARIO a ON "
                + "p.ID_ARMARIO= a.ID_ARMARIO "
                + "INNER JOIN DROPPOINT d ON "
                + "a.ID_DROPPOINT=d.ID_DROPPOINT "
                + "WHERE a.MANUTENCAO = ? AND a.ID_ARMARIO= ? "
                + "AND p.OCUPADO= ? AND p.IS_OPERATIONAL = ? AND p.ATIVO=?";

        PreparedStatement stmnt = this.con.prepareStatement(query);

        stmnt.setInt(1, 1);
        stmnt.setInt(2, cabinet.getId());
        stmnt.setInt(3, 0);
        stmnt.setInt(4, 1);
        stmnt.setInt(5, 1);

        ResultSet result = stmnt.executeQuery();

        while (result.next()) {
            Cell newCell = new Cell();
            newCell.setId(result.getInt(1));
            String description = "Cell number: " + result.getString(3);
            newCell.setDescription(description);
            data.push(newCell);
        }
        return data.isEmpty() ? null : data;
    }

    public boolean updateCell(Cell cell, int state) throws SQLException {
        String query = "UPDATE PRATELEIRA "
                + "SET ATIVO=? ,"
                + "is_operational =? "
                + "WHERE ID_PRATELEIRA=? ";

        PreparedStatement stmnt = this.con.prepareStatement(query);

        stmnt.setInt(1, state);
        stmnt.setInt(2, cell.isOperational());
        stmnt.setInt(3, cell.getId());
        stmnt.executeUpdate();
        return true;
    }

    public boolean insertReport(Cell cell) {
        return true;
    }

    private Cell findCell(String query) {
        Cell prateleira = null;
        PreparedStatement stmnt;

        try {
            stmnt = this.con.prepareStatement(query);
            ResultSet res = stmnt.executeQuery();
            if(res.next()) {
                prateleira = new Cell();
                prateleira.setId(res.getInt("ID_PRATELEIRA"));
                prateleira.setDescription(res.getString("NUMERO_PRATELEIRA"));
            }
        } catch (SQLException e) {
        }
        return prateleira;
    }

    /**
     * Returns a String with the info about all cells of an specified droppoint
     *
     * @param dropID the droppoint id
     * @return the cells info
     * @throws SQLException
     */
    public String getCellsByDropPointID(int dropID) throws SQLException {

        ResultSet rs = executeQuery("select d.DESCRICAO as DIM_DESC, d.ALTURA, d.LARGURA, d.COMPRIMENTO, t.DESCRICAO as TMP_DESC, t.TEMP_MAX, t.TEMP_MIN, COUNT(*) as PRATELEIRAS_DISPONIVEIS \n"
                + "                from armario a, prateleira p, classe_temperatura t, classe_dimensao d\n"
                + "                where a.ID_DROPPOINT = " + dropID + " \n"
                + "                and a.id_armario = p.id_armario\n"
                + "                and p.OCUPADO = 0\n"
                + "                and p.ATIVO = 1\n"
                + "                and p.ID_TEMPERATURA = t.ID_TEMPERATURA\n"
                + "                and p.ID_TIPO_DIMENSAO = d.ID_TIPO_DIMENSAO \n"
                + "                group by d.ALTURA, d.DESCRICAO, t.DESCRICAO, t.TEMP_MAX, t.TEMP_MIN, d.LARGURA, d.COMPRIMENTO\n"
                + "                order by d.COMPRIMENTO");
        String info = "Prateleiras:\n";

        while (rs.next()) {
            info += String.format("\nDimensão: %s\n"
                    + "CxLxA: %sx%sx%s\n"
                    + "Temperatura: %s\n"
                    + "TMin: %s\n"
                    + "TMax: %s\n"
                    + "Disponiveis: %d\n",
                    rs.getString("DIM_DESC"),
                    rs.getString("COMPRIMENTO"),
                    rs.getString("LARGURA"),
                    rs.getString("ALTURA"),
                    rs.getString("TMP_DESC"),
                    rs.getString("TEMP_MIN"),
                    rs.getString("TEMP_MAX"),
                    rs.getInt("PRATELEIRAS_DISPONIVEIS"));
        }
        return info;
    }

    public List<Cell> getListOfCellsWithDeliveriesOutOfDateOrderByTemperature(int dropID, String tokenCode) {

        List<Cell> listCells = new ArrayList<>();

        String qry = "SELECT p.ID_PRATELEIRA, p.NUMERO_PRATELEIRA, c.TEMP_MIN"
                + " FROM armario a, prateleira p , token t, entrega e, classe_temperatura c"
                + " WHERE " + dropID + " = a.ID_DROPPOINT"
                + " AND a.ID_ARMARIO = p.ID_ARMARIO"
                + " AND p.OCUPADO = 1"
                + " AND p.ID_PRATELEIRA = e.ID_PRATELEIRA"
                + " AND e.ID_TOKEN_COLABORADOR = t.ID_TOKEN"
                + " AND t.CODIGO = '" + tokenCode + "'"
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
