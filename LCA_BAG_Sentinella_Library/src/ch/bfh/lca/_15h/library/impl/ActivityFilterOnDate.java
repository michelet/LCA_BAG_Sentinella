/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.ActivityFilter;
import ch.bfh.lca._15h.library.model.Activity;
import java.util.Date;

/**
 * Filter activities based on their date
 * @author Cédric Michelet
 */
public class ActivityFilterOnDate implements ActivityFilter {
    private Date startDate = null;
    private Date endDate = null;
    
    public ActivityFilterOnDate(Date startDateIncluded, Date endDateIncluded) {
        startDate = startDateIncluded;
        endDate = endDateIncluded;
    }
    
    @Override
    public Boolean matchActivity(Activity activity) {
        //@TODO filter on date
        return true;
    }   
}
