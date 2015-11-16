/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.io.File;
import java.net.URL;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Stefan
 */
public class DatabaseHandlerTest {
    
    MSAccessDatabase instance;
    
    public DatabaseHandlerTest() {
        URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
        this.instance = new MSAccessDatabase(filePath);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    
    // Lösche Datei
        URL dbPath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database");
        String filePath = dbPath.getPath() + "/sentidb.db";
        
        File f = new File(filePath);
        f.delete();
    }

    /**
     * Test of fireSelectQuery method, of class DatabaseHandler.
     * @throws java.lang.Exception
     */
    @Test
    public void testFireSelectQuery() throws Exception {
        System.out.println("fireSelectQuery");
        String query = "SELECT * FROM Patient WHERE NOT PatID = ?";
        IntegerDBParam[] params = new IntegerDBParam[1];
        params[0] = new IntegerDBParam(1, 100);
        DatabaseHandler handler = new DatabaseHandler(this.instance);
        
        int expResult = 99;
        int result;
        List<DBResultRow> resultRows = handler.fireSelectQuery(query, params);
        
        result = resultRows.size();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getDatabase method, of class DatabaseHandler.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");

        DatabaseHandler handler = new DatabaseHandler(this.instance);
        IDatabase result = handler.getDatabase();
        IDatabase expResult = this.instance;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of fireIUDQuery method, of class DatabaseHandler.
     */
    @Test
    public void testFireIUDQuery() throws Exception {
        System.out.println("fireIUDQuery");
        
        URL dbPath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database");
        String filePath = dbPath.getPath() + "/sentidb.db";
        
        SQLLiteDatabase sqli = new SQLLiteDatabase(filePath);
        DatabaseHandler sqliHandler = new DatabaseHandler(sqli);
     
        String query = "CREATE TABLE SENTINELLARECORD " +
                   "(ID INT PRIMARY KEY     NOT NULL," +
                   " patNumber      TEXT, " + 
                   " patBirthdate   LONG, " + 
                   " patSex         INT, " + 
                   " patDiagnosis   TEXT," +
                   " dpcDate        INT)";
        
        int res = sqliHandler.fireIUDQuery(query, null);
        
        query = "INSERT INTO SENTINELLARECORD (ID, patNumber, patBirthdate, patSex, patDiagnosis, dpcDate) VALUES (0, '10001', 64546, 1, 'KA', 21314)";
        res = sqliHandler.fireIUDQuery(query, null);
        System.out.println("MIAU: " + res);
        
        
        query = "SELECT * FROM SENTINELLARECORD";
        List<DBResultRow> resultRows = sqliHandler.fireSelectQuery(query, null);
        int result = resultRows.size();
        int expRes = 1;
        
        assertEquals(expRes, result);
        
    }
    
}
