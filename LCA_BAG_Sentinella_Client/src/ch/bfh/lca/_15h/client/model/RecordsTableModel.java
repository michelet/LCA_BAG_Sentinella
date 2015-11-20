/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.client.model;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Cédric Michelet
 */
public class RecordsTableModel extends AbstractTableModel {

    private DataSource dataSource = null;

    /**
     * Constructor
     *
     * @param dataSource
     */
    public RecordsTableModel(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int getRowCount() {
        if(dataSource == null) return 0;
        try {
            return dataSource.countDoctorPatientContacts();
        } catch (Exception ex) {
            //@TODO show/log error
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(dataSource == null) return null;
        DoctorPatientContact dpc;
        try {
            dpc = dataSource.getDoctorPatientContact(rowIndex);

            switch (columnIndex) {
                case 0:
                    return dpc.getPatID();
                case 1:
                    return dpc.getPatSex();
                case 2:
                    return dpc.getPatBirthdate();
                case 3:
                    return "?";
                case 4:
                    return dpc.getContactDate();
            }

            return "?";
        } catch (Exception ex) {
            //@TODO show/log error
            return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
                case 0:
                    return "PatID";
                case 1:
                    return "PatSex";
                case 2:
                    return "PatBirthdate";
                case 3:
                    return "PatDiagnosis";
                case 4:
                    return "ContactDate";
            }
        
        return "?";
    }
    
    public void setDataSource(DataSource datasource) {
        this.dataSource = datasource;
        this.fireTableDataChanged(); //call update on the table
    }
}
