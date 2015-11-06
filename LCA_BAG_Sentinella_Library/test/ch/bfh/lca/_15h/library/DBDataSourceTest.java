/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.impl.DBDataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import ch.bfh.lca._15h.library.Database.MSAccessDatabase;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class DBDataSourceTest {
    
    DBDataSource instance = null;
    final URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
    public DBDataSourceTest() {
        try {
            this.instance = new DBDataSource(new MSAccessDatabase(filePath));
            //this.instance.getDPCs();
        } catch (Exception ex) {
            Logger.getLogger(DBDataSourceTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("BAD");
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDPCs method, of class DBDataSource.
     */
    @Test
    public void testGetDPCs() throws Exception {
        System.out.println("getDPCs");
        
        
        assertTrue(instance.hasNext());
    }

    /**
     * Test of getSize method, of class DBDataSource.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");

        int expResult = 14;
        int result = this.instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toArray method, of class DBDataSource.
     */
    @Test
    public void testToArray() {
        System.out.println("toArray");

        DoctorPatientContact[] result = instance.toArray();
        assertNotNull(result);
    }

    /**
     * Test of hasNext method, of class DBDataSource.
     */
    @Test
    public void testHasNext() {
        System.out.println("hasNext");

        boolean expResult = true;
        boolean result = instance.hasNext();
        assertEquals(expResult, result);
    }

    /**
     * Test of next method, of class DBDataSource.
     */
    @Test
    public void testNext() {
        System.out.println("next");

        DoctorPatientContact expResult = instance.next();
        DoctorPatientContact result = instance.next();
        
        assertNotNull(result);
        assertNotSame(expResult, result);
    }
    
}
