/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.translation;

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
public class TranslationTest {

    public TranslationTest() {
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
    public void test_GetTranslation() {
        try {
            assertEquals("Translation (French)", "test FR", Translation.getForKey(Translation.TRANSLATION_LANGUAGE.FR, "test"));
            assertEquals("Translation (German)", "test DE", Translation.getForKey(Translation.TRANSLATION_LANGUAGE.DE, "test"));
            assertEquals("Translation (French) - unknow key", null, Translation.getForKey(Translation.TRANSLATION_LANGUAGE.FR, "xxxxxx_unknow_xxxxxxxxx"));
        } catch (Exception ex) {
            fail("Translation exception: " + ex.getLocalizedMessage());
        }
    }
}
