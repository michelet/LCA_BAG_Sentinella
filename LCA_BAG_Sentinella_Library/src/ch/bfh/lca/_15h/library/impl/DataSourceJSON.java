/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DoctorPatientContactFilter;
import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Class to read information from a BAG structured JSON file.
 * @author Cédric Michelet
 */
public class DataSourceJSON implements DataSource {
    /**
     *  Store path of the JSON file containing the data.
     */
    private String jsonFilePath;
    
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
     * @param jsonFilePath Path of the JSON file containing the data
     */
    public DataSourceJSON(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    /***
     * Load the JSON file and create the model in memory.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException 
     */
    private void loadJSONInMemory() throws FileNotFoundException, IOException, ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
        //Library => https://code.google.com/p/json-simple/
        BufferedReader br;
        br = new BufferedReader(new FileReader(this.jsonFilePath));
        JSONArray array, diagCodes; // = (JSONArray) JSONValue.parseWithException(br);
        JSONObject sentinellaObject = (JSONObject) JSONValue.parseWithException(br);

        aPatients = new ArrayList<>();

        DoctorPatientContact dpc;
        String k;
        
        if(sentinellaObject == null) return;
        array = (JSONArray)sentinellaObject.get("SentinellaRecords");               

        for (Object item : array) {
            dpc = new DoctorPatientContact();
            
            for (Object key : ((JSONObject) item).keySet()) {
                k = key.toString();
                
                switch (k) {
                    case "PatNumber":
                        dpc.setPatID((String)((JSONObject) item).get(key));
                        break;
                    case "PatSex":
                        dpc.setPatSex(DoctorPatientContact.intToSex(Integer.parseInt((String)((JSONObject) item).get(key))));
                        break;
                    case "PatBirthdate":
                        dpc.setPatBirthdate(DoctorPatientContact.objectToDate((String)((JSONObject) item).get(key)));
                        break;
                    case "DiagCode":
                        diagCodes = (JSONArray)((JSONObject) item).get(key);
                         for (Object item2 : diagCodes) {
                            dpc.addDiagnosis((String)item2); 
                        }   
                        //dpc.setDiagnosis(DoctorPatientContact.stringToDiagnosis((String)((JSONObject) item).get(key)));
                        break;
                    case "ConsDate":
                        dpc.setContactDate(DoctorPatientContact.objectToDate((String)((JSONObject) item).get(key)));
                        break;
                }
            }   
            
            aPatients.add(dpc);
        }
        
        //reset iterator index
        iteratorIndex = 0;
    }

    /***
     * Convert data contained in a datasource to a BAG JSON file.
     * @param source DataSource containing the input data.
     * @return JSON as a string
     * @throws Exception 
     */
    public static String toBAGJSON(DataSource source) throws Exception {
        return toBAGJSON(source, null);
    }
    
    /***
     * Convert data contained in a datasource to a BAG JSON file.
     * @param source DataSource containing the input data.
     * @param doctorPatientContactFilter Allow to filter wich activities to export
     * @return JSON as a string
     * @throws Exception 
     */
    public static String toBAGJSON(DataSource source, DoctorPatientContactFilter doctorPatientContactFilter) throws Exception {
        //Library => https://code.google.com/p/json-simple/
        JSONArray array = new JSONArray();
        JSONObject objPatient;
        JSONArray diagnosisArray;
        DoctorPatientContact dpc;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
 
        for (int i = 0; i < source.countDoctorPatientContacts(); i++) {
            dpc = source.getDoctorPatientContact(i);
            objPatient = new JSONObject();
            objPatient.put("PatNumber", dpc.getPatID());
            objPatient.put("PatSex", dpc.getPatSex().getValue()+""); //+"" => to convert int to string
            objPatient.put("PatBirthdate", sdf.format(dpc.getPatBirthdate()));
            
            //build diagnosis object
            diagnosisArray = new JSONArray();         
            for(int j=0; j < dpc.getDiagnosis().length; j++) {
              diagnosisArray.add(dpc.getDiagosisAtIndex(j));
            }
            objPatient.put("DiagCode", diagnosisArray);
            
            objPatient.put("ConsDate", sdf.format(dpc.getContactDate()));

            array.add(objPatient);
        }

        JSONObject ret = new JSONObject();
        ret.put("SentinellaRecords", array);
        return ret.toJSONString();
    }
    
    /***
     * Convert data contained in a datasource and write it to a BAG JSON file.
     * @param source DataSource containing the input data.
     * @param doctorPatientContactFilter Allow to filter wich activities to export
     * @param filePath path of the file containing the result
     * @throws Exception 
     */
    public static void writeBAGJSONToFile(DataSource source, DoctorPatientContactFilter doctorPatientContactFilter, String filePath) throws Exception {
        String json = DataSourceJSON.toBAGJSON(source);
        Files.write(Paths.get(filePath), json.getBytes());
    }

    @Override
    public int countDoctorPatientContacts() throws Exception {
        if (aPatients == null) this.loadJSONInMemory();
        return aPatients.size();
    }

    @Override
    public DoctorPatientContact getDoctorPatientContact(int index) throws Exception {
       if (aPatients == null) this.loadJSONInMemory();
       return aPatients.get(index);
    }

    @Override
    public boolean hasNext() {
        try {
            if(aPatients == null) this.loadJSONInMemory();
            if(iteratorIndex >= aPatients.size()) return false;
            return true;
        } catch (Exception e) {
            throw new NoSuchElementException(e.getLocalizedMessage());  
        }
    }

    @Override
    public DoctorPatientContact next() {
        try {
            if(aPatients == null) this.loadJSONInMemory();
            if(!this.hasNext()) throw new NoSuchElementException();
            ++iteratorIndex;
            return aPatients.get(iteratorIndex);
        } catch (Exception e) {
            throw new NoSuchElementException(e.getLocalizedMessage());
        }
    }
    
    @Override
    public void removeDoctorPatientContact(int index) throws Exception {
        aPatients.remove(index);
    }
}
