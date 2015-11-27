/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.exporter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.Database.DatabaseHandler;
import ch.bfh.lca._15h.library.Database.IDatabase;
import ch.bfh.lca._15h.library.impl.DBDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 * Class to get Sentinella Records from a Databse
 */
public class DatabaseExportHandler {
    
    IDatabase databse;
    
    /**
     * Constructor of Database Exporthandler.
     * @param database 
     */
    public DatabaseExportHandler(IDatabase database) {
        this.databse = database;
    }
    
    /**
     * Gets Elemts by Year. 
     * @deprecated Actually there is no Year filter implemented in the Query.
     *   Use getElements instead.
     * @param year Year from which you want the records.
     * @return
     * @throws Exception 
     */
    public DataSource getElemntsByYear(int year) throws Exception {
       
        String query = "SELECT * FROM sentinellaRecord";
        
        DatabaseHandler db = new DatabaseHandler(this.databse);
        try {
            this.databse.openConnection();
            DBDataSource dataSource = new DBDataSource(this.databse, query, null);
            return dataSource;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseExportHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            this.databse.closeConnection();
        }
    }
    
    public DataSource getElemnts() throws Exception {
       
        String query = "SELECT * FROM sentinellaRecord";
        
        DatabaseHandler db = new DatabaseHandler(this.databse);
        try {
            this.databse.openConnection();
            DBDataSource dataSource = new DBDataSource(this.databse, query, null);
            return dataSource;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseExportHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally {
            this.databse.closeConnection();
        }
    }    
    
}
