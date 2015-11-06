/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.impl;

import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import ch.bfh.lca._15h.library.Database.DBResultRow;
import ch.bfh.lca._15h.library.Database.DatabaseHandler;
import ch.bfh.lca._15h.library.Database.IDatabase;
import ch.bfh.lca._15h.library.DataSource;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Stefan
 */
public class DBDataSource implements DataSource {

    private DoctorPatientContact[] dpcList;
    private final String aQuery = "SELECT DISTINCT Patient.PatNumber, Patient.PatBirthdate, Patient.PatSex, Patient.PatDiagnosis, Leistung.BehNumber, Leistung.Date FROM Patient INNER JOIN Leistung ON (Patient.PatLastBehNumber = Leistung.BehNumber) AND (Patient.PatNumber = Leistung.PatNumber) WHERE LEN(Patient.PatDiagnosis) > 0;";
    private final IDatabase databse;
    private int index = 0;
    
    public DBDataSource(IDatabase database) throws Exception {
        this.databse = database;
        this.getDPCs();
    }
    
    private void getDPCs() throws Exception {
        /* QUERY PARTS
          Patient.PatNumber - AS String
          Patient.PatBirthdate - As Integer
          Patient.PatSex - AS Integer, 0=Male
          Patient.PatDiagnosis - AS String
          Leistung.BehNumber - As String (Not used)
          Leistung.Date - As Integer
        */
        DatabaseHandler handler = new DatabaseHandler(this.databse);
        List<DBResultRow> results;
        results = handler.fireSelectQuery(this.aQuery, null);
        
        this.dpcList = new DoctorPatientContact[results.size()];
        
        for (DBResultRow result : results) {
            DoctorPatientContact newDPC = new DoctorPatientContact();
            newDPC.setPatID(result.getValueAt(0).toString());
            newDPC.setPatBirthdate(DoctorPatientContact.objectToDate(result.getValueAt(1)));
            newDPC.setPatSex(DoctorPatientContact.intToSex((Integer) result.getValueAt(2)));
            newDPC.setDiagnosis(DoctorPatientContact.stringToDiagnosis(result.getValueAt(3).toString()));
            newDPC.setContactDate(DoctorPatientContact.objectToDate(result.getValueAt(4)));
            this.dpcList[this.lastUsedIndex()] = newDPC;
            this.setNextIndex();
        }
        
        this.index = 0;
        
    }

    public int getSize() {
        if (this.dpcList != null) {
        return this.dpcList.length;
        } else {
            return 0;
        }
    }
    
    public DoctorPatientContact[] toArray() {
        return this.dpcList;
    }
    
    private int lastUsedIndex(){
        return this.index;
    }
    
    @Override
    public boolean hasNext() {
        boolean result = false;
                      
        if (this.lastUsedIndex() < this.getSize()-1) {
            result = true;
        }
        return result;
    }

    private void setNextIndex() {
        this.index++;
    }
    
    @Override
    public DoctorPatientContact next() {
        if (this.hasNext()) {
          this.setNextIndex();
          return this.toArray()[this.lastUsedIndex()];
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public int countDoctorPatientContacts() throws Exception {
        return dpcList.length;
    }

    @Override
    public DoctorPatientContact getDoctorPatientContact(int index) throws Exception {
        if(index >= dpcList.length) throw new NoSuchElementException();
        return dpcList[index];
    }
}
