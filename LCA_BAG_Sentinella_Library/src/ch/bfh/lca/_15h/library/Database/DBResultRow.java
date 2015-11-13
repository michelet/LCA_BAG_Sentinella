package ch.bfh.lca._15h.library.Database;

import ch.bfh.lca._15h.library.GenericResultRow;
import ch.bfh.lca._15h.library.model.ResultRow;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DBResultRow Object is for an generic use of result from a SQL query
 * @author Stefan Schöpfer
 */
public class DBResultRow extends GenericResultRow implements ResultRow {
  
    /**
     * Returns a List of ResultRow objects extracted from a resultSet
     * @param resultSet - java.sql.ResultSet
     * @return Collection of ResultRow objectss
     * @throws SQLException
     */
    public static List<DBResultRow> getResultRowsFromResultSet(ResultSet resultSet) throws SQLException {
        List<DBResultRow> resultList = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
                
        int rowSize = metaData.getColumnCount();
        String[] names = DBResultRow.getNames(metaData, rowSize);
        
        while(resultSet.next()) {
            Object[] values = DBResultRow.getValues(resultSet, rowSize);
            DBResultRow resultRow = new DBResultRow();
            resultRow.setValues(names, values);
            resultList.add(resultRow);
        }
        
        
        return resultList;   
    }
    
    private static String[] getNames(ResultSetMetaData metaData, int size) throws SQLException {
        String[] names = new String[size];
        
        for(int i=0; i<size; i++) {
          names[i] = metaData.getColumnName(i+1);
        }
        
        return names;
    }
    
    private static Object[] getValues(ResultSet resultSet, int size) throws SQLException {
        Object[] values = new Object[size];
        
        for(int i=0; i<size; i++) {
          values[i] = resultSet.getObject(i+1);
        }
        
        return values;
    }    
}
