/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
    private void loadCSVInMemory() throws  Exception  {
        aPatients = new ArrayList<>();
        DoctorPatientContact dpc;
        String[] nextLine;
        char cvsSplitBy = ',';
        Boolean isFirstLine = true;

        int patNumberColIndex;
        int patBirthdateColIndex;
        int patSexColIndex;
        int patDiagnosisColIndex;
        int patDateColIndex;

        //******************************************LOAD PATIENTS
        CSVReader reader = new CSVReader(new FileReader(this.patientsCSVPath), cvsSplitBy);

        patNumberColIndex = patBirthdateColIndex = patSexColIndex = patDiagnosisColIndex = -1;
        
        //@TODO check file is csv and conform to the format (column)
        while ((nextLine = reader.readNext()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                //search position of each col
                for(int i=0; i<nextLine.length; i++)
                    switch(nextLine[i]) {
                        case "PatNumber":
                            patNumberColIndex = i;
                            break;
                        case "PatBirthdate":
                            patBirthdateColIndex = i;
                            break;
                         case "PatSex":
                            patSexColIndex = i;
                            break;
                         case "PatDiagnosis":
                            patDiagnosisColIndex = i;
                            break;  
                    }
            } else {
                dpc = new DoctorPatientContact();
                if(patNumberColIndex > -1)
                    dpc.setPatID(nextLine[patNumberColIndex]);
                if(patBirthdateColIndex > -1)
                    dpc.setPatBirthdate(DoctorPatientContact.objectToDate(nextLine[patBirthdateColIndex]));
                if(patSexColIndex > -1)
                    dpc.setPatSex(DoctorPatientContact.intToSex(Integer.parseInt(nextLine[patSexColIndex])));
                if(patDiagnosisColIndex > -1)
                    dpc.setDiagnosis(DoctorPatientContact.stringToDiagnosis(nextLine[patDiagnosisColIndex]));
                
                //take only patient with diagnosis
                if(dpc.getDiagnosis().length > 0)
                    aPatients.add(dpc);
            }
        }

        reader.close();
        
        //******************************************LOAD ACTIVITIES
        if(this.activitiesCSVPath == null) return;
        
        //create local cache to speed up patient search
        HashMap <String, DoctorPatientContact> patientsCache = new HashMap();
        for(DoctorPatientContact p2 : aPatients) {
            patientsCache.put(p2.getPatID(), p2);
        }
            
        reader = new CSVReader(new FileReader(this.activitiesCSVPath), cvsSplitBy);
        patNumberColIndex = patDateColIndex = -1;
        isFirstLine = true;
        String activityDate;
        String activityPatNumber;

        //@TODO check file is csv and conform to the format (column)
        while ((nextLine = reader.readNext()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                //search position of each col
                for(int i=0; i<nextLine.length; i++)
                    switch(nextLine[i]) {
                        case "PatNumber":
                            patNumberColIndex = i;
                            break;
                        case "Date":
                            patDateColIndex = i;
                            break;
                    }
            } else {
                activityDate = null;
                activityPatNumber = null;
                
                if(patNumberColIndex > -1)
                    activityPatNumber = nextLine[patNumberColIndex];
                if(patDateColIndex > -1)
                    activityDate = nextLine[patDateColIndex];
               
                 //search patient
                if(activityPatNumber != null && activityDate != null) {
                    dpc = patientsCache.get(activityPatNumber);
                    if(dpc != null) {
                        dpc.setContactDate(DoctorPatientContact.objectToDate(activityDate));
                    }
                }
            }
        }

        reader.close();
        
        //******************************************REMOVE PATIENT WITHOUT ACTIVITIES
         for (Map.Entry<String, DoctorPatientContact> entry : patientsCache.entrySet()) {
            if(entry.getValue().getContactDate() == null)
                aPatients.remove(entry.getValue());
        }
        
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
            throw new NoSuchElementException(e.getLocalizedMessage());       
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
            throw new NoSuchElementException(e.getLocalizedMessage());    
        }
    }
}
