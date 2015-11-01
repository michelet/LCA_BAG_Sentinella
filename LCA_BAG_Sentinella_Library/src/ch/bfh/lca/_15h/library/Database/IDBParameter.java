package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * IDBParameters should be implemented by database parameters of any type. It's important to have a class which can add a parameter to a
 * java.sql.PreparedStatement. No matter what type that parameter should be.
 * 
 * @author Stefan Schöpfer
 */
public interface IDBParameter {

    /**
     * Should add a parameter to a java.sql.PreparedStatement object. You habe to code it for each type you want to use.
     * 
     * For example: in case you want to use a String parameter. You need to code statement.setString().
     * Or when you want to use a Integer parameter, then you need too code statement.setString().
     * 
     * The meaning is, you add the values you have stored within the parameter object to the java.sql.PreparedStatement.
     * @param statement java.sql.PreparedStatement 
     * @return java.sql.PreparedStatement - with one brand new parameter added.
     * @throws SQLException 
     * @see java.sql.PreparedStatement
     */
    public PreparedStatement addParamToStatement(PreparedStatement statement) throws SQLException;
}
 