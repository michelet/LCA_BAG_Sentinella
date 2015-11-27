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
 * ExportHandler
 * @author Stefan
 * 
 * Class to Export Sentinalla datas as a table to an Excel file or as Graph to a JPEG.
 */
public class ExportHandler {
    
    /**
     * Types of Export which are possible
     */
    public enum ExportType {
        EXCEL_BY_AGEGROUP,
        EXCEL_BY_AGE,
        GRAPH_BY_AGEGROUP,
        GRAPH_BY_AGE,
    }
    
    DatabaseExportHandler databaseExportHandler;
    
    /**
     * Constructor for an ExportHandler Object. Sets the needed DatabaseExportHandler.
     * @param databaseExportHandler 
     */
    public ExportHandler(DatabaseExportHandler databaseExportHandler)
    {
        this.databaseExportHandler = databaseExportHandler;
    }
    
    
    /**
     * Creates an Excel file with the content of DPC happend in a specific year to a specific path. The Table will be by Age and the filtered
     * by the year the DPC had happend.
     * @param year
     * @param path
     * @throws IOException 
     */
    public void ExportToExcelByYearAndAge(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));
        
        this.writeToExcel(lines, year, path);
    }    
    
    /**
     * Creates an Excel file with the content of DPC happend in a specific year to a specific path. The Table will be by Age Groups and the filtered
     * by the year the DPC had happend.
     * @param year
     * @param path
     * @throws IOException 
     */
    public void ExportToExcelByYearAndAgeCategories(int year, String path) throws IOException {
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));
        
        this.writeToExcel(lines, year, path);
    }
    
    /**
     * Creates a popolatino Chart file as jpg By Age Group of Patient and Year in which the DPC happend.
     * @param year in which the DPC happend
     */       
    public void ExportToPopulationCharByAgeGroup(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));
        
        this.writeToChartFile(lines, year, path);

    }

    /**
     * Creates a popolatino Chart file as jpg By Age of Patient and Year in which the DPC happend.
     * @param year in which the DPC happend
     */       
    public void ExportToPopulationCharByAge(int year, String path) throws IOException {

        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));
        
        this.writeToChartFile(lines, year, path);

    }  
    
    /**
     * Returns the Chart By Age Group of Patient and Year in which the DPC happend.
     * @param year in which the DPC happend
     * @return JFreeChart
     */    
    public JFreeChart getChartByAgeGroup(int year){
        
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAgeGroups(this.getDataSourceByYear(year));        
        ChartHandler ch = new ChartHandler();
        JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
              
        return chart;
    } 
    
    /**
     * Returns the Chart By Age of Patient and Year in which the DPC happend.
     * @param year in which the DPC happend
     * @return JFreeChart
     */
    public JFreeChart getChartByAge(int year){
        
        StatisticHandler sh = new StatisticHandler();
        List<GenericResultRow> lines = sh.getResultRowByAge(this.getDataSourceByYear(year));        
        ChartHandler ch = new ChartHandler();
        JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
              
        return chart;
    }     
    
    /**
     * Gets the DataSource filtered by the Year in which the DPC happend.
     * @param year
     * @return
     * @throws Exception 
     */
    public DataSource getDataSource(int year) throws Exception {
        return this.databaseExportHandler.getElemntsByYear(year);
    }
    
    /**
     * Saves the Chart to a JPG on a specific Filename
     * @param chart JFreeChart Chart.
     * @param fileName Full Path of File
     * @param width Width of image
     * @param height height of image
     * @return
     * @throws IOException 
     */
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
    
    /**
     * Filter a DataSource by Year in which the DPC was made.
     * @param year int year you want to analyse
     * @return Filter4ed DataSource by Year in which the DPC was made.
     */
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
    
    /**
     * Generates from a List of GenericResultRows an Excelfile and Saves it to the given path.
     * @param lines List of GenericResultRow Objects
     * @param year Year for which you want to create the Excel file
     * @param path Path where you whish to save your Excel file
     */    
    private void writeToExcel(List<GenericResultRow> lines, int year, String path) {
        
        try {
            
            ExportToExcel.exportToExcel(TRANSLATION_LANGUAGE.DE, Integer.toString(year), "SentinellaExport", this.ResultRowListToArray(lines), path);
        
            } catch (Exception ex) {
                Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Generates from a List of GenericResultRows a ChartFile and Saves it to the given path.
     * Please ensure, that you try to create a *.jpg file
     * @param lines List of GenericResultRow Objects
     * @param year Year for which you want to create the Chart
     * @param path Path where you whish to save your Chart file
     */
    private void writeToChartFile(List<GenericResultRow> lines, int year, String path) {
        try {
                   
            ChartHandler ch = new ChartHandler();
            JFreeChart chart = ch.generatesPopulationChart(this.ResultRowListToArray(lines));
        
            this.saveChartToJPG(chart, path, 800, 1200);
        
        } catch (Exception ex) {
            Logger.getLogger(ExportHandler.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    /**
     * Converts the a List to a Array of GenericResultRows
     * @param lines
     * @return an Array of GenericResultRows
     */
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
