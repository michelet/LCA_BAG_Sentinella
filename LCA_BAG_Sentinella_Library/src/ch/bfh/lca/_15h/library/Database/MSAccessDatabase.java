package ch.bfh.lca._15h.library.Database;

import java.net.URL;

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
}
