/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.impl.DBDataSource;
import ch.bfh.lca._15h.library.Database.MSAccessDatabase;
import ch.bfh.lca._15h.library.impl.DataSourceJSON;
import java.io.File;
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
public class JsonDPCWriterTest {

    DataSource source = null;
    final URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/Database/Leistungen.accdb");
    
    public JsonDPCWriterTest() {
        try {
            this.source = new DBDataSource(new MSAccessDatabase(filePath));
            //this.source.getDPCs();
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
     * Test of createDPCJson method, of class JsonDPCWriter.
     */
    @Test
    public void testCreateDPCJson() throws Exception {
        System.out.println("createDPCJson");
        
        String homeFolder = System.getProperty("user.home");

        System.out.println(homeFolder + "\\newTest.json");
        
        //JsonDPCWriter instance = new JsonDPCWriter(homeFolder + "\\newTest.json");
        //instance.createDPCJson(this.source);
        DataSourceJSON.writeBAGJSONToFile(this.source, null, homeFolder + "\\newTest.json");
        
        File f = new File(homeFolder + "\\newTest.json");
        boolean result = false;
        if(f.exists() && !f.isDirectory()) { 
            result = true;
        }
        assertTrue(result);
       
    }
    
}
