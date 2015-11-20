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
        
        
        this.addTranslations(TRANSLATION_LANGUAGE.FR, "ch/bfh/lca/_15h/library/translation/fr.properties");
        this.addTranslations(TRANSLATION_LANGUAGE.DE, "ch/bfh/lca/_15h/library/translation/de.properties");
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
    
    /**
     * Allow to load translations properties file
     * @param language Language of the properties file
     * @param translationsPath internal path of the properties file
     * @throws IOException 
     */
    public void addTranslations(TRANSLATION_LANGUAGE language, String translationsPath) throws IOException {
        //load translation
        Properties translations = new Properties();
        
        InputStream input = null;
        
        URL url = Translation.class.getClassLoader().getResource(translationsPath);
        input = url.openStream();
	translations.load(input);
        
        //merge with existings loaded properties
        switch(language) {
            case FR:
                frTranslations.putAll(translations);
                break;
            case DE:
                deTranslations.putAll(translations); //
                break;
        } 
    }
    
    /**
     * Return the translation in a specific language for a specifiy key
     * @param language
     * @param key
     * @return 
     */
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
