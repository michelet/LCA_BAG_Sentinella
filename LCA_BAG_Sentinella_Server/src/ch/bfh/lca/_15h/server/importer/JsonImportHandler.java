/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.lca._15h.server.importer;

import ch.bfh.lca._15h.library.impl.DataSourceJSON;

/**
 *
 * @author Stefan
 */
public class JsonImportHandler {
    
    private final String FILEPATH;
    
    public JsonImportHandler(String filePath) {
        this.FILEPATH = filePath;
    }
    
    public DataSourceJSON getDataSource() {
       DataSourceJSON dataSource = new DataSourceJSON(FILEPATH);
       return dataSource;
    }
}
