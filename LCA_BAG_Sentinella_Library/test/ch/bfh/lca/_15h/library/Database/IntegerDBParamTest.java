/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.net.URL;
import java.sql.PreparedStatement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class IntegerDBParamTest {
    
    MSAccessDatabase database;
    
    public IntegerDBParamTest() {
        URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
        this.database = new MSAccessDatabase(filePath);        
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
    /**
     * Test of addParamToStatement method, of class IntegerDBParam.
     */
    @Test
    public void testAddParamToStatement() throws Exception {
               
        System.out.println("addParamToStatement");
        String query = "SELECT * FROM Patient WHERE PatID = ?";
        
        this.database.openConnection();
        PreparedStatement stmt = this.database.getConnection().prepareStatement(query);
        
        IDBParameter[] params = new IDBParameter[1];
        params[0] = new IntegerDBParam(1, 100);
        
        PreparedStatement result = params[0].addParamToStatement(stmt);
        assertNotNull(result);
        this.database.closeConnection();
    }
    
}
