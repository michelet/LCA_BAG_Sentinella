/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.filter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.impl.DataSourceJSON;
import ch.bfh.lca._15h.library.model.Criteria;
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
public class OrCriteriaTest {
    
    DataSourceJSON datasource;
    public OrCriteriaTest() {
    }
    
    @Before
    public void setUp() {
        URL filePath = this.getClass().getClassLoader().getResource("ch/bfh/lca/_15h/library/filter/senti.json");
        this.datasource = new DataSourceJSON(filePath.getPath());        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addAndCriteria method, of class OrCriteria.
     */
    @Test
    public void testAddORCriteria() {
        
        Criteria criteria1 = new CriteriaAge(31);
        Criteria criteria2 = new CriteriaAge(30);
        
        OrCriteria instance = new OrCriteria();
        instance.addOrCriteria(criteria1);
        instance.addOrCriteria(criteria2);
        
        int expResult = 2;
        assertEquals(expResult, instance.CountCriterias());
    }    
    
    /**
     * Test of meetCrieria method, of class OrCriteria.
     */
    @Test
    public void testMeetCrieria() {

        System.out.println("meetCrieria");
        DataSource dataSource = this.datasource;
        
        Criteria criteria1 = new CriteriaAge(31);
        Criteria criteria2 = new CriteriaAge(30);
        
        OrCriteria instance = new OrCriteria();
        instance.addOrCriteria(criteria1);
        instance.addOrCriteria(criteria2);
        
        DataSource dsResult = instance.meetCrieria(dataSource);
        int result = 0;
        try {
            result = dsResult.countDoctorPatientContacts();
        } catch (Exception ex) {
            Logger.getLogger(OrCriteriaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 2;
        
        assertEquals(expResult, result);

    }



    
}
