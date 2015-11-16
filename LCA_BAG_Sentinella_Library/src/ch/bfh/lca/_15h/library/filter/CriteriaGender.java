/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import ch.bfh.lca._15h.library.model.DoctorPatientContact.ESex;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class CriteriaGender implements Criteria {
    
    
    
    ESex targetSex;
    public CriteriaGender(ESex targetSex) {
        this.targetSex = targetSex;
    }

    @Override
    public DataSource meetCrieria(DataSource dataSource) {
        dataSource.resetIncerementIndex();
        for(DoctorPatientContact dpc : dataSource) {
            if (targetSex!=dpc.getPatSex()) {
                try {
                    dataSource.removeDoctorPatientContactFromMemory(dpc);
                } catch (Exception ex) {
                    Logger.getLogger(CriteriaGender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dataSource;
    }
    
    
}
