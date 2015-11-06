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
public interface IDataSource extends Iterator<DoctorPatientContact> {
    
    /**
     * Gets all Doctor-Patient-Contacts from the Source the Interface is implemented for.
     * 
     * Please implement here the way you want to get your DPC Datas from you implemented Database
     * @throws java.lang.Exception
     */
    public void getDPCs() throws Exception;
}
