/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

/**
 * Absract Class for Database parameters. Uses with generic types.
 * @author Stefan Schöpfer
 * @param <T> generic type
 */
public abstract class ADBParameter<T> {
    
    private final int index;
    private final T value;
    
    /**
     * Constructor of an ADBParamter instance. You have to set the index and the value.
     * @param index index of paramter within the sql query. starts with 1
     * @param value value of parameter
     */
    public ADBParameter(int index, T value) {
        this.index = index;
        this.value = value; 
    }
    
    /**
     * Returns the index of the database parameter. IMPORTANT: database parameter indexes start with position 1 and not 0 like in an array.
     * @return int - the index of the parameter
     */
    public int getIndex() {
        return this.index;
    }
    
    /** 
     * Returns the Value of the ADBParameter object. The value can be of any type as the Object is defined. The value represent the value which will be places into the SQL query.
     * @return T - value of generic type 
     */
    public T getValue() {
        return this.value;
        
    }
}
