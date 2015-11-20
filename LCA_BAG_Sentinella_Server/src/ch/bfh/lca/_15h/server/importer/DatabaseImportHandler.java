/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.importer;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.Database.DatabaseHandler;
import ch.bfh.lca._15h.library.Database.IDBParameter;
import ch.bfh.lca._15h.library.Database.IDatabase;
import ch.bfh.lca._15h.library.Database.IntegerDBParam;
import ch.bfh.lca._15h.library.Database.LongDBParam;
import ch.bfh.lca._15h.library.Database.StringDBParam;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Stefan
 */
public class DatabaseImportHandler {
    
    IDatabase database;
    
    public DatabaseImportHandler(IDatabase databse) {
        this.database = databse;
    }
       
    public boolean writeSentinellaRecordInDatabase(DataSource datasource) {
        DatabaseHandler handler = new DatabaseHandler(this.database);
        
        String query  = "INSERT INTO sentinellaRecord VALUES (?, ?, ?, ?, ?)";
        IDBParameter[] params = new IDBParameter[5];
        int i = 0;
        try {
            for(DoctorPatientContact dpc : datasource) {
                params[0] = new StringDBParam(1, dpc.getPatID());
                params[1] = new IntegerDBParam(2, dpc.getPatSex().getValue());
                params[2] = new LongDBParam(3, dpc.getPatBirthdate().getTime());
                params[3] = new StringDBParam(4, dpc.getDiagnosisCommaDemilited());
                params[4] = new LongDBParam(5, dpc.getContactDate().getTime());
                               
                handler.fireIUDQuery(query, params);
                i++;
            }
            }catch(SQLException exp) {
              System.out.println(exp.getMessage());
              return false;
            } finally {
            System.out.println(Integer.toString(i) + " records added into Databse");
        }
        return true;
    }
    
    public DataSource readFromSentinallaDatabase() {
        DataSource dataSource = null;
        
        return dataSource;
    }
    
    public void createSentinellaDB() {
        DatabaseHandler handler = new DatabaseHandler(this.database);
        
        String query = "CREATE TABLE IF NOT EXISTS sentinellaRecord"
                + "(PatNumber TEXT,"
                + "PatSex INT,"
                + "PatBirthdate NUM,"
                + "PatDiagnosis TEXT,"
                + "ContactDate NUM)";
        
        try {
            handler.fireIUDQuery(query, null);
            System.out.println("New Database created. -- Create Table");
        } catch (SQLException exp) {
            System.out.println("Not able to create table: " + exp.getMessage());
        }
        
    }
}
