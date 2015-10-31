/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.sql.ResultSet;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class DBResultRowTest {
    
    DBResultRow instance;
    
    public DBResultRowTest() {
        this.instance = new DBResultRow();
    }
    
    @Before
    public void setUp() {
    Integer[] values = {10, 20, 30, 40}; 
    String[] names = {"Val1","Val2","Val3","Val4"};
    
    this.instance.setValues(names, values);
    
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of getColNames method, of class DBResultRow.
     */
    @Test
    public void testGetColNames() {
        System.out.println("getColNames");
        
        String[] expResult =  {"Val1","Val2","Val3","Val4"};;
        String[] result = this.instance.getColNames();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getValues method, of class DBResultRow.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        
        Object[] expResult = {10, 20, 30, 40};
        Object[] result = instance.getValues();
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of getValueAt method, of class DBResultRow.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        
        int index = 1;
        Integer expResult = 20;
        Object result = instance.getValueAt(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getValueByName method, of class DBResultRow.
     */
    @Test
    public void testGetValueByName() {
        System.out.println("getValueByName");
        String name = "Val3";
        
        Object expResult = 30;
        Object result = this.instance.getValueByName(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of Count method, of class DBResultRow.
     */
    @Test
    public void testCount() {
        System.out.println("Count");
        
        int expResult = 4;
        int result = instance.Count();
        assertEquals(expResult, result);
    }

    /**
     * Test of setValues method, of class DBResultRow.
     */
    @Test
    public void testSetValues() {
        System.out.println("setValues");
        Integer[] values = {110, 120, 130, 140, 150}; 
        String[] names = {"XVal1","XVal2","XVal3","XVal4","XVal5"};
        
        DBResultRow xInstance = new DBResultRow();
        xInstance.setValues(names, values);
        
        int result = xInstance.Count();
        int expResult = 5;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getResultRowsFromResultSet method, of class DBResultRow.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetResultRowsFromResultSet() throws Exception {
        System.out.println("getResultRowsFromResultSet");
        
        URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
        DatabaseHandler dbh;
        
        dbh = new DatabaseHandler(new MSAccessDatabase(filePath));
        String query = "SELECT TOP 100 * FROM Patient";
        List<DBResultRow> result = dbh.fireSelectQuery(query, null);
        
        int resultCount = 0;
        int unexpResult = 0;
        resultCount = resultCount + result.size();

        assertNotNull(result);
        assertNotSame(unexpResult, resultCount);
    }

    
}
