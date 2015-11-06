package ch.bfh.lca._15h.library.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {

    /**
     * Gets the SQL Connection of the Database Object.
     * 
     * You will need to import java.sql.Connection
     * @return SQL Connection (java.sql.Connection)
     */
    public Connection getConnection();

    public String getConnectionString();
    
    public void setConnectionString(String connectionString);
    
    
    
    /**
     * @author Stefan Schöpfer
     * 
     * Does close an active java.sql.Connection. When the connection is already closed, the java.sql.Connection Object will be destroyed.
     * After the java.sql.Connection has changed successfully, the java.sql.Connection Object has to be deleted.
     * 
     * To delete the java.sql.Connection Object, it has to be nulled.
     * @throws java.sql.SQLException 
     */
    public void closeConnection() throws SQLException;

    /**
     * Opens or creates an java.sql.Connection. Whenn there is already an open java.sql.Connection it has to be deleted.
     * @throws java.sql.SQLException
     */
    public void openConnection() throws SQLException;

    /**
     * Does check, if the IDatabase has an open Connection. When there is an open java.sql.Connection. The method returns true
     * @return boolean. True if the Object have an open java.sql.Connection
     */
    public boolean hasOpenConnection();
    
}
