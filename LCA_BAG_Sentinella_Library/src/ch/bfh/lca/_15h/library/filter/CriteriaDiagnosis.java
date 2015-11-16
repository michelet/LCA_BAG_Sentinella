/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class CriteriaDiagnosis implements Criteria {

    private String targetValue;
    public CriteriaDiagnosis(String targetDiagnosis) {
        this.targetValue = targetDiagnosis;
    }

    @Override
    public DataSource meetCrieria(DataSource dataSource) {
        dataSource.resetIncerementIndex();
        
        for(DoctorPatientContact dpc : dataSource) {
            boolean containsCriteria = false;
            for(String diag : dpc.getDiagnosis()) {
                if (diag.equals(this.targetValue)) {
                    containsCriteria = true;
                }
            }
            if (!containsCriteria) {
                try {
                    dataSource.removeDoctorPatientContactFromMemory(dpc);
                } catch (Exception ex) {
                    Logger.getLogger(CriteriaDiagnosis.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return dataSource;
    }
}
