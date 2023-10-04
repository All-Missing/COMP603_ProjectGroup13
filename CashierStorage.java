package COMP603_ProjectGroup13;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashierStorage {
    
    private final CashierDBManager dbManager;
    private final Connection conn;
    private Statement statement;
//    private HashMap<String, Product> product_records;
    private ProductList productList;
    
    public CashierStorage() {
        dbManager = new CashierDBManager();
        conn = dbManager.getCashierDBConnection();
        productList = new ProductList();
    }
    
    public void createProductDB() {
        
        try {
            //connect and initialize db
            statement = conn.createStatement();
        
            String tableName = "PRODUCT";
             //Check if table exisits. if not, manually create it
            checkExistedTable(tableName);
            
            String sqlTable = "CREATE TABLE " + tableName + " (ITEM_ID VARCHAR(10), "
                    + "ITEM VARCHAR(50), ITEM_PRICE DOUBLE, CATEGORY VARCHAR(20))";                    
            statement.executeUpdate(sqlTable);
            System.out.println("Table " + tableName +" created");
                       
            //Retrieve product records
            HashMap<String, Product> product_records = productList.getProduct_records();
            
        } catch (SQLException ex) {
            Logger.getLogger(CashierStorage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void checkExistedTable(String aTableName) {
        
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);
            
            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name + " table is existing");
                if (table_name.equalsIgnoreCase(aTableName)) {
                    statement.executeUpdate("Drop table " + aTableName);
                    System.out.println("Table " + aTableName + " is deleted.\n");
                    break;
                }                                
            }
            rs.close();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection() {
        this.dbManager.closeCashierDBConnection();
    }
    
}
