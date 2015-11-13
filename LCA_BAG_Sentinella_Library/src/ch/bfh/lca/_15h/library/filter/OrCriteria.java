/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.ArrayList;

/**
 *
 * @author Stefan
 */
public class OrCriteria implements Criteria {

        ArrayList<Criteria> criterias;
    
    public OrCriteria(ArrayList<Criteria> criterias) {
        this.criterias = new ArrayList<>();
        this.criterias = criterias;
    }
    
    public OrCriteria() {
        this.criterias = null;
        this.criterias = new ArrayList<>();
    }
    @Override
    public DataSource meetCrieria(DataSource dataSource) {
        DataSource result = this.addFirstCriteriaResultIntoList(dataSource);
        
        for(int i=1; i<criterias.size(); i++) {
            result = this.addNewItems(result, criterias.get(i).meetCrieria(dataSource));
        }
        
        return result;
    }

    public void addAndCriteria(Criteria criteria) {
        this.criterias.add(criteria);
    }
    
    public void removeAndCriteria(Criteria criteria) {
        this.criterias.remove(criteria);
    }
       
    private DataSource addFirstCriteriaResultIntoList(DataSource dataSource) {
        return this.criterias.get(0).meetCrieria(dataSource);
    }
    
    private DataSource addNewItems(DataSource dest, DataSource src) {
        
        for(DoctorPatientContact dpc : src) {
          if (dest.getIndexOfDocctorPatientContact(dpc)<0) {
              dest.addDoctorPatientContact(dpc);
          }  
        }
        return dest;
    }
}
