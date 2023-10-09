package COMP603_ProjectGroup13;

import java.util.HashMap;
import java.util.Map;


public class TestingSaveRecord {
    
    public static void main(String[] args) {
        
        SaleProcess saveRecords = new SaleProcess();
        Cashier cashier1 = new Cashier();
        Cashier cashier2 = new Cashier();
        Cashier cashier3 = new Cashier();
        Cashier cashier4 = new Cashier();
        HashMap<String, Double> maps;
                                
//        HashMap <String id, Cashier>
        cashier1.setBill(100.0);
        cashier2.setBill(200.0);
        cashier3.setBill(300.0);
        cashier4.setBill(400.0);
        
        saveRecords.addCashierRecord(1, cashier1);          
        saveRecords.addCashierRecord(2, cashier2);          
        saveRecords.addCashierRecord(3, cashier3);          
        saveRecords.addCashierRecord(4, cashier4);          
               
                               
        saveRecords.printSaleRecord();

        
    }
    
    
}
