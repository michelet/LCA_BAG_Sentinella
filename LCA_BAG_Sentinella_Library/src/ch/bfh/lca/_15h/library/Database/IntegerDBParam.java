package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IntegerDBParam extends ADBParameter<Integer> implements IDBParameter {

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
