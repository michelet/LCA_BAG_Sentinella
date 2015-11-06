/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DoctorPatientContactFilter;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Filter activities based on their date
 * @author Cédric Michelet
 */
public class DoctorPatientContactFilterOnDate implements DoctorPatientContactFilter {
    private Date startDate = null;
    private Date endDate = null;
        
    /***
     * Constructor
     * @param startDateIncluded
     * @param endDateIncluded 
     */
    public DoctorPatientContactFilterOnDate(Date startDateIncluded, Date endDateIncluded) {
        startDate = startDateIncluded;
        endDate = endDateIncluded;
    }
    
    @Override
    public Boolean matchDoctorPatientContact(DoctorPatientContact doctorPatientContact) throws Exception {
        //String consultDate = activity.getTimeStamp(); //yyyy:MM:dd:HH:mm:ss:SSS:
        Date consultDate = doctorPatientContact.getContactDate();
        
        if(consultDate == null) return false;
              
        if(startDate != null) {
            if(consultDate.before(startDate)) return false;
        }
        
        if(endDate != null) {
            if(endDate.before(consultDate)) return false;
        }

        return true;
    }   
}
