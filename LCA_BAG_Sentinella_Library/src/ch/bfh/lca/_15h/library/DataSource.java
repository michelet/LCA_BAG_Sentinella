/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.Patient;

/**
 * Interface for all classes which can read data from a source.
 * @author Cédric Michelet
 */
public interface DataSource {
    /**
     * Count the number of patients available in the source
     * @return Number of patients available in the source
     * @throws Exception 
     */
    int countPatients() throws Exception;
    
    /**
     * Return a specific patient
     * @param index Position of the patient in the list
     * @return Required patient
     * @throws Exception 
     */
    public Patient getPatient(int index) throws Exception;
}
