/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;

/**
 *
 * @author Stefan
 * @param <T>
 */
public abstract class ADBParameter<T> {
    
    private final int index;
    private final T value;
    
    public ADBParameter(int index, T value) {
        this.index = index;
        this.value = value; 
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public T getValue() {
        return this.value;
    }
}
