/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.unit_test;

import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author micheletc
 */
public class DataSourceJSONUnitTest {
    
    public DataSourceJSONUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testDataSourceJSONPatient() {
         //@TODO test to make
         
         /*URL csvURL = DataSourceJSONUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.csv");

         DataSourceCSV ds = new DataSourceCSV(csvURL.getPath(), null);
         
        try {
            assertEquals("DataSourceCSV count patients",1,ds.countPatients());
            assertEquals("DataSourceCSV read 1st line patID","1",ds.getPatient(0).getPatID());
            assertEquals("DataSourceCSV read 1st line patSalutation","Herrn",ds.getPatient(0).getPatSalutation());
            assertEquals("DataSourceCSV read 1st line longReserver1","6",ds.getPatient(0).getLongReserve1());
        } catch (Exception ex) {
            fail("DataSourceCSV exception: " + ex.getLocalizedMessage());
        }*/
     }
}