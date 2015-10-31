package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IDBParameter {

    public PreparedStatement addParamToStatement(PreparedStatement statement) throws SQLException;
}
 