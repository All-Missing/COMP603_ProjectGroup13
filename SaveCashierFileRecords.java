package COMP603_ProjectGroup13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SaveCashierFileRecords {

    private static Scanner scan = new Scanner(System.in);           
    private final HashMap<String, Double> cashier_records;

    public SaveCashierFileRecords()
    {
        this.cashier_records = new HashMap<>();        
    }
    
    //Cashier receipt is recorded
    public HashMap<String, Double> getCashierRecord() {
        return cashier_records;
    }
    
    //Add each cart_orderID and each cashier object in cashier_records - works
    public void addCashierRecord(int order_id, Cashier cashier)
    {
        if (!(cashier.carts.isEmpty()) && cashier.getCartSize() > 0)
        {
            String str_order_id = String.valueOf(order_id);
            double bill = cashier.getBill();
            //Testing order_id & bill
//            System.out.println("Test addCashierRecord!!!");
//            System.out.println("str_order_id: "+str_order_id+"bill: "+bill);
            
            this.cashier_records.put(str_order_id, bill);            
        }    
        else
            System.out.println("There are no cart_orderID recorded.");      
    }
    
       
    public void saveFileRecord(String shift_id, String staff_id, String staff_name) {       
       String aShiftID = shift_id;
       String aStaffID = staff_id;
       String aStaffName = staff_name;
       
       saveFileRecords(aShiftID, aStaffID, aStaffName);
   } 
    
   private void saveFileRecords(String shift_id, String staff_id, String staff_name)
   {
       double total_balance = 0;
       BufferedWriter bw = null;
            try
            {
                
                bw = new BufferedWriter(new FileWriter("./file_records/BillOrder_records.txt", true));

                // First line of text file indicates staffID and staff name respectively
                String line = "---ShiftID: " + shift_id + " " + "staffID: " + staff_id + " staffName: " + staff_name;
                bw.write(line);
                bw.newLine();

                for (Map.Entry<String, Double> cashierEntry : cashier_records.entrySet())
                {
                    String current_order_id = cashierEntry.getKey();                    
                    Double current_bill = cashierEntry.getValue();
                    total_balance += current_bill;
                    
                    line = "OrderID: " + cashierEntry.getKey() + " Bill: $" + cashierEntry.getValue();
                    bw.append(line);
                    bw.newLine();
                }
                line = "\t\t---Total balance earned per shift: $" + total_balance;
                bw.append(line);
                bw.newLine();
                bw.close();
            }
            catch (IOException ex)
            {
                System.out.println("Interrupted file. Cannot save this file!");
            }
    }
     
}
    
    
