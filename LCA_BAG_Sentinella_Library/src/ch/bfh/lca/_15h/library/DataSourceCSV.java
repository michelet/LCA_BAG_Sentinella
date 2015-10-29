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
    public ArrayList<Patient> parsePatients() throws Exception {
        ArrayList<Patient> aPatients = new ArrayList<Patient>();
        Patient p;
        String[] pCSV;
        String[] csvHeaders = null;
        int i;
        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        Boolean isFirstLine = true;

        br = new BufferedReader(new FileReader(this.patientsCSVPath));

        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                csvHeaders = line.split(cvsSplitBy);
            } else {
                pCSV = line.split(cvsSplitBy);
                p = new Patient();
                
                for(i=0; i<csvHeaders.length; i++) {
                    //String propertyName = csvHeaders[i];
                    //String methodName = "set" + StringUtils.capitalize(propertyName);
                    //String methodName = "set" + propertyName.replaceAll("\"", "");
                    if(i < pCSV.length) //make sure data line as enought column
                        p.getClass().getMethod("set" + csvHeaders[i].replaceAll("\"", ""), String.class).invoke(p, pCSV[i].replaceAll("\"", ""));
                }
                
                aPatients.add(p);
            }
        }

        br.close();
        
        return aPatients;
    }
    
    @Override
    public ArrayList<Activity> parseActivities() throws Exception {
        return new ArrayList<Activity>();
    }
}
