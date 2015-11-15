/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.exporter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.Database.DBResultRow;
import ch.bfh.lca._15h.library.Database.DatabaseHandler;
import ch.bfh.lca._15h.library.Database.IDBParameter;
import ch.bfh.lca._15h.library.Database.IDatabase;
import ch.bfh.lca._15h.library.Database.IntegerDBParam;
import ch.bfh.lca._15h.library.Database.LongDBParam;
import ch.bfh.lca._15h.library.impl.DBDataSource;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class DatabaseExportHandler {
    
    IDatabase databse;
    
    public DatabaseExportHandler(IDatabase database) {
        this.databse = database;
    }
    
    public DataSource getElemntsByYear(int year) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.set(year-1, 12, 31);
        Date dtLow = cal.getTime();
        
        cal.set(year+1, 1, 1);
        Date dtHigh = cal.getTime();
        
        String query = "SELECT * FROM sentinellaRecord WHERE ContactDate BETWEEN ? AND ?";
        
        IDBParameter[] params = new IDBParameter[2];
        params[0] = new LongDBParam(1, dtLow.getTime());
        params[1] = new LongDBParam(1, dtHigh.getTime());
        
        DatabaseHandler db = new DatabaseHandler(this.databse);
        try {
            DBDataSource dataSource = new DBDataSource(this.databse, query, params);
            return dataSource;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseExportHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
