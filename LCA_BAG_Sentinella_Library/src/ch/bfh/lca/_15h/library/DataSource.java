/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.Patient;

/**
 *
 * @author micheletc
 */
public interface DataSource {
    int countPatients();
    public Patient getPatient(int index) throws Exception;
}
