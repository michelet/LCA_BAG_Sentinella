/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Stefan
 */
public class SQLLiteDatabase extends AbstractDatabase implements IDatabase {

    private final String DRIVERCLASS = "jdbc:sqlite:";
    private final String filePath;
    
    public SQLLiteDatabase(URL fileURL) {
        this.filePath = fileURL.getPath();
        this.connectionString = DRIVERCLASS + this.filePath + ";Journal Mode=Off";
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
    

    @Override
    public Date parseObjectToDate(Object dateAsObject) {
        long dateAsLong = 0;
        final String DOUBLECLASS = "class java.lang.Double";
        String classString = dateAsObject.getClass().toString();
        
        if (classString.equals(DOUBLECLASS))
        {
            double dbl = (double) dateAsObject;
            dateAsLong = (new Double(dbl)).longValue();
        } 
        else if (classString.equals("class java.lang.Integer"))
        {
            int dateAsInt = (Integer) dateAsObject;
            dateAsLong = dateAsInt;
        }
        else { 
            try {
                dateAsLong = (Long) dateAsObject;
            } catch(Exception exp) {
                throw new IllegalArgumentException("Object is: " + classString + " - But Long or Integer excepted" );
            }
        }
        
        SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date(dateAsLong);
        return date;
    }
    
}
