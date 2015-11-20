/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.client.model;

import java.io.File;
import javax.swing.filechooser.FileFilter;


/**
 * Filter which accepts only CSV files.
 * @author Cédric Michelet
 */
public class FileFilterCSV extends FileFilter {

    /**
     * Determin if a file should be displayed or not.
     * @param f
     * @return 
     */
    @Override
    public boolean accept(File f) {
        if(this.getExtension(f) == null) return false;
        if(this.getExtension(f).equals("csv")) return true;
        return false;
    }

    /**
     * Name of the filter displayed in the dialog to the user
     * @return 
     */
    @Override
    public String getDescription() {
        return "CSV";
    }
    
    /**
     * Extract extension of a file
     * @param f
     * @return 
     */
    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
