package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;

public interface IDBParameter {

    public IDBParameter addParamToStatement(PreparedStatement statement, IDBParameter param);
}
