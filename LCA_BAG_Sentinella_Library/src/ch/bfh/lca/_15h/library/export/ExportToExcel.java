/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.export;

import ch.bfh.lca._15h.library.IResultRow;
import ch.bfh.lca._15h.library.translation.Translation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 *
 * @author Cédric Michelet
 */
public class ExportToExcel {

    public static void exportToExcel(Translation.TRANSLATION_LANGUAGE language, String sheetTitle, String tableTitle, IResultRow[] rows, String excelFilePath) throws FileNotFoundException, IOException {
        //Workbook wb = new HSSFWorkbook(); //xls
        Workbook wb = new SXSSFWorkbook(); //xlsx
        //create new sheet
        Sheet sheet1 = wb.createSheet(sheetTitle);
        Row row;
        Cell cell;
        String translation;

        int rowIndex = 0;

        //add title
        row = sheet1.createRow((short) rowIndex);
        cell = row.createCell(0);
        cell.setCellValue(tableTitle);
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 24);
        //font.setFontName("Courier New");
        style.setFont(font);
        cell.setCellStyle(style);

        //add rows
        for (int i = 0; i < rows.length; i++) {
            //if first line, write col names
            if (i == 0) {
                row = sheet1.createRow((short) rowIndex + 2);

                for (int j = 0; j < rows[i].getColNames().length; j++) {
                    cell = row.createCell(j);

                    //look for translation
                    translation = Translation.getForKey(language, rows[i].getColNames()[j]);
                    if (translation == null) {
                        translation = rows[i].getColNames()[j]; //if doesn't found a translation for the column take name of col
                    }
                    cell.setCellValue(translation);
                }
            }

            row = sheet1.createRow((short) (rowIndex + i + 3));

            for (int j = 0; j < rows[i].getColNames().length; j++) {
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
