/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.export;

import ch.bfh.lca._15h.library.Database.DBResultRow;
import ch.bfh.lca._15h.library.IResultRow;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cédric Michelet
 */
public class ExportToExcelTest {
    
    public ExportToExcelTest() {
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

    @Test
     public void test_writeExcel() {
        
         
        try {
            IResultRow[] rows = new IResultRow[5];
            DBResultRow dbrr;
            String[] colNames = {"Col0","Col1","Col2"};
            Object[] values;
            
            for(int i=0; i<5; i++) {
                dbrr = new DBResultRow();
                values = new Object[3];
                values[0] = "line " + i + " col 0";
                values[1] = "line " + i + " col 1";
                values[2] = "line " + i + " col 2";
                
                dbrr.setValues(colNames, values);
                rows[i] = dbrr;
            }
            
            String path = "c:\\output.xls";
            
            ExportToExcel.exportToExcel(rows, path);
            
            //assertEquals("DataSourceCSV read 1st line patID","1",ds.getDoctorPatientContact(0).getPatID());
            //assertEquals("DataSourceCSV read 1st line patSalutation","Herrn",ds.getDoctorPatientContact(0).getPatSalutation());
            //assertEquals("DataSourceCSV read 1st line longReserver1","6",ds.getDoctorPatientContact(0).getLongReserve1());
        } catch (Exception ex) {
            fail("ExportToExcel exception: " + ex.getLocalizedMessage());
        }
     }
}
