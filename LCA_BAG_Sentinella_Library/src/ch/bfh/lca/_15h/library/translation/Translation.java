/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.translation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Class to handle translation between differents languages.
 * Translation are stored in properties files.
 * @author Cédric Michelet
 */
public class Translation {

    /***
     * Enumaration of languages
     */
    public enum TRANSLATION_LANGUAGE {

        FR, DE, EN
    }
    
    /***
     * Store translations in French
     */
    Properties frTranslations;
    
    /***
     * Store translations in German
     */
    Properties deTranslations;
    
    /**
     * Store translations in English
     */
    Properties enTranslations;

    /**
     * Constructor as private because it's a singleton.
     */
    private Translation() throws IOException {
        //load translations
        frTranslations = new Properties();
        deTranslations = new Properties();
        enTranslations = new Properties();
        
        this.addTranslations(TRANSLATION_LANGUAGE.FR, "ch/bfh/lca/_15h/library/translation/fr.properties");
        this.addTranslations(TRANSLATION_LANGUAGE.DE, "ch/bfh/lca/_15h/library/translation/de.properties");
        this.addTranslations(TRANSLATION_LANGUAGE.EN, "ch/bfh/lca/_15h/library/translation/en.properties");
    }

    /**
     * Instance of singleton
     */
    private static Translation INSTANCE = null;

    /**
     * Getter of singleton
     * @return 
     * @throws java.io.IOException
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
        
        InputStream input;
        
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
            case EN:
                enTranslations.putAll(translations); //
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
        if(language == TRANSLATION_LANGUAGE.DE) return deTranslations.getProperty(key);
        
        return enTranslations.getProperty(key);
    }
    
    /**
     * Return the translation in a specific language for a specifiy key
     * @param language
     * @param key
     * @return 
     */
    public static String getForKey(TRANSLATION_LANGUAGE language, String key) {
        try {
            return Translation.getInstance().getTranslationForKey(language, key);
        } catch(Exception e) {
            return null;
        }
    }
}
