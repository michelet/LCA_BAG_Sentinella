/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class DoctorPatientContactTest {

    private final SimpleDateFormat DTEFORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public DoctorPatientContactTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setPatSex method, of class DoctorPatientContact.
     */
    @Test
    public void testSetPatSex() {
        System.out.println("setPatSex");

        DoctorPatientContact.ESex patSex = DoctorPatientContact.ESex.FEMALE;
        DoctorPatientContact instance = new DoctorPatientContact();
        instance.setPatSex(patSex);
        assertEquals(patSex, instance.getPatSex());

        int expResult = 1;
        assertEquals(expResult, instance.getPatSex().getValue());
    }

    /**
     * Test of setPatID method, of class DoctorPatientContact.
     */
    @Test
    public void testSetPatID() {
        System.out.println("setPatID");
        String patID = "123456";
        DoctorPatientContact instance = new DoctorPatientContact();
        instance.setPatID(patID);

        assertEquals(patID, instance.getPatID());
    }

    /**
     * Test of setPatBirthdate method, of class DoctorPatientContact.
     */
    @Test
    public void testSetPatBirthdate() {
        System.out.println("setPatBirthdate");
        Date patBirthdate;
        String aDate = "04.03.1984";

        try {
            patBirthdate = DTEFORMAT.parse(aDate);
        } catch (ParseException ex) {
            Logger.getLogger(DoctorPatientContactTest.class.getName()).log(Level.SEVERE, null, ex);
            patBirthdate = new Date();
        }

        DoctorPatientContact instance = new DoctorPatientContact();
        instance.setPatBirthdate(patBirthdate);

        String result = DTEFORMAT.format(instance.getPatBirthdate());

        assertEquals(aDate, result);
    }

    /**
     * Test of setDiagnosis method, of class DoctorPatientContact.
     */
    @Test
    public void testSetDiagnosis() {
        System.out.println("setDiagnosis");
        String[] patDiagnosis = {"N1", "N2"};
        DoctorPatientContact instance = new DoctorPatientContact();
        instance.setDiagnosis(patDiagnosis);

        int result = instance.getDiagnosis().length;
        int expResult = 2;

        assertEquals(expResult, result);
    }

    /**
     * Test of ObjectToDate of class DoctorPatientContact.
     */
    @Test
    public void testObjectToDate() {
        try {
            Date d = DoctorPatientContact.accessObjectToDate(new String("22451"));
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:SS");
            Date goal = sdf.parse("19.06.1961 01:00:00");
            assertEquals("DoctorPatientContact objectToDate",goal,d);
        } catch (Exception e) {
            fail("DoctorPatientContact objectToDate exception: " + e.getLocalizedMessage());
        }
    }
}
