package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;

public class IntegerDBParam extends ADBParameter<Integer> implements IDBParameter {

    public void IntegerDBParam(int index, Integer value) {
    }

    @Override
    public IDBParameter addParamToStatement(PreparedStatement statement, IDBParameter param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
