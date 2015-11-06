/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class to read information from a BAG structured CSV file.
 * @author Cédric Michelet
 */
public class DataSourceCSV implements DataSource {
    /**
     * Store path of the CSV file containing the patients.
     */
    protected String patientsCSVPath;
    
    /**
     * Store path of the CSV file containing the activities.
     */
    protected String activitiesCSVPath;
    
    /**
     * List of loaded patients.
     */
    ArrayList<DoctorPatientContact> aPatients;

    /**
     * Store current postion for iterator
     */
    int iteratorIndex;
    
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
        DoctorPatientContact dpc;
        String[] csvHeaders = null;

        BufferedReader br;
        String line;
        String cvsSplitBy = ",";
        Boolean isFirstLine = true;
        Splitter splitter = Splitter.on(',');
        Iterable<String> is;
        int col;

        //******************************************LOAD PATIENTS
        br = new BufferedReader(new FileReader(this.patientsCSVPath));

        //@TODO check file is csv and conform to the format (column)
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                csvHeaders = line.split(cvsSplitBy);
                for(int k=0; k<csvHeaders.length; k++)
                    //csvHeaders[k] = "set" + csvHeaders[k].replaceAll("\"", "");
                    csvHeaders[k] = csvHeaders[k].replaceAll("\"", "");
            } else {
                //pCSV = line.split(cvsSplitBy);
                dpc = new DoctorPatientContact();

                is = splitter.split(line);
                col = 0;
                if (csvHeaders != null) {
                    if (col < csvHeaders.length) {
                        for (String st : is) {
                            //p.getClass().getMethod(csvHeaders[col], String.class).invoke(p, st.replaceAll("\"", ""));

                            if(csvHeaders[col].equals("PatNumber"))
                                dpc.setPatID(st);
                            else if(csvHeaders[col].equals("PatBirthdate"))
                                dpc.setPatBirthdate(DoctorPatientContact.objectToDate(st));
                            else if(csvHeaders[col].equals("PatSex"))
                                dpc.setPatSex(DoctorPatientContact.intToSex(Integer.parseInt(st)));
                             else if(csvHeaders[col].equals("PatDiagnosis"))
                                dpc.setDiagnosis(DoctorPatientContact.stringToDiagnosis(st));
                            col++;
                        }
                    }
                }
                
                aPatients.add(dpc);
            }
        }

        br.close();
        
        //******************************************LOAD ACTIVITIES
        if(this.activitiesCSVPath == null) return;
        
        //create local cache to speed up patient search
        HashMap <String, DoctorPatientContact> patientsCache = new HashMap();
        for(DoctorPatientContact p2 : aPatients) {
            patientsCache.put(p2.getPatID(), p2);
        }
            
        br = new BufferedReader(new FileReader(this.activitiesCSVPath));
        isFirstLine = true;
        csvHeaders = null;
        String activityDate;
        String activityPatNumber;

        //@TODO check file is csv and conform to the format (column)
        while ((line = br.readLine()) != null) {
            
            if (isFirstLine) {
                isFirstLine = false;
                csvHeaders = line.split(cvsSplitBy);
                for(int k=0; k<csvHeaders.length; k++)
                    //csvHeaders[k] = "set" + csvHeaders[k].replaceAll("\"", "");
                    csvHeaders[k] = csvHeaders[k].replaceAll("\"", "");
            } else {
                //pCSV = line.split(cvsSplitBy);
                is = splitter.split(line);

                col = 0;
                activityDate = null;
                activityPatNumber = null;
                
                if (csvHeaders != null) {
                    if (col < csvHeaders.length) {
                        for (String st : is) {
                            //a.getClass().getMethod(csvHeaders[col], String.class).invoke(a, st.replaceAll("\"", ""));
                            if(csvHeaders[col].equals("PatNumber"))
                                activityPatNumber = st;
                            else if(csvHeaders[col].equals("Date"))
                                activityDate = st;
                            col++;
                        }
                    }
                }  
                
                //search patient
                if(activityPatNumber != null && activityDate != null) {
                    dpc = patientsCache.get(activityPatNumber);
                    if(dpc != null) {
                        dpc.setContactDate(DoctorPatientContact.objectToDate(activityDate));
                    }
                }
            }
        }

        br.close();
        
        //reset iterator index
        iteratorIndex = 0;
    }

    @Override
    public int countDoctorPatientContacts() throws Exception {
        if(aPatients == null) this.loadCSVInMemory();
        return aPatients.size();
    }

    @Override
    public DoctorPatientContact getDoctorPatientContact(int index) throws Exception {
        if(aPatients == null) this.loadCSVInMemory();
        return aPatients.get(index);
    }

    @Override
    public boolean hasNext() {
        try {
            if(aPatients == null) this.loadCSVInMemory();
            if(iteratorIndex >= aPatients.size()) return false;
            return true;
        } catch (Exception e) {
            return false;     
        }
    }

    @Override
    public DoctorPatientContact next() {
        try {
            if(aPatients == null) this.loadCSVInMemory();
            if(!this.hasNext()) throw new NoSuchElementException();
            ++iteratorIndex;
            return aPatients.get(iteratorIndex);
        } catch (Exception e) {
            throw new NoSuchElementException();    
        }
    }
}
