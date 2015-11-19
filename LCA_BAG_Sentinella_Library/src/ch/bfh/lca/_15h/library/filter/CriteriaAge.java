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
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.Locale;


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
        
        GenericDataSource newDataSource = new GenericDataSource();
        dataSource.resetIncerementIndex();
        
        for(DoctorPatientContact dpc: dataSource) {
            
            Calendar calToday = this.getCalendar(this.today);
            Calendar birthdate = this.getCalendar(dpc.getPatBirthdate());
            
            int diff = calToday.get(YEAR) - birthdate.get(YEAR);
            if (diff != this.targetAge) {
            } else {
                newDataSource.addDoctorPatientContact(dpc);
            }
        }
        
        return newDataSource;
    }
    
    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.setTime(date);
    return cal;
    }
    
}
