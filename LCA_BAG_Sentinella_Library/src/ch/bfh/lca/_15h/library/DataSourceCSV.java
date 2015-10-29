/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author micheletc
 */
public class DataSourceCSV implements DataSource {
    private String patientsCSVPath;
    private String activitiesCSVPath;
    
    public DataSourceCSV(String patientsCSVPath, String activitiesCSVPath) {
        this.patientsCSVPath = patientsCSVPath;
        this.activitiesCSVPath = activitiesCSVPath;
    }
    
    @Override
    public ArrayList<Patient>  parsePatients() throws Exception {
        ArrayList<Patient> aPatients = new ArrayList<Patient>();
        Patient p;
        String[] pCSV;
        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        br = new BufferedReader(new FileReader(this.patientsCSVPath));
        
        while ((line = br.readLine()) != null) {
                pCSV = line.split(cvsSplitBy);
                p = new Patient();
                p.setPatID(pCSV[0]);
                aPatients.add(p);
            }
        
        br.close();
        
        return aPatients;
    }
    
    @Override
    public ArrayList<Activity> parseActivities() throws Exception {
        return new ArrayList<Activity>();
    }
}
