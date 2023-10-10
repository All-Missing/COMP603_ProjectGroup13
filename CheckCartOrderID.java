package COMP603_ProjectGroup13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckCartOrderID {

    public int current_cartID;
    
    public CheckCartOrderID() {
        
    }
    
    public static boolean checkBillOrderRecord() {   
        File file = new File("./file_records/BillOrder_Records.txt");        
        return file.exists() && file.length() == 0;
     }
     
    public static int getLastOrderID() {
        int current_order_id = 0;
        boolean isFileEmpty = checkBillOrderRecord();
        
        if (isFileEmpty)
            return current_order_id = 0;
        else {            
            BufferedReader br;
            try {            
                br = new BufferedReader(new FileReader("./file_records/BillOrder_Records.txt"));
                String line ="";
                while ((line = br.readLine()) != null)
                {
                                 
                }
                br.close();
            }   catch (FileNotFoundException e) {
                    System.out.println("Found not found!");
            }   catch (IOException e) {
                    System.out.println("IOException");
            }
        }                
        return current_order_id;
    }
       
    
}
