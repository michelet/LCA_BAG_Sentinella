/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.DoctorPatientContact;


/**
 * Class to filter activity according to internal conditions.
 * @author Cédric Michelet
 */
public interface DoctorPatientContactFilter {
    public Boolean matchDoctorPatientContact(DoctorPatientContact doctorPatientContact) throws Exception;    
}
