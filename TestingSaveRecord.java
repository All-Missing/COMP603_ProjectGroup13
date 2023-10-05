package COMP603_ProjectGroup13;

import java.util.HashMap;
import java.util.Map;


public class TestingSaveRecord {
    
    public static void main(String[] args) {
        
        SaveCashierFileRecords tAddCashierRecord = new SaveCashierFileRecords();
        Cashier cashier1 = new Cashier();
        Cashier cashier2 = new Cashier();
        Cashier cashier3 = new Cashier();
        Cashier cashier4 = new Cashier();
        
        cashier1.setBill(100.0);
        cashier2.setBill(200.0);
        cashier3.setBill(300.0);
        cashier4.setBill(400.0);
        
        tAddCashierRecord.addCashierRecord(1, cashier1);
        tAddCashierRecord.addCashierRecord(2, cashier2);
        tAddCashierRecord.addCashierRecord(3, cashier3);
        tAddCashierRecord.addCashierRecord(4, cashier4);
        
        HashMap<String, Double> retrieveMap = tAddCashierRecord.getCashierRecord();
        for (Map.Entry<String, Double> entry : retrieveMap.entrySet()) {
            String order_id = entry.getKey();
            Double bill = entry.getValue();
            
            System.out.println("order id: "+order_id+" bill: "+bill);
        }
    }
}
