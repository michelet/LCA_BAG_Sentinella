/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library;

import ch.bfh.lca._15h.library.model.ResultRow;

/**
 *
 * @author Stefan
 */
public class GenericResultRow implements ResultRow {

    private String[] colNames;
    private Object[] values;
     /**
     * Returns the names of the attributes which were catched from a SQL query.
     * @return Array of String within the names.
     */
    @Override
    public String[] getColNames() {
        if (this.colNames == null) {
            return null;
        }
        else {
            return this.colNames;
        }
    }

    /**
     * Returns the values of the attributes which were catched from a SQL query.
     * @return  Array of Object with the values of the SQL result stored
     */
    @Override
    public Object[] getValues() {
        if (this.values == null) {
            return null;
        }
        else {
            return this.values;
        }
    }

    /**
     * Returns one value of type object, which is stored at a specific place within the DBResultRow object.
     * @param index int - index of the value
     * @return
     */
    @Override
    public Object getValueAt(int index) {
        if (index > (this.values.length -1)) {
            throw new IndexOutOfBoundsException();
        }else {
            return this.getValues()[index];
        }
    }

    /**
     * Returns an value of the type object. Refered by the column name in which the value is stored.
     * @param name - String name of the attribute 
     * @return Object - value
     */
    @Override
    public Object getValueByName(String name) {
        int index = 0;
        for(String str : this.getColNames()) {
            if (str.toLowerCase().equals(name.toLowerCase())) {
                break;
            }
            index++;
        }
        
        return this.getValueAt(index);
    }

    /**
     * Returns the amount of attributes and values stored in the DBResultRow. A pair of attribute and value counts as one element. 
     * @return int - amount of pairs of attribute and value
     */
    @Override
    public int Count() {
        if (this.getValues() == null) {
            return 0;
        } else {
            return this.getValues().length; 
        }
    }

    /**
     * Sets the an collection of attribute pairs of name an values. Is used for the case, when there is no SQL ResultSet. Mostly for test reasons.
     * 
     * The lenght of both array have to be the same. Otherwise an IllegalArgumentException will be thrown.
     * @param names - array of strings
     * @param values - array of objects
     * @throws IllegalArgumentException
     */
    @Override
    public void setValues(String[] names, Object[] values) throws IllegalArgumentException {
        if (names.length != values.length) {
           throw new IllegalArgumentException("Lenght of both array must be equal");
        } else {
            this.colNames = names;
            this.values = values;
        }
    }
    
}
