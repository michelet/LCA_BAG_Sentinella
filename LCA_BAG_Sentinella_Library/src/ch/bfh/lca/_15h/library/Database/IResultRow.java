package ch.bfh.lca._15h.library.Database;

public interface IResultRow {

    public String[] getColNames();

    public Object[] getValues();

    public Object getValueAt(int index);

    public Object getValueByName(String name);

    public int Count();
    
     /**
     * Adds names and Values to the Object. The lenght of the name array have to be equals to the lenght of the values array
     * @param names Array of strings
     * @param values Array of objects
     * @throws IllegalArgumentException Is thrown, when the to Array lenght didn't match
     */
    public void setValues(String[] names, Object[] values);

}