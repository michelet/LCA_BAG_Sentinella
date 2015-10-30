/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Activity;
import ch.bfh.lca._15h.library.model.Patient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to read information from a BAG structured CSV file.
 * @author Cédric Michelet
 */
public class DataSourceCSV implements DataSource {
    /**
     * Store path of the CSV file containing the patients.
     */
    private String patientsCSVPath;
    
    /**
     * Store path of the CSV file containing the activities.
     */
    private String activitiesCSVPath;
    
    /**
     * List of loaded patients.
     */
    ArrayList<Patient> aPatients;

    /**
     * Indicate what to do when an activity is found but not the related patient. True=ignoe the activity, False=Generate an exception
     */
    Boolean ignoreActivitiesWithoutPatient = false;
    
    /**
     * Constructor
     * @param patientsCSVPath Path of the CSV file containing the patients
     * @param activitiesCSVPath  Path of the CSV file containing the activities
     */
    public DataSourceCSV(String patientsCSVPath, String activitiesCSVPath) {
        this.patientsCSVPath = patientsCSVPath;
        this.activitiesCSVPath = activitiesCSVPath;
    }

    /**
     * Getter
     * @return 
     */
    public Boolean getIgnoreActivitiesWithoutPatient() {
        return ignoreActivitiesWithoutPatient;
    }

    /**
     * Setter
     * @param ignoreActivitiesWithoutPatient 
     */
    public void setIgnoreActivitiesWithoutPatient(Boolean ignoreActivitiesWithoutPatient) {
        this.ignoreActivitiesWithoutPatient = ignoreActivitiesWithoutPatient;
    }
    
    /**
     * Load the CSV file and create the model in memory.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws Exception 
     */
    private void loadCSVInMemory() throws FileNotFoundException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        aPatients = new ArrayList<>();
        Patient p;
        Activity a;
        String[] pCSV;
        String[] csvHeaders = null;
        int i;

        BufferedReader br;
        String line;
        String cvsSplitBy = ",";
        Boolean isFirstLine = true;

        //******************************************LOAD PATIENTS
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
        
        //******************************************LOAD ACTIVITIES
        if(this.activitiesCSVPath == null) return;
        
        //create local cache to speed up patient search
        HashMap <String, Patient> patientsCache = new HashMap();
        for(Patient p2 : aPatients) {
            patientsCache.put(p2.getPatID(), p2);
        }
            
        br = new BufferedReader(new FileReader(this.activitiesCSVPath));
        isFirstLine = true;
        csvHeaders = null;
        
        //@TODO check file is csv and conform to the format (column)
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                csvHeaders = line.split(cvsSplitBy);
                for(int k=0; k<csvHeaders.length; k++)
                    csvHeaders[k] = "set" + csvHeaders[k].replaceAll("\"", "");
            } else {
                pCSV = line.split(cvsSplitBy);
                a = new Activity();

                if (csvHeaders != null) {
                    for (i = 0; i < csvHeaders.length; i++) {
                    //String propertyName = csvHeaders[i];
                        //String methodName = "set" + StringUtils.capitalize(propertyName);
                        //String methodName = "set" + propertyName.replaceAll("\"", "");
                        if (i < pCSV.length) //make sure data line as enought column
                        {
                            a.getClass().getMethod(csvHeaders[i], String.class).invoke(a, pCSV[i].replaceAll("\"", ""));
                        }
                    }
                }
                
                p = patientsCache.get(a.getPatNumber());
                if(p == null) {
                    if(ignoreActivitiesWithoutPatient == false)
                        throw new Exception("Patient with ID(" + a.getPatNumber() + ") not found for Activity with ID(" + a.getID() + ")");
                }
                else
                    p.addActivity(a);
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
