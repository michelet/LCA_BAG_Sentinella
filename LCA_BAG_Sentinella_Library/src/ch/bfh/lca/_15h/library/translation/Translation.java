/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.translation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 *
 * @author Cédric Michelet
 */
public class Translation {

    public enum TRANSLATION_LANGUAGE {

        FR, DE
    }
    
    Properties frTranslations;
    Properties deTranslations;

    /**
     * Constructor as private because it's a singleton.
     */
    private Translation() throws IOException {
        //load translations
        frTranslations = new Properties();
        deTranslations = new Properties();
        
        InputStream input = null;
        
        URL url = Translation.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/translation/fr.properties");
        input = url.openStream();
	frTranslations.load(input);
        
        url = Translation.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/translation/de.properties");
        input = url.openStream();
	deTranslations.load(input);
    }

    /**
     * Instance of singleton
     */
    private static Translation INSTANCE = null;

    /**
     * Getter of singleton
     */
    public static Translation getInstance() throws IOException {
        if(INSTANCE == null)
            INSTANCE = new Translation();
        
        return INSTANCE;
    }
    
    public String getTranslationForKey(TRANSLATION_LANGUAGE language, String key) {
        if(language == TRANSLATION_LANGUAGE.FR) return frTranslations.getProperty(key);
        
        return deTranslations.getProperty(key);
    }
    
    /**
     * Return the translation in a specific language for a specifiy key
     * @param language
     * @param key
     * @return 
     */
    public static String getForKey(TRANSLATION_LANGUAGE language, String key) throws IOException {
        return Translation.getInstance().getTranslationForKey(language, key);
    }
}
