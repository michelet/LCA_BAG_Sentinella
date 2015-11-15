/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server;

import ch.bfh.lca._15h.library.Database.SQLLiteDatabase;
import ch.bfh.lca._15h.server.exporter.DatabaseExportHandler;
import ch.bfh.lca._15h.server.exporter.ExportHandler;
import ch.bfh.lca._15h.server.importer.ImportHandler;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLite;

/**
 *
 * @author micheletc
 */
public class LCA_BAG_Sentinella_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if (args != null && args.length > 0) {
            System.out.println("SENTINELLA CLIENT: First argument found: " + args[0]);
            if (args[0].equals("-json") && args[2].equals("-db")) {
                String jsonFilePath = args[1];
                String dbFilePath = args[3];
                
                ImportHandler ih = new ImportHandler(jsonFilePath, dbFilePath);
                ih.importData();
                System.out.println("Done");
            } else if (args[0].equals("-tex") && args[2].equals("-db") && args[4].equals("-yyyy")) {
                String xlsdFilePath = args[1];
                String dbFilePath = args[3];
                int year = 0;
                try {
                    year  = Integer.parseInt(args[5]);
                } catch(NumberFormatException exp) {
                    System.out.println("Wrong Year format. Year is set to 2015");
                    year = 2015;
                }
                System.out.println("Try to export");
                        
                ExportHandler eh = new ExportHandler(new DatabaseExportHandler(new SQLLiteDatabase(dbFilePath)));
                try {
                    eh.ExportToExcelByYearAndAgeCategories(year, xlsdFilePath);
                } catch (IOException ex) {
                    Logger.getLogger(LCA_BAG_Sentinella_Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                System.out.println("Wrong parameters. Please check the manuel");
            }
        }
                
    }
    
    
    
}
