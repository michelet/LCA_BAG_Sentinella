/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.Iterator;


/**
 * Defines the datasource with which the Doctor-Patient-Contats will be gettet.
 * 
 * Please pay attention, that for each DataSource, such as Databse, CSV or others has to be implemented
 * a one DataSource.
 * 
 * The DataSource interface extents the iterator Interface. You have to implement an iterator
 * through all the Doctor-Patient-Contacts (IDoctorPatientContact)
 * @author Stefan
 */
public interface DataSource extends Iterator<DoctorPatientContact> {
    /**
     * Count the number of patients available in the source
     * @return Number of patients available in the source
     * @throws Exception 
     */
    int countDoctorPatientContacts() throws Exception;
    
    /**
     * Return a specific patient contact
     * @param index Position of the patient in the list
     * @return Required patient
     * @throws Exception 
     */
    public DoctorPatientContact getDoctorPatientContact(int index) throws Exception;
    
    /** 
     * Removes a specific patientc contact
     * @param index Position of the patient in the lis
     * @throws Exception 
     */
    public void removeDoctorPatientContact(int index) throws Exception;
}
