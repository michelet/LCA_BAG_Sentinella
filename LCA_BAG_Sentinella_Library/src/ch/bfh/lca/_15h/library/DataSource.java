/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.Patient;
import ch.bfh.lca._15h.library.model.Activity;
import java.util.ArrayList;

/**
 *
 * @author micheletc
 */
public interface DataSource {
    public ArrayList<Patient> parsePatients() throws Exception;
    public ArrayList<Activity>  parseActivities() throws Exception;
}
