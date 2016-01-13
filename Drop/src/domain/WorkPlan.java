/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Rúben Teixeira <1140780@isep.ipp.pt>
 */
public interface WorkPlan {

    List<Plannable> calcPlanPath();
    
    @Override
    String toString();
    
}
