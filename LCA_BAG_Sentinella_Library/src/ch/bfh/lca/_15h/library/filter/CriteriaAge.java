/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.impl.DBDataSource;
import ch.bfh.lca._15h.library.model.Criteria;

/**
 *
 * @author Stefan
 */
public class CriteriaAge implements Criteria {

    int targetAge;
    
    public CriteriaAge(int target) {
        this.targetAge = target;
    }
    
    @Override
    public DataSource meetCrieria(DataSource dataSource) {
        dataSource.remove();
        
        return null;
    }
    
}
