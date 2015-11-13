package ch.bfh.lca._15h.library.model;

/**
 * The ResultRow interface is made to implements classes which gets several information in one row. such as database results or
 demilited files.
 * 
 * The target ist to have one commen one to get informations from different datasources.
 * 
 * 
 * @author Stefan Schöpfer
 */
public interface ResultRow {

    /**
     * Returns all the column names which are stored within an ResultRow Object.
     * @return array of String
     */
    public String[] getColNames();

    /**
     * Returns all the values which are stored within an ResultRow Object. It's important,
     * a value is at the same position as the assigned name.
     * @return
     */
    public Object[] getValues();

    /**
     * Returns a value at a speciic position.
     * @param index int
     * @return Object value
     */
    public Object getValueAt(int index);

    /**
     * Returns the value which is at the same position like the given name.
     * @param name - String
     * @return Object value
     */
    public Object getValueByName(String name);

    /**
     * Returns the amount of elements stored within the object.
     * @return int.
     */
    public int Count();
    
     /**
     * Adds names and Values to the Object. The lenght of the name array have to be equals to the lenght of the values array
     * @param names Array of strings
     * @param values Array of objects
     * @throws IllegalArgumentException Is thrown, when the to Array lenght didn't match
     */
    public void setValues(String[] names, Object[] values);

}