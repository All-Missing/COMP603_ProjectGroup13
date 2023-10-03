package COMP603_ProjectGroup13;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AccessStaffFile {

    private static Scanner scan;
    private static HashMap<String, String> staffList;
    private final Staff_Record staff_records;
    private int retrieve_shift_id = 0;
    private HashMap<String, Product> product_records;
    private HashMap<String, Double> bill_records;    
    private static SaveCashierFileRecords saveRecords;
    
    public AccessStaffFile(int shift_id) {
        saveRecords = new SaveCashierFileRecords();
        this.product_records = new HashMap<>();
        scan = new Scanner(System.in);
        this.staff_records = new Staff_Record();
        this.retrieve_shift_id = shift_id;
        this.bill_records = new HashMap<>();
    }
    
    //Login staff_ID
    public void loginStaff_ID()
    {
        
        //Staff records_ID
        this.staffList = staff_records.getStaff_list();                      
        //Prompt and display CUI
        System.out.println("You are signing in to this POS.....");
        System.out.println("Please wait... for a few seconds to connect the main system!");
        String shift_id = String.valueOf(retrieve_shift_id); 
        boolean isValid = false;
        while (!isValid)
        {
            System.out.print("Please enter your staff_ID: ");            
            String userInput = scan.nextLine();            
            for (Map.Entry<String, String> entry: staffList.entrySet())
            {
                if (entry.getValue().equals(userInput.trim())) 
                {
                    String staff_name = entry.getKey();
                    String staff_id = entry.getValue();
                    System.out.println("user: "+entry.getValue()+" "+"login succeed!");
                    isValid = true;                    
                    //Invoking sale process!
                    SaleProcess saleProcess = new SaleProcess();
                    
                    //Invoking ProductList constructor to call load_product_list()
                    ProductList productList = new ProductList(); 
                    product_records = productList.getProduct_records();
                    saleProcess.saleProcess(product_records);
                    
                    //Notify user has exist the sale process successfully
                    System.out.println();                    
                    System.out.println("The sale process exits successfully");
                    System.out.println("Process log out system... Please wait a few minutes...");
                    
                    //Retrive values bill_records,shift_id, staff_id, staff_name
                    // and ready to print out it on text file which used BufferedWriterter class
                    bill_records = saveRecords.getCashierRecord(); //Load cashier reports                   
                                                           
                    //Invoking logOut_staffID with 4 parameters retrieved above
                    logOut_staffID(bill_records, shift_id, staff_id, staff_name);
                }                                                
            }
            
            if (!isValid)
                System.out.println("Invalid input! Pls enter valid staff_id again!");                                        
        }
        
    }
    
    public static void logOut_staffID(HashMap<String, Double> cashier_records, String shift_id, String staff_id, String staff_name)
    {                               
        boolean isLoginValid = false;
        while (!isLoginValid)
        {
            System.out.print("> staff name: ");            
            String check_staffName = scan.nextLine();
            System.out.print("> staff id: ");
            String check_staffID = scan.nextLine();
            System.out.println();
            
            if (check_staffID.trim().equals(staff_id) && check_staffName.trim().equalsIgnoreCase(staff_name))
            {                                   
                boolean isExit = false;
                while (!isExit)
                {
                    System.out.println("Please follow up instructions for staff users.");
                    System.out.println("1. Please save first before logging out!");                
                    System.out.println("2.Log out\n");                     
                    System.out.println("-----End of shift-----");                    
                    System.out.println("Staff name: "+staff_name+" Staff_id: "+staff_id);                    
                    System.out.print("> Staff choice: ");
                    String user_input = scan.nextLine();                    
                    int user_option;
                    
                    try
                    {
                        user_option = Integer.parseInt(user_input.trim());
                        switch(user_option)
                        {
                            case 1://Save file writer
                                saveRecords.saveFileRecord(cashier_records, shift_id , staff_id, staff_name);
                                System.out.println("File is saved sucessfully!");
                                break;                            
                            case 2:  //Exit the whole system after the shift is ended
                                System.out.println("Successfully log out for this staff "+staff_id+" - "+staff_name);
                                System.out.println("-------------------------------------");
                                System.out.println();
                                isExit = true;
                                isLoginValid = true;
                                break;
                            default:
                                break;
                        }                        
                    }
                    catch (NumberFormatException e)
                    {   System.out.println("Number format exception! Please enter valid input!");
                    }                    
                }
            }
            
            //If its fail, this asks user to put valid input again.
            if (!isLoginValid)
            {   System.out.println("Log out failed! Please enter valid staffID and staff name again!");
            }            
        }
                
    }
    
}
