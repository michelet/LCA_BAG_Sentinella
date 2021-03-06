/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.unit_test;

import ch.bfh.lca._15h.library.impl.DataSourceCSV;
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
public class DataSourceCSVUnitTest {
    
    public DataSourceCSVUnitTest() {
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

    
     private DataSourceCSV getDataSource() {
         URL csvURLPatients = DataSourceCSVUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.csv");
         URL csvURLActivities = DataSourceCSVUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-leistung.csv");
         
         return new DataSourceCSV(csvURLPatients.getPath(), csvURLActivities.getPath());
     }
     
     @Test
     public void test_countPatient() {
         DataSourceCSV ds = this.getDataSource();
         
        try {
            assertEquals("DataSourceCSV count patients",1,ds.countDoctorPatientContacts());
        } catch (Exception ex) {
            fail("DataSourceCSV exception: " + ex.getLocalizedMessage());
        }
     }
    
     @Test
     public void test_readAttributes() {
         DataSourceCSV ds = this.getDataSource();
         
        try {
            assertEquals("DataSourceCSV read 1st line patID","1",ds.getDoctorPatientContact(0).getPatID());
            //assertEquals("DataSourceCSV read 1st line patSalutation","Herrn",ds.getDoctorPatientContact(0).getPatSalutation());
            //assertEquals("DataSourceCSV read 1st line longReserver1","6",ds.getDoctorPatientContact(0).getLongReserve1());
        } catch (Exception ex) {
            fail("DataSourceCSV exception: " + ex.getLocalizedMessage());
        }
     }
     
     @Test
     public void test_readDiagnosis() {
         DataSourceCSV ds = this.getDataSource();
         
        try {
            assertEquals("DataSourceCSV read 1st line 1st activity","A4",ds.getDoctorPatientContact(0).getDiagosisAtIndex(0));
        } catch (Exception ex) {
            fail("DataSourceCSV exception: " + ex.getLocalizedMessage());
        }
     }
}
