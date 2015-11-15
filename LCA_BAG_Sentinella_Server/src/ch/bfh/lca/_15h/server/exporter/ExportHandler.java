/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.exporter;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.GenericResultRow;
import ch.bfh.lca._15h.library.export.ExportToExcel;
import ch.bfh.lca._15h.library.filter.CriteriaConsultYear;
import ch.bfh.lca._15h.library.translation.Translation;
import ch.bfh.lca._15h.library.translation.Translation.TRANSLATION_LANGUAGE;
import ch.bfh.lca._15h.server.statisticServices.StatisticHandler;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stefan
 */
public class ExportHandler {
    
    public enum ExportType {
        EXCEL_BY_AGEGROUP,
        EXCEL_BY_AGE,
        GRAPH_BY_AGEGROUP,
        GRAPH_BY_AGE,
    }
    
    DatabaseExportHandler databaseExportHandler;
    
    public ExportHandler(DatabaseExportHandler databaseExportHandler)
    {
        this.databaseExportHandler = databaseExportHandler;
    }
    
    
    public void ExportToExcelByYearAndAgeCategories(int year, String path) throws IOException {
        CriteriaConsultYear ccy = new CriteriaConsultYear(year);
        DataSource dataSourceByYear;
        try {
            dataSourceByYear = ccy.meetCrieria(this.getDataSource(year));
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(dataSourceByYear);
        /*
        Translation.TRANSLATION_LANGUAGE language, String sheetTitle, String tableTitle, ResultRow[] rows, String excelFilePath
        */
        // ExportToExcel.exportToExcel(TRANSLATION_LANGUAGE.DE, "Mauz", lines, path);
        GenericResultRow[] arrResults = new GenericResultRow[lines.size()];
        int i = 0;
        for(GenericResultRow grr : lines) {
            arrResults[i] = grr;
            i++;
        }
        
        ExportToExcel.exportToExcel(TRANSLATION_LANGUAGE.DE, Integer.toString(year), "SentinellaExport", arrResults, path);
        
        } catch (Exception ex) {
            Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }
    
    private DataSource getDataSource(int year) throws Exception {
        return this.databaseExportHandler.getElemntsByYear(year);
    }
}
