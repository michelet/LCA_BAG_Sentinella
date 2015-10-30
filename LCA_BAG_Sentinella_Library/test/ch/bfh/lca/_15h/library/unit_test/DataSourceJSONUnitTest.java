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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testDataSourceJSON_convertToBAGJson() {
        URL csvURL = DataSourceJSONUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.csv");

        DataSourceCSV ds = new DataSourceCSV(csvURL.getPath(), null);

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
    public void testDataSourceJSON_readBAGJson() {
        URL jsonURL = DataSourceJSONUnitTest.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/unit_test/sample-patient.json");

        DataSourceJSON ds = new DataSourceJSON(jsonURL.getPath());

        try {
            assertEquals("DataSourceJSON count patients",1,ds.countPatients());
            assertEquals("DataSourceJSON read 1st patient patID","1",ds.getPatient(0).getPatID());
            //assertEquals("DataSourceJSON read 1st patient patSalutation","Herrn",ds.getPatient(0).getPatSalutation());
            //assertEquals("DataSourceJSON read 1st patient longReserver1","6",ds.getPatient(0).getLongReserve1());
        } catch (Exception ex) {
            fail("DataSourceJSON exception: " + ex.getLocalizedMessage());
        }
    }
}
