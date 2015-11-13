/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.library.export;

import ch.bfh.lca._15h.library.IResultRow;
import ch.bfh.lca._15h.library.translation.Translation;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFTable;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;


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

        wb.close();
    }

    public static void exportToAgePyramid(Translation.TRANSLATION_LANGUAGE language, IResultRow[] rows, String excelFilePath) throws FileNotFoundException, IOException, InvalidFormatException {
        //open template
        URL url = Translation.class.getClassLoader().getResource("ch/bfh/lca/_15h/library/export/template/alter-pyramide-v2.xlsx");
        //Workbook wb = WorkbookFactory.create(new File(url.getPath()));
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(url.getPath()))); 
        
        Sheet sheet = wb.getSheetAt(0);

        //http://www.programming-free.com/2012/12/create-charts-in-excel-using-java.html
        //https://poi.apache.org/spreadsheet/quick-guide.html#NewWorkbook
        Row row;
        Cell cell;

        for (int i = 0; i < 20; i++) {
            row = sheet.getRow(i + 1);
            if (row == null) {
                row = sheet.createRow(i + 1);
            }

            for (int j = 0; j < 3; j++) {
                cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }

                switch(j) {
                    case 0:
                        cell.setCellValue(i);
                        break;
                    case 1:
                        cell.setCellValue(i * j*-1);
                        break;
                    case 2:
                        cell.setCellValue(i * j);
                        break;
                }
            }
        }

        //redefine data range
        //http://thinktibits.blogspot.ch/2014/09/Excel-Insert-Format-Table-Apache-POI-Example.html
        XSSFSheet sheet1 = wb.getSheetAt(0); 
        XSSFTable table = sheet1.getTables().get(0);
        CTTable cttable = table.getCTTable();
        
        AreaReference my_data_range = new AreaReference(new CellReference(0, 0), new CellReference(20, 2));    
        /* Set Range to the Table */
        cttable.setRef(my_data_range.formatAsString());
       // cttable.setDisplayName("DATEN");      /* this is the display name of the table */
        //cttable.setName("test");    /* This maps to "displayName" attribute in &lt;table&gt;, OOXML */
        //cttable.setId(1L); //id attribute against table as long value
        
        /*
        //redefine data range
        Name rangeCell = wb.getName("DATEN");
        //Set new range for named range 
        //String reference = sheetName + "!$C$" + (deface + 1) + ":$C$" + (rowNum + deface);
        String reference = sheet.getSheetName() + "!$A$2:$C$20";
        //Assigns range value to named range
        rangeCell.setRefersToFormula(reference);
*/
        
        //write to the file
        FileOutputStream fileOut = new FileOutputStream(excelFilePath);
        wb.write(fileOut);
        fileOut.close();
        wb.close();
    }
}
