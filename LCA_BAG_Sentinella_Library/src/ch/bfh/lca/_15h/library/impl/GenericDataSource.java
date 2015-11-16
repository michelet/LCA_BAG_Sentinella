/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.Iterator;

/**
 *
 * @author Stefan
 */
public class GenericDataSource implements DataSource {

    private DoctorPatientContact[] dpcList;
    private int iteratorIndex = 0;
    
    @Override
    public int countDoctorPatientContacts() throws Exception {
        if (dpcList == null) {
            return 0;
        } else {
            return this.dpcList.length;
        }
    }

    @Override
    public DoctorPatientContact getDoctorPatientContact(int index) throws Exception {
        if (dpcList == null) {
            return null;
        } else {
            return this.dpcList[index];
        }
    }

    @Override
    public void removeDoctorPatientContactFromMemory(int index) throws Exception {
        if (dpcList != null) {
          DoctorPatientContact[] newList = new DoctorPatientContact[this.dpcList.length -1];  
          
          int n = 0;
          for (int i = 0; i<this.dpcList.length; i++) {
              if (i != index) {
                  newList[n] = this.getDoctorPatientContact(i);
                  n++;
              }
          }
          this.dpcList = newList;
        } 
    }

    @Override
    public void removeDoctorPatientContactFromMemory(DoctorPatientContact object) throws Exception {
        if (dpcList != null) {
          DoctorPatientContact[] newList = new DoctorPatientContact[this.dpcList.length -1];  
          
          int n = 0;
          for (int i = 0; i<this.dpcList.length; i++) {
              if (!this.getDoctorPatientContact(i).equals(object)) {
                  newList[n] = this.getDoctorPatientContact(i);
                  n++;
              }
          }
          this.dpcList = newList;
        } 
    }

    @Override
    public int getIndexOfDocctorPatientContact(DoctorPatientContact object) {
        if (dpcList != null) {
          DoctorPatientContact[] newList = new DoctorPatientContact[this.dpcList.length -1];  
          
          for (int i = 0; i<this.dpcList.length; i++) {
              if (this.dpcList[i].equals(object)) {
                  return i;
              }
          }
          
          return -1;
        } else {
            return -1;
        } 
    }

    @Override
    public void addDoctorPatientContact(DoctorPatientContact object) {
        DoctorPatientContact[] newList;
        if (dpcList == null) {
            newList = new DoctorPatientContact[1];
            newList[0] = object;
        } else {
            newList = new DoctorPatientContact[this.dpcList.length+1];
            
            for(int i=0; i<this.dpcList.length;i++) {
                newList[i] = this.dpcList[i];
            }
            
            newList[this.dpcList.length] = object;
        }
        
        this.dpcList = newList;
    }

    @Override
    public void removeDoctorPatientContact(int index) throws Exception {
        if (dpcList != null) {
          DoctorPatientContact[] newList = new DoctorPatientContact[this.dpcList.length -1];  
          
          int n = 0;
          for (int i = 0; i<this.dpcList.length; i++) {
              if (i != index) {
                  newList[n] = this.getDoctorPatientContact(i);
                  n++;
              }
          }
          this.dpcList = newList;
        } 
    }

    @Override
    public boolean hasNext() {
        if (this.dpcList == null) {
            return false;
        } else if (this.dpcList.length == 0) {
            return false;
        } else if (this.iteratorIndex < this.dpcList.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DoctorPatientContact next() {
        DoctorPatientContact dpc = this.dpcList[this.iteratorIndex];
        this.iteratorIndex++;
        return dpc;
    }

    @Override
    public Iterator<DoctorPatientContact> iterator() {
        return this;
    }
    
    @Override
    public void resetIncerementIndex() {
        this.iteratorIndex = 0;
    }
    
}
