/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                for(DoctorPatientContact dpc: dataSource) {
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(dpc.getContactDate());
                       
            if (this.targetYear != cal.get(Calendar.YEAR));
            {
                try {
                    dataSource.removeDoctorPatientContactFromMemory(dpc);
                } catch (Exception ex) {
                    Logger.getLogger(CriteriaAge.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        return dataSource;
    }
    
}
