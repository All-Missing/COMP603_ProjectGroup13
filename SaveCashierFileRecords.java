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
    private SaleProcess saleProcess;
    public SaveCashierFileRecords()
    {
        this.saleProcess = new SaleProcess();
        this.cashier_records = saleProcess.getCashierRecord();
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
                
                //Test if cashier_record is empty
                if (cashier_records.isEmpty())
                    System.out.println("cashier_record is empty!");
                else {
                    for (Map.Entry<String, Double> entry : cashier_records.entrySet())
                    {
                        String current_order_id = entry.getKey();                    
                        Double current_bill = entry.getValue();
                        total_balance += current_bill;

                        line = "OrderID: " + current_order_id + " Bill: $" + current_bill;
                        bw.append(line);
                        bw.newLine();
                    }
                    line = "\t\t---Total balance earned per shift: $" + total_balance;
                    bw.append(line);
                    bw.newLine();
                    bw.close();                    
                }
            }
            catch (IOException ex)
            {
                System.out.println("Interrupted file. Cannot save this file!");
            }
    }
     
}
    
    
