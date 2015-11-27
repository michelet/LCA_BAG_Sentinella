/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.statisticServices;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.Database.DBResultRow;
import ch.bfh.lca._15h.library.GenericResultRow;
import ch.bfh.lca._15h.library.filter.CriteriaAge;
import ch.bfh.lca._15h.library.filter.OrCriteria;
import ch.bfh.lca._15h.library.model.Criteria;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.Frequency;

/**
 *
 * @author Stefan
 */
public class StatisticHandler {
      
    
    /**
     * Gets a list of GenericResultRows which conatins the the Frequencies of genders (male & female) by age groups
     * @param dataSource Have to be a Doctor Patient Contact dataSource
     * @return Calculated List of Population by age, which had an doctor contact.
     */
    public List<GenericResultRow> getResultRowByAgeGroups(DataSource dataSource) {
        
        List<GenericResultRow> newList = new ArrayList<>();
        
        for(int i=1; i<=100; i=i+5) {
            Criteria ageCriterias =  this.generateAgeFilter(i, i+5);
            DataSource filteredSource = ageCriterias.meetCrieria(dataSource);
            newList.add(this.genderFrequency(i + " bis " + Integer.toString(i+5), filteredSource));
        }
        
        return newList;
    }

    /**
     * Gets a list of GenericResultRows which conatins the the Frequencies of genders (male & female) by Age
     * @param dataSource Have to be a Doctor Patient Contact dataSource
     * @return Calculated List of Population by age, which had an doctor contact.
     */
    public List<GenericResultRow> getResultRowByAge(DataSource dataSource) {
        
        List<GenericResultRow> newList = new ArrayList<>();
        
        for(int i=0; i<=100; i++) {
            CriteriaAge criteria = new CriteriaAge(i);
            DataSource filteredSource = criteria.meetCrieria(dataSource);
            newList.add(this.genderFrequency(i + " Jahr(e)", filteredSource));
        }
        return newList;
    }    
    
    /**
     * Creates an Or-Criteria for Age Groups. Age from (minValue) to (maxValue)
     * @param minValue Integer which represents the lowest age within the age group
     * @param maxValue Integer which represents the highest age within the age group
     * @return an OrCriteria to get the Entries within the Age group
     */
    private OrCriteria generateAgeFilter(int minValue, int maxValue){
        OrCriteria orCriteria = new OrCriteria();
        
        for(int age=minValue; age<maxValue; age++) {
            orCriteria.addOrCriteria(new CriteriaAge(age));
        }
        
        return orCriteria;
    }
    
    /**
     * Calculates the frequencies of genders within an dataSource. The result is given in a GenericResultRow
     * @param name Name of Age (group)
     * @param dataSource Source with datas of DPCs
     * @return GenericResultRow (Contains in field 0 the description of the Age (group),
     *   in field 1 the amount of male within that group
     *   in field 2 the amount of females within the group.
     */
    private GenericResultRow genderFrequency(String name, DataSource dataSource) {
        Frequency freqGender = new Frequency();
        GenericResultRow grr = new DBResultRow();

        for(DoctorPatientContact dpc : dataSource) {
            freqGender.addValue(dpc.getPatSex().getValue());
        }
        
        String[] colName = {"AgeGroup", "Male", "Fenale"};
        Object[] values = {name, freqGender.getCount(0), freqGender.getCount(1)};
        
        grr.setValues(colName, values);
        return grr;
    }
    
}
