/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Patient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 * @author micheletc
 */
public class DataSourceCSV implements DataSource {
    private String patientsCSVPath;
    private String activitiesCSVPath;
    ArrayList<Patient> aPatients;

    public DataSourceCSV(String patientsCSVPath, String activitiesCSVPath) {
        this.patientsCSVPath = patientsCSVPath;
        this.activitiesCSVPath = activitiesCSVPath;
    }

    private void loadCSVInMemory() throws FileNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        aPatients = new ArrayList<>();
        Patient p;
        String[] pCSV;
        String[] csvHeaders = null;
        int i;

        BufferedReader br;
        String line;
        String cvsSplitBy = ",";
        Boolean isFirstLine = true;

        br = new BufferedReader(new FileReader(this.patientsCSVPath));

        //@TODO check file is csv and conform to the format (column)
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                csvHeaders = line.split(cvsSplitBy);
            } else {
                pCSV = line.split(cvsSplitBy);
                p = new Patient();

                if (csvHeaders != null) {
                    for (i = 0; i < csvHeaders.length; i++) {
                    //String propertyName = csvHeaders[i];
                        //String methodName = "set" + StringUtils.capitalize(propertyName);
                        //String methodName = "set" + propertyName.replaceAll("\"", "");
                        if (i < pCSV.length) //make sure data line as enought column
                        {
                            p.getClass().getMethod("set" + csvHeaders[i].replaceAll("\"", ""), String.class).invoke(p, pCSV[i].replaceAll("\"", ""));
                        }
                    }
                }
                aPatients.add(p);
            }
        }

        br.close();
    }
    
    @Override
    public Patient getPatient(int index) throws Exception {
        if(aPatients == null) this.loadCSVInMemory();
        return aPatients.get(index);
    }

    @Override
    public int countPatients() throws Exception {
        if(aPatients == null) this.loadCSVInMemory();
        return aPatients.size();
    }

   
}
