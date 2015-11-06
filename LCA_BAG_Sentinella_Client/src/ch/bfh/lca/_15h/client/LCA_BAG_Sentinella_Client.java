/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.client;

import ch.bfh.lca._15h.client.ui.MainFrame;
import ch.bfh.lca._15h.library.impl.DBDataSource;
import ch.bfh.lca._15h.library.Database.MSAccessDatabase;
import ch.bfh.lca._15h.library.JsonDPCWriter;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point.
 * @author Cédric Michelet
 */
public class LCA_BAG_Sentinella_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch main windows
        if (args == null ||args.length == 0) {
            MainFrame mf = new MainFrame();
            mf.setVisible(true);
        } else {
            System.out.println("SENTINELLA CLIENT: First argument found: " + args[0]);
            if (args[0].equals("-msacc")) {
                LCA_BAG_Sentinella_Client.prepareForMsAccessExport(args);
            }
        }
    }
    
    private static void prepareForMsAccessExport(String[] args) {
        String dst = "";
        String db = "";
        System.out.println("SENTINELLA CLIENT: Starting preparation for MS ACCESS Export");

        for(int i=0; i < args.length;i++) {
            if (args[i].equals("-msacc")) {
                try {
                    db = args[i+1];
                }catch(ArrayIndexOutOfBoundsException exp) {
                    System.out.println("SENTINELLA ERROR: Cant't find source database");
                }
            }
            
            if (args[i].equals("-dst")) {
                try {
                    dst = args[i+1];                    
                } catch (ArrayIndexOutOfBoundsException exp) {
                    System.out.println("SENTINELLA ERROR: Cant't find source destination file");
                }
            }
        }
        
        if (!dst.equals("") && !db.equals("")) {
            LCA_BAG_Sentinella_Client.exportJsonFromMsAccess(db, dst);
            System.out.println("SENTINELLA CLIENT: Export completed!");
        } else {
          System.out.println("SENTINELLA ERROR: Cant't find source or//and destination file");    
          System.out.println("SENTINELLA ERROR: AccessDB=" + db + " --- Dest=" + dst);
        }
    }
    
    private static void exportJsonFromMsAccess(String msAccessFile, String dstPath) {
        
        System.out.println("SENTINELLA CLIENT: Starting Export from " + msAccessFile.replace("\\", "/") + " to " + dstPath);

        try {
            MSAccessDatabase database = new MSAccessDatabase(msAccessFile.replace("\\", "/"));
            DBDataSource dataSource = new DBDataSource(database);
           
            JsonDPCWriter jsonWrtier = new JsonDPCWriter(dstPath);
            jsonWrtier.createDPCJson(dataSource);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(LCA_BAG_Sentinella_Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SENTINELLA ERROR: An Error occurs: " + ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(LCA_BAG_Sentinella_Client.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SENTINELLA ERROR: An Error occurs: " + ex.getMessage());
        } 
        
    }
    
}
