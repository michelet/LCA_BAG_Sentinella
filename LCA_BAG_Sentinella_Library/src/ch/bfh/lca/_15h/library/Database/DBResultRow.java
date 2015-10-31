package ch.bfh.lca._15h.library.Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBResultRow implements IResultRow {

    private String[] colNames;
    private Object[] values;
    
    
    @Override
    public String[] getColNames() {
        if (this.colNames == null) {
            return null;
        }
        else {
            return this.colNames;
        }
    }

    @Override
    public Object[] getValues() {
        if (this.values == null) {
            return null;
        }
        else {
            return this.values;
        }
    }

    @Override
    public Object getValueAt(int index) {
        if (index > (this.values.length -1)) {
            throw new IndexOutOfBoundsException();
        }else {
            return this.getValues()[index];
        }
    }

    @Override
    public Object getValueByName(String name) {
        int index = 0;
        for(String str : this.getColNames()) {
            if (str.toLowerCase().equals(name.toLowerCase())) {
                break;
            }
            index++;
        }
        
        return this.getValueAt(index);
    }

    @Override
    public int Count() {
        if (this.getValues() == null) {
            return 0;
        } else {
            return this.getValues().length; 
        }
    }

    @Override
    public void setValues(String[] names, Object[] values) {
        if (names.length != values.length) {
           throw new IllegalArgumentException("Lenght of both array must be equal");
        } else {
            this.colNames = names;
            this.values = values;
        }
    }
    
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
