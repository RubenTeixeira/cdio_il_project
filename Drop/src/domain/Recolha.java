/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public class Recolha implements TransaccaoPrateleira {

    private int idRecolha;
    private int idEntrega;
    private Date dateOpen;
    private Date dateClose;
    private int idToken;
    
    public Recolha() {
    }

    public Recolha(int idRecolha, int idToken) {
        this.idRecolha = idRecolha;
        this.idToken = idToken;
    }

    /**
     *
     * @param idRecolha
     */
    @Override
    public void setId(int idRecolha) {
        this.idRecolha = idRecolha;
    }

    
    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    @Override
    public void setDateOpen() {
        this.dateOpen = new Date();
    }

    public Date getDateClose() {
        return dateClose;
    }

    @Override
    public void setDateClose() {
        this.dateClose = new Date();
    }

    @Override
    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }
    
    

    @Override
    public String getQueryPrateleira(Token token) {
        return "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA" +
                "  from prateleira p, entrega e, reserva r, TOKEN T" +
                "  where p.ocupado = 1" +
                "  and p.ID_PRATELEIRA = e.ID_PRATELEIRA" +
                "  and e.ID_RESERVA = r.ID_RESERVA" +
                "  and r.ID_RESERVA = T.ID_RESERVA" +
                "  and T.CODIGO = '"+token.getCodigo()+"' and T.ATIVO = 1";
    }

    @Override
    public String getQueryInsert() {
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
        String dtOpen = ft.format(dateOpen);
        String dtClose = ft.format(dateClose);
        return "INSERT INTO RECOLHA ("
                + "ID_RECOLHA,ID_ENTREGA,ID_TOKEN_CLIENTE,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA)"
                + " VALUES ("+this.idRecolha+","+this.idEntrega+","+this.idToken+",TO_DATE('"+dtOpen+"', 'dd-mm-yyyy HH24:MI'),TO_DATE('"+dtClose+"', 'dd-mm-yyyy HH24:MI'))";
    }

    @Override
    public String getQueryGetId() {
        return "select nvl(max(id_recolha),0)+1 as id from recolha";
    }

    @Override
    public boolean valido() {
        return idEntrega > 0 && idRecolha > 0 && idToken > 0 && dateOpen != null && dateClose != null;
    }
    
    
    
}
