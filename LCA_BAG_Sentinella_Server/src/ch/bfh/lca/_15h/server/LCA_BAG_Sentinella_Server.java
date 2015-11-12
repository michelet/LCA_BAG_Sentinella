/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server;

import ch.bfh.lca._15h.server.importer.ImportHandler;

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
            } else
            {
                System.out.println("Wrong parameters. Please check the manuel");
            }
        }
                
    }
    
    
    
}
