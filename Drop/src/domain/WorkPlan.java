/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.SQLException;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public interface WorkPlan {

    void calcPlanPath() throws SQLException ;
    boolean submitNewPlanPath();
    String getElements();
    
    @Override
    String toString();

    
    
}
