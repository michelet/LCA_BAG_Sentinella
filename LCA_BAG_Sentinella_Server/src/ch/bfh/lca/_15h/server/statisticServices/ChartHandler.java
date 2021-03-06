/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.statisticServices;

import ch.bfh.lca._15h.library.DataSource;
import ch.bfh.lca._15h.library.GenericResultRow;
import ch.bfh.lca._15h.library.model.DoctorPatientContact;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultKeyedValues2DDataset;

/**
 *
 * @author Stefan
 */
public class ChartHandler {
   /**
    * Empty constructor of class. 
    */
    public ChartHandler() {
       
   } 
   
    /**
     * Generates a JFreeChart object, which represents an age-pyramide diagramm.
     * @param arrResults an array of GenericResultRow objects. The following assignement has to be done:
     * Value at Position 0 = Categorie (such as Age 25 to 30)
     * Value at Position 1 = Count of males
     * Value at Position 2 = Count of females
     * @return an JFreeChart Object
     */
   public JFreeChart generatesPopulationChart(GenericResultRow[] arrResults){
       
       DefaultKeyedValues2DDataset data = new DefaultKeyedValues2DDataset();
       
       for(GenericResultRow resultRow : arrResults) {
           long maleCount = (long) resultRow.getValueAt(1);
           long femaleCount = (long) resultRow.getValueAt(2);
           String category = (String) resultRow.getValueAt(0);
           // Männer
           data.addValue(-maleCount, "Männer", category);
           // Frauem
           data.addValue(femaleCount, "Frauen", category);
        }
       
               // create the chart...
          JFreeChart chart = ChartFactory.createStackedBarChart(
            "Arzt-Patientenkontakte (APK)",
            "Alter",     // domain axis label
            "Arzt-Patientenkontakte (APK)", // range axis label
            data,         // data
            PlotOrientation.HORIZONTAL,
            true,            // include legend
            true,            // tooltips
            false);
        
       return chart;
   }
}
