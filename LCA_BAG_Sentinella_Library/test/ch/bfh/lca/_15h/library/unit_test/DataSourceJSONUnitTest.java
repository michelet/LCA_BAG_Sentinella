/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.unit_test;

import ch.bfh.lca._15h.library.impl.DataSourceCSV;
import ch.bfh.lca._15h.library.impl.DataSourceJSON;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private DataSourceCSV getDataSourceCSV() {
        URL csvURLPatients = DataSourceCSVUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.csv");
        URL csvURLActivities = DataSourceCSVUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-leistung.csv");
         
        return new DataSourceCSV(csvURLPatients.getPath(), csvURLActivities.getPath());
    }
    
    private DataSourceJSON getDataSourceJSON() {
        URL jsonURL = DataSourceJSONUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.json");

        return new DataSourceJSON(jsonURL.getPath());
    }
    
    
    @Test
    public void test_convertToBAGJson() {
        DataSourceCSV ds = this.getDataSourceCSV();

        try {
            String json = DataSourceJSON.toBAGJSON(ds);
            InputStream is = DataSourceJSONUnitTest.class.getResourceAsStream("/ch/bfh/lca/_15h/library/unit_test/sample-patient.json");

            if (is == null) {
                fail("DataSourceJSON cannot open sample result");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();
            is.close();

            assertEquals("DataSourceJSON convert to JSON", sb.toString(), json);
        } catch (Exception ex) {
            fail("DataSourceJSON exception: " + ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void test_readCount() {
        DataSourceJSON ds = this.getDataSourceJSON();

        try {
            assertEquals("DataSourceJSON count patients",1,ds.countPatients());
        } catch (Exception ex) {
            fail("DataSourceJSON exception: " + ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void test_readAttributes() {
        DataSourceJSON ds = this.getDataSourceJSON();

        try {
            assertEquals("DataSourceJSON read 1st patient patID","1",ds.getPatient(0).getPatID());
        } catch (Exception ex) {
            fail("DataSourceJSON exception: " + ex.getLocalizedMessage());
        }
    }
    
    @Test
    public void test_readActivities() {
        DataSourceJSON ds = this.getDataSourceJSON();

        try {
            assertEquals("DataSourceJSON read 1st patient 1st activity","1",ds.getPatient(0).getActivities().get(0).getPatNumber());
        } catch (Exception ex) {
            fail("DataSourceJSON exception: " + ex.getLocalizedMessage());
        }
    }
}
