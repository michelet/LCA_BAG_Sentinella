/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.export;

import ch.bfh.lca._15h.library.IResultRow;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Cédric Michelet
 */
public class ExportToExcel {

    public static void exportToExcel(IResultRow[] rows, String excelFilePath) throws FileNotFoundException, IOException {
        Workbook wb = new HSSFWorkbook();

        //create new sheet
        Sheet sheet1 = wb.createSheet("Sentinella");
        Row row;
        Cell cell;
        
        for(int i=0; i<rows.length; i++) {
            //if first line, write col names
            if(i==0) {
                row = sheet1.createRow((short)0);
                
                for(int j=0; j<rows[i].getColNames().length; j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(rows[i].getColNames()[j]);
                }
            }
            
            row = sheet1.createRow((short)(i+1));
            
            for(int j=0; j<rows[i].getColNames().length; j++) {
                cell = row.createCell(j);
                cell.setCellValue(rows[i].getValueAt(j).toString());
            }
        }
        
        //write to the file
        FileOutputStream fileOut = new FileOutputStream(excelFilePath);
        wb.write(fileOut);
        fileOut.close();
    }
}
