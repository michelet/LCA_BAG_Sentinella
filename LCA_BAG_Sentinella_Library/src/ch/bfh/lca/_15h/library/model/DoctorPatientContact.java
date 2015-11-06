/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.model;

import java.util.Date;


public class DoctorPatientContact {

        public enum ESex {
        MALE(0),
        FEMALE(1);
        
        private final int value;
        
        ESex(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    private String patID;
    private ESex patSex;
    private Date patBirthDate;
    private String[] patDiagnosis;
    private Date contactDate;
        
        
    public void setPatSex(ESex patSex) {
        this.patSex = patSex;
    }

    public void setPatID(String patID) {
        this.patID = patID;
    }

    public void setPatBirthdate(Date patBirthdate) {
        this.patBirthDate = patBirthdate;
    }

    public void setDiagnosis(String[] patDiagnosis) {
        this.patDiagnosis = patDiagnosis;
    }

    public void addDiagnosis(String patDiagnosis) {
        
        if (this.patDiagnosis != null) {
            int newSize = this.patDiagnosis.length;
            newSize++;
            
            String[] oldArray = this.getDiagnosis();
            String[] newArray = new String[newSize];
            
            for(int i=0; i<newSize-1;i++) {
                newArray[i] = oldArray[i];
            }
            
            newArray[newSize] = patDiagnosis;
            this.setDiagnosis(newArray);
            
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public ESex getPatSex() {
        return this.patSex;
    }

    public String getPatID() {
        return this.patID;
    }

    public Date getPatBirthdate() {
       return this.patBirthDate;
    }

    public String[] getDiagnosis() {
        return this.patDiagnosis;
    }

    public String getDiagnosisCommaDemilited() {
        String result = "";
        
        for(int i=0; i<this.getDiagnosis().length;i++) {
            if (i==0) {
                result = result + this.getDiagosisAtIndex(i);
            } else {
                result = "," + result + this.getDiagosisAtIndex(i);
            }
        }
        
        return result;
    }

    public Date getContactDate() {
        return this.contactDate;
    }
    
    public String getDiagosisAtIndex(int index) {
        if (index >= 0 && index < this.getDiagnosis().length) {
            return this.getDiagnosis()[index];
        } else
        {
            throw new IndexOutOfBoundsException();
        }
    }
    
}