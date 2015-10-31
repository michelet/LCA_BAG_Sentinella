/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.ActivityFilter;
import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.Activity;
import ch.bfh.lca._15h.library.model.Patient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    ArrayList<Patient> aPatients;

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
    private void loadJSONInMemory() throws FileNotFoundException, IOException, ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //Library => https://code.google.com/p/json-simple/
        BufferedReader br;
        br = new BufferedReader(new FileReader(this.jsonFilePath));
        JSONArray array = (JSONArray) JSONValue.parseWithException(br);

        aPatients = new ArrayList<>();

        Patient p;
        Activity a;
        String k;

        for (Object item : array) {
            p = new Patient();
            for (Object key : ((JSONObject) item).keySet()) {
                //is it an attribut or activities
                if (key.toString().equals("activities")) {
                    a = new Activity();
                    for (Object key2 : ((JSONObject)((JSONObject) item).get(key)).keySet()) {
                        k = key2.toString();
                        k = k.substring(0, 1).toUpperCase() + k.substring(1); //capitalize 1st letter
                        a.getClass().getMethod("set" + k, String.class).invoke(a, ((JSONObject)((JSONObject) item).get(key)).get(key2));
                    }
                    p.addActivity(a);
                } else {
                    k = key.toString();
                    k = k.substring(0, 1).toUpperCase() + k.substring(1); //capitalize 1st letter
                    p.getClass().getMethod("set" + k, String.class).invoke(p, ((JSONObject) item).get(key));
                }
            }
            aPatients.add(p);
        }
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
     * @param activityFilter Allow to filter wich activities to export
     * @return JSON as a string
     * @throws Exception 
     */
    public static String toBAGJSON(DataSource source, ActivityFilter activityFilter) throws Exception {
        //Library => https://code.google.com/p/json-simple/
        JSONArray array = new JSONArray();
        JSONObject objPatient;
        JSONObject objActivity;
        Patient p;
        Activity a;

        for (int i = 0; i < source.countPatients(); i++) {
            p = source.getPatient(i);
            objPatient = new JSONObject();
            objPatient.put("patID", p.getPatID());
            //@TODO add other required fields

            //add activities
            for (int j = 0; j < p.getActivities().size(); j++) {
                a = p.getActivities().get(j);

                if (activityFilter == null || activityFilter.matchActivity(a)) {
                    objActivity = new JSONObject();
                    objActivity.put("patNumber", a.getPatNumber());
                    //@TODO add other required fields

                    //add to patient
                    objPatient.put("activities", objActivity);
                }
            }

            array.add(objPatient);
        }

        return array.toJSONString();
    }

    @Override
    public Patient getPatient(int index) throws Exception {
        if (aPatients == null) {
            this.loadJSONInMemory();
        }
        return aPatients.get(index);
    }

    @Override
    public int countPatients() throws Exception {
        if (aPatients == null) {
            this.loadJSONInMemory();
        }
        return aPatients.size();
    }
}
