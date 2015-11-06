/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public abstract class AbstractDatabase implements IDatabase{

    Connection connection;
    String connectionString;
    
    
  @Override
    public Connection getConnection() {
        if (this.connection == null) {
            return null;
        } else {
            return this.connection;            
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        if (this.getConnection() != null) {
            if (!this.getConnection().isClosed()) {
                this.getConnection().close();
                this.destroyConnection();
            }
        }
    }

    @Override
    public void openConnection()  throws SQLException  {
        connection=DriverManager.getConnection(this.connectionString);
    }

    @Override
    public boolean hasOpenConnection() {
        
        boolean isOpen = false;
        try {
          if (this.getConnection() != null) {
            if (!this.getConnection().isClosed()) {
                isOpen = true;
            }
          }   
          return isOpen;
        } catch (SQLException ex) {
            Logger.getLogger(MSAccessDatabase.class.getName()).log(Level.SEVERE, null, ex);
            this.destroyConnection();
            return false;
        } 
    }
    
    /**
     * Destroys the Connection Object which is stored in the connection variable
     */
    private void destroyConnection() {
        this.connection = null;
    }
    
}
