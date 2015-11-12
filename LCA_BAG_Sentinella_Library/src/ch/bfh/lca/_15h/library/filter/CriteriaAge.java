/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.impl.DBDataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class CriteriaAge implements Criteria {

    int targetAge;
    final Date today = new Date();
    
    public CriteriaAge(int target) {
        
        this.targetAge = target;
    }
    
    @Override
    public DataSource meetCrieria(DataSource dataSource) {
        
        for(DoctorPatientContact dpc: dataSource) {
            
            long timeDiff = today.getTime() - dpc.getPatBirthdate().getTime();
            long ageInDays = TimeUnit.MICROSECONDS.toDays(timeDiff);
            double ageReal = (ageInDays / 365);
            long age = round(ageReal);
            
            if (this.targetAge != age) {
                try {
                    dataSource.removeDoctorPatientContactFromMemory(dpc);
                } catch (Exception ex) {
                    Logger.getLogger(CriteriaAge.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        
        return dataSource;
    }
    private long round(double d) {
        double dAbs = Math.abs(d);
        
        long l = (long) dAbs;
        double result = dAbs - (double) l;
        
        if (result < 0.5) {
            return l;            
        } else {
            return l+1;
        }
    }
    
}
