/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.impl.GenericDataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Stefan
 */
public class CriteriaConsultYear implements Criteria{

    int targetYear;
    final Date today = new Date();
    
    public CriteriaConsultYear(int target) {
        
        this.targetYear = target;
    }    
    
    @Override
    public DataSource meetCrieria(DataSource dataSource) {

        GenericDataSource newDataSource = new GenericDataSource();
        dataSource.resetIncerementIndex();
        
        for(DoctorPatientContact dpc: dataSource) {
            if (dpc != null) {
              Calendar cal = Calendar.getInstance();
              cal.setTime(dpc.getContactDate());
            
              int activeYear = cal.get(Calendar.YEAR);
              String strTargetYear = Integer.toString(this.targetYear);
              String compareYear = Integer.toString(activeYear);
              
              if (this.targetYear!=activeYear)
              {
              } else 
              {
                  newDataSource.addDoctorPatientContact(dpc);
                }
            }
            
        }
        return newDataSource;
    }
    
}
