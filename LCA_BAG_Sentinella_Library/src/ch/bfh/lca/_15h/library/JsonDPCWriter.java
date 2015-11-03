/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Stefan
 */
public class JsonDPCWriter {
    
    private final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private final String jsonPath;
    
    public JsonDPCWriter(String jsonPath) {
        this.jsonPath = jsonPath;
    }
    
    public void createDPCJson(IDataSource dataSource) throws IOException{
        
        // DPC Entries
        JSONObject jsonObject = new JSONObject();
        JSONArray dpcList = new JSONArray();
        
        while(dataSource.hasNext()) {
            JSONObject obj = new JSONObject();
            DoctorPatientContact dpc = dataSource.next();
            obj.put("PatNumber", dpc.getPatID());
            obj.put("PatSex", dpc.getPatSex().getValue());
            obj.put("PatBirthdate", DATEFORMAT.format(dpc.getPatBirthdate()));
            obj.put("DiagCode", this.getDiagnosisArray(dpc));
            obj.put("ConsDate", DATEFORMAT.format(dpc.getContactDate()));
            
            dpcList.add(obj);
            
        }
        jsonObject.put("SentinellaRec", dpcList);

        this.writeJsonFileToDestination(jsonObject, this.jsonPath);
        
    }
        
    private JSONArray getDiagnosisArray(DoctorPatientContact dpc) {
            JSONArray newArray = new JSONArray();
            String[] diagnosis = dpc.getDiagnosis();
            
            if (dpc.getDiagnosis() != null) {
            
            for(int i=0; i < diagnosis.length; i++) {
              JSONObject jObj = new JSONObject();
              jObj.put("DiagID", diagnosis[i]);
              newArray.add(jObj);
            }
            return newArray;
            } else {
                return null;
            }
    }
    
    private void writeJsonFileToDestination(JSONObject jsonObject, String dest) throws IOException  {

        File textFile = new File(dest);
        BufferedWriter out = new BufferedWriter(new FileWriter(textFile));
        try {
            out.write(jsonObject.toJSONString());
        } finally {
            out.close();
        }             
    }
    
}
