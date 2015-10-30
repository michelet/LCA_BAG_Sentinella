/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Patient;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 * @author micheletc
 */
public class DataSourceJSON implements DataSource {
    private String jsonFilePath;
    ArrayList<Patient> aPatients;

    public DataSourceJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        
    }
    
    public static DataSourceJSON loadJSONInMemory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient getPatient(int index) throws Exception {
        if(aPatients == null) this.loadJSONInMemory();
        return aPatients.get(index);
    }

    @Override
    public int countPatients() {
        if(aPatients == null) try {
            this.loadJSONInMemory();
        } catch (IllegalArgumentException ex) {
            return 0;
        }
        return aPatients.size();
    }
    
}
