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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

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
    
    private void loadJSONInMemory() throws FileNotFoundException, IOException, ParseException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //Library => https://code.google.com/p/json-simple/
        BufferedReader br;
        br = new BufferedReader(new FileReader(this.jsonFilePath));
        JSONArray array = (JSONArray)JSONValue.parseWithException(br);
        
        aPatients = new ArrayList<>();
        
        Patient p;
        String k;
        
        for (Object item : array) {
            p = new Patient();
            for(Object key : ((JSONObject)item).keySet()) {
                k = key.toString();
                k = k.substring(0, 1).toUpperCase() + k.substring(1); //capitalize 1st letter
                p.getClass().getMethod("set" + k, String.class).invoke(p, ((JSONObject)item).get(key));
            }
            aPatients.add(p);
        } 
    }
    
    public static String toBAGJSON(DataSource source) throws Exception {
        //Library => https://code.google.com/p/json-simple/
        JSONArray array = new JSONArray();
        JSONObject obj;
        Patient p;
        
        for(int i=0; i<source.countPatients(); i++) {
            p = source.getPatient(i);
            obj = new JSONObject();
            obj.put("patID", p.getPatID());
            //@TODO add other required fields
            array.add(obj);
        }
        
        return array.toJSONString();
    }

    @Override
    public Patient getPatient(int index) throws Exception {
        if(aPatients == null) this.loadJSONInMemory();
        return aPatients.get(index);
    }

    @Override
    public int countPatients() throws Exception {
        if(aPatients == null) this.loadJSONInMemory();
        return aPatients.size();
    } 
}