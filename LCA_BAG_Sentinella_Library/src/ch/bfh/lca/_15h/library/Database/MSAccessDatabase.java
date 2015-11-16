package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MSAccessDatabase extends AbstractDatabase implements IDatabase {

    
    private final String DRIVERCLASS = "jdbc:ucanaccess://";
    
    private final String filePath;

    
    public MSAccessDatabase(URL fileURL) {
        this.filePath = fileURL.getPath();
        this.connectionString = DRIVERCLASS + this.filePath;
    }

     public MSAccessDatabase(String filePath) {
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
        
        if (classString.equals(DOUBLECLASS)) {
            double dbl = (double) dateAsObject;
            dateAsLong = (new Double(dbl)).longValue() - 25569;
            
        } else {
            try {
                dateAsLong = (Long) dateAsObject;
            } catch(Exception exp) {
                throw new IllegalArgumentException("Long or Double excepted");
            }
        }
        
        SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date((dateAsLong * 24 *60 * 60 *1000));
        return date;
    }
      
}
