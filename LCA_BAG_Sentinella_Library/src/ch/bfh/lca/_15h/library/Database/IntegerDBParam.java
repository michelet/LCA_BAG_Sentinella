package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * IntegerDBParam has to add integer parameters to a prepared sql statmenet. 
 * @author Stefan Schöpfer
 * @see ch.bfh.lca._15h.library.Database.ADBParameter
 * @see ch.bfh.lca._15h.library.Database.IDBParameter
 */
public class IntegerDBParam extends ADBParameter<Integer> implements IDBParameter {

    /**
     * Constructor method. Sets the Index of the paramter (which starts with 1) and the value as integer.
     * @param index - int (Starts at 1)
     * @param value - Integer
     */
    public IntegerDBParam(int index, Integer value) {
        super(index, value);
    }

    @Override
    public PreparedStatement addParamToStatement(PreparedStatement statement) throws SQLException{
        PreparedStatement resultStatement = statement;
        
        resultStatement.setInt(this.getIndex(), this.getValue());
        
        return resultStatement;
    }
}
