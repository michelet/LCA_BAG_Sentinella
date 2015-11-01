package ch.bfh.lca._15h.library.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * DatabaseHandler class helps you to use database connections easily.
 * @author Stefan Schöpfer
 */
public class DatabaseHandler {

    private final IDatabase database;
    private PreparedStatement sqlStatement;

    /**
     * Creates the DatabaseHandler object. What would a DatabaseHandler be without a assigned Database. For that reason
     * we need to have an IDatabase object as paramter.
     * @param database - IDatabase as assigned database
     */
    public DatabaseHandler(IDatabase database) {
        this.database = database;
    }
    
    /**
     * Fires a query against the database. executeQuery() to execute the command. The result of the query will be tranformed to a collection
     * of DBResultRow objects.
     * 
     * 
     * @param query - SQL query
     * @param params - array of IDBParameter objects. When ther are no parameter used, you can use null instead.
     * @return
     * @throws SQLException
     */
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
        
    /**
     * Returns the assigned IDatabase object.
     * @return IDatabase
     */
    public IDatabase getDatabase() {
        return this.database;
    }
   
}
