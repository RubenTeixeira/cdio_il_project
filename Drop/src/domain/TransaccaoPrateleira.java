/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public interface TransaccaoPrateleira {
    void setDateOpen();
    void setDateClose();
    void setIdToken(int idToken);
    void setId(int id);
    boolean valido();
}
