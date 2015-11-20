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
import ch.bfh.lca._15h.library.translation.Translation.TRANSLATION_LANGUAGE;
import ch.bfh.lca._15h.server.statisticServices.ChartHandler;
import ch.bfh.lca._15h.server.statisticServices.StatisticHandler;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

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

    public void ExportToExcelByYearAndAge(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));
        
        this.writeToExcel(lines, year, path);
    }    
    
    public void ExportToExcelByYearAndAgeCategories(int year, String path) throws IOException {
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));
        
        this.writeToExcel(lines, year, path);
    }
    
    public void ExportToPopulationCharByAgeGroup(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));
        
        this.writeToChartFile(lines, year, path);

    }

    public void ExportToPopulationCharByAge(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));
        
        this.writeToChartFile(lines, year, path);

    }    
    
    public JFreeChart getChartByAgeGroup(int year){
        
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));        
        ChartHandler ch = new ChartHandler();
        JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
              
        return chart;
    } 
    public JFreeChart getChartByAge(int year){
        
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));        
        ChartHandler ch = new ChartHandler();
        JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
              
        return chart;
    }     
    
    public DataSource getDataSource(int year) throws Exception {
        return this.databaseExportHandler.getElemntsByYear(year);
    }
    
    private String saveChartToJPG(final JFreeChart chart, String fileName, final int width, final int height) throws IOException {
        String result = null;
        
        if (chart != null) {
            if (fileName == null) {
                final String chartTitle = chart.getTitle().getText();
                if (chartTitle != null) {
                    fileName = chartTitle;
                } else {
                    fileName = "chart";
                }
            }
            result = fileName+".jpg";
            ChartUtilities.saveChartAsJPEG(new File(result), chart, width, height);
        }//else: input unavailable
        
        return result;
    }//saveChartToJPG()
    
    public DataSource getDataSourceByYear (int year) {
        try {
            CriteriaConsultYear ccy = new CriteriaConsultYear(year);
            DataSource dataSourceByYear;
            
            System.out.println(year);
            DataSource ds = this.getDataSource(year);
            dataSourceByYear = ccy.meetCrieria(ds);
            
            return dataSourceByYear;
        } catch (Exception ex) {
            Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    private void writeToExcel(List<GenericResultRow> lines, int year, String path) {
        
        try {
            
            ExportToExcel.exportToExcel(TRANSLATION_LANGUAGE.DE, Integer.toString(year), "SentinellaExport", this.ResultRowListToArray(lines), path);
        
            } catch (Exception ex) {
                Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void writeToChartFile(List<GenericResultRow> lines, int year, String path) {
        try {
                   
            ChartHandler ch = new ChartHandler();
            JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
        
            this.saveChartToJPG(chart, path, 800, 1200);
        
        } catch (Exception ex) {
            Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    private GenericResultRow[]  ResultRowListToArray(List<GenericResultRow> lines) {
        GenericResultRow[] arrResults = new GenericResultRow[lines.size()];
        int i = 0;
        
        for(GenericResultRow grr : lines) {
            arrResults[i] = grr;
            i++;
        }
        
        return arrResults;
    }
    

}
