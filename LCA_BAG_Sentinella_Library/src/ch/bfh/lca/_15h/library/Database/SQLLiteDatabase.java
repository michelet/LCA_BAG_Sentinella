/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class SQLLiteDatabase extends AbstractDatabase implements IDatabase {

    private final String DRIVERCLASS = "jdbc:sqlite:";
    private final String filePath;
    
    public SQLLiteDatabase(URL fileURL) {
        this.filePath = fileURL.getPath();
        this.connectionString = DRIVERCLASS + this.filePath;
    }

     public SQLLiteDatabase(String filePath) {
        this.filePath = filePath;
        this.connectionString = DRIVERCLASS + this.filePath;
    }


    @Override
    public String getConnectionString() {
        return this.connectionString;
    }

    @Override
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
}
