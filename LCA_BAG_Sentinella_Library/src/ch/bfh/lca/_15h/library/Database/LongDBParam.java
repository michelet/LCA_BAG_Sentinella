package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * IntegerDBParam has to add integer parameters to a prepared sql statmenet. 
 * @author Stefan Schöpfer
 * @see ch.bfh.lca._15h.library.Database.ADBParameter
 * @see ch.bfh.lca._15h.library.Database.IDBParameter
 */
public class LongDBParam extends ADBParameter<Long> implements IDBParameter {

    /**
     * Constructor method. Sets the Index of the paramter (which starts with 1) and the value as integer.
     * @param index - int (Starts at 1)
     * @param value - Integer
     */
    public LongDBParam(int index, Long value) {
        super(index, value);
    }

    @Override
    public PreparedStatement addParamToStatement(PreparedStatement statement) throws SQLException{
        PreparedStatement resultStatement = statement;
        
        resultStatement.setLong(this.getIndex(), this.getValue());
        
        return resultStatement;
    }
}
