/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.ActivityFilter;
import ch.bfh.lca._15h.library.model.Activity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Filter activities based on their date
 * @author Cédric Michelet
 */
public class ActivityFilterOnDate implements ActivityFilter {
    private Date startDate = null;
    private Date endDate = null;
    private SimpleDateFormat simpleDateFormat = null;
    
    /***
     * Constructor
     * @param startDateIncluded
     * @param endDateIncluded 
     */
    public ActivityFilterOnDate(Date startDateIncluded, Date endDateIncluded, String dateFormat) {
        startDate = startDateIncluded;
        endDate = endDateIncluded;
        simpleDateFormat = new SimpleDateFormat(dateFormat);
    }
    
    @Override
    public Boolean matchActivity(Activity activity) throws Exception {
        String consultDate = activity.getTimeStamp(); //yyyy:MM:dd:HH:mm:ss:SSS:

        if(consultDate == null || consultDate.equals("")) return false;
       
        Date d = simpleDateFormat.parse(consultDate);
        
        if(startDate != null) {
            if(d.before(startDate)) return false;
        }
        
        if(endDate != null) {
            if(endDate.before(d)) return false;
        }

        return true;
    }   
}
