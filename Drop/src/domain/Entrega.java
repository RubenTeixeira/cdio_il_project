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
public class Entrega implements TransaccaoPrateleira {
    
    private int idEntrega;
    private int idPrat;
    private Date dateOpen;
    private Date dateClose;
    private int idReservation;
    private int idToken;

    public Entrega(int idPrat, int idReservation, int idToken) {
        this.idPrat = idPrat;
        this.idReservation = idReservation;
        this.idToken = idToken;
    }

    public Entrega() {
    }

    /*Getter methods*/
    public int getIdPrat() {return idPrat;}
    public Date getDateOpen() {return dateOpen;}
    public Date getDateClose() {return dateClose;}
    public int getIdReservation() {return idReservation;}
    public int getId_entrega() {return idEntrega;}
    

    /*Setter methods*/
    
    @Override
    public void setId(int idEntrega) {this.idEntrega = idEntrega;}

    public void setIdPrat(int idPrat) {
        this.idPrat = idPrat;
    }
    @Override
    public void setDateOpen() {this.dateOpen = new Date();}
    @Override
    public void setDateClose() {this.dateClose = new Date();}
    public void setIdReservation(int idReservation) {this.idReservation = idReservation;}
    public void setId_entrega(int id_entrega) {this.idEntrega = id_entrega;}

    @Override
    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }
    
    

    @Override
    public String getQueryPrateleira(Token token) {
        return "select p.NUMERO_PRATELEIRA,p.ID_PRATELEIRA" +
                "  from prateleira p, reserva r, armario a" +
                "  where p.ocupado = 0" +
                "    and p.id_temperatura = r.ID_TEMPERATURA and p.id_tipo_dimensao = r.ID_TIPO_DIMENSAO" +
                "    and p.id_armario = a.id_armario" +
                "    and a.id_droppoint = 1 and a.id_droppoint = r.id_droppoint" +
                "    and exists(select *" +
                "                 from reserva r, token t" +
                "                where r.id_temperatura = p.id_temperatura and r.id_tipo_dimensao = p.id_tipo_dimensao" +
                "                  and r.id_reserva = t.id_reserva" +
                "                  and t.codigo = '"+token.getCodigo()+"' and t.ATIVO = 1)" +
                "    and ROWNUM = 1";
    }

    @Override
    public String getQueryInsert() {
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy HH:mm");
        String dtOpen = ft.format(dateOpen);
        String dtClose = ft.format(dateClose);
        return "INSERT INTO ENTREGA ("
                + "ID_ENTREGA,ID_PRATELEIRA,ID_RESERVA,ID_TOKEN_ESTAFETA,DATA_ABRE_PRATELEIRA,DATA_FECHA_PRATELEIRA) " +
"  VALUES ("+this.idEntrega+","+this.idPrat+","+this.idReservation+","+this.idToken+",TO_DATE('"+dtOpen+"', 'dd-mm-yyyy HH24:MI'),TO_DATE('"+dtClose+"', 'dd-mm-yyyy HH24:MI'))";
    }

    @Override
    public String getQueryGetId() {
        return "select nvl(max(id_entrega),0)+1 as id from entrega";
    }

    @Override
    public boolean valido() {
        return idEntrega > 0 && idPrat > 0 && idReservation > 0 && idToken > 0 && dateOpen != null && dateClose != null;
    }
    
}
