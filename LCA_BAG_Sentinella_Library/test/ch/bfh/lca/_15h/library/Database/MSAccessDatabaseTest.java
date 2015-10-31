/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class MSAccessDatabaseTest {
    
    final MSAccessDatabase instance;
    public MSAccessDatabaseTest() {
        URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
        this.instance = new MSAccessDatabase(filePath);
    }

    /**
     * Test of getConnection method, of class MSAccessDatabase.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
            
        Connection result = instance.getConnection();
        assertNull(result);
        
        try {
            this.instance.openConnection();
            result = instance.getConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(MSAccessDatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertNotNull(result);
    }

    /**
     * Test of closeConnection method, of class MSAccessDatabase.
     */
    @Test
    public void testCloseConnection() throws Exception {
        System.out.println("closeConnection");

        // Preparing Test. First wie need a verified open connection to do the test.
        instance.openConnection();
        Connection result = instance.getConnection();
        assertNotNull(result);
        
        // result have to be null
        instance.closeConnection();
        result = instance.getConnection();
        assertNull(result); 
    }

    /**
     * Test of openConnection method, of class MSAccessDatabase.
     */
    @Test
    public void testOpenConnection() throws Exception {
        System.out.println("openConnection");

        instance.openConnection();
        Connection result = instance.getConnection();
        assertNotNull(result);        
        
    }

    /**
     * Test of hasOpenConnection method, of class MSAccessDatabase.
     */
    @Test
    public void testHasOpenConnection() throws Exception {
        System.out.println("hasOpenConnection");

        instance.closeConnection();
        boolean result = instance.hasOpenConnection();
        assertFalse(result);
        
        instance.openConnection();
        result = instance.hasOpenConnection();
        assertTrue(result);
        
        
    }
    
}
