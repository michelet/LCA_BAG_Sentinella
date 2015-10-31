package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DatabaseHandler {

    private final IDatabase database;
    private PreparedStatement sqlStatement;

    public DatabaseHandler(IDatabase database) {
        this.database = database;
    }
    
    public List<DBResultRow> fireSelectQuery(String query, IDBParameter[] params) throws SQLException {
        this.getDatabase().openConnection();
        this.sqlStatement = this.getDatabase().getConnection().prepareStatement(query);
        
        if (params != null) {
            this.addParameters(params);
        }
        ResultSet resultSet = this.getStatement().executeQuery();
        List<DBResultRow> results =  DBResultRow.getResultRowsFromResultSet(resultSet);
        this.getDatabase().closeConnection();
        return results;
    }
    
    private PreparedStatement getStatement() {
        return this.sqlStatement;
    }
    
    private void addParameters(IDBParameter[] params) throws SQLException {
        for(IDBParameter param: params) {
            param.addParamToStatement(this.getStatement());
        }
    }
        
    public IDatabase getDatabase() {
        return this.database;
    }
   
}
