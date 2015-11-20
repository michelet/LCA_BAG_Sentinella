/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.importer;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.Database.SQLLiteDatabase;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author Stefan
 */
public class ImportHandler {
    
    private final String SOURCE_FILEPATH;
    private final String DEST_DB_FILEPATH;
    
    public ImportHandler(String sourcePathJason, String destPathDB) {
        this.DEST_DB_FILEPATH = destPathDB;
        this.SOURCE_FILEPATH = sourcePathJason;
    }
    
    public void importData() {

            SQLLiteDatabase database = new SQLLiteDatabase(this.DEST_DB_FILEPATH);
            
            
            DatabaseImportHandler dih = new DatabaseImportHandler(database);
            dih.createSentinellaDB();
            
            System.out.println("Start import from :" + this.SOURCE_FILEPATH);
            JsonImportHandler jih = new JsonImportHandler(this.SOURCE_FILEPATH);
            
            DataSource dataSource = jih.getDataSource();
             boolean itWorks = false;
        try {            
            database.openConnection();
            itWorks = dih.writeSentinellaRecordInDatabase(dataSource);
            
            database.closeConnection();
            
            if (itWorks) {
                System.out.println("Import finished without errors");
            } else {
                System.out.println("Import finished with errors");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
