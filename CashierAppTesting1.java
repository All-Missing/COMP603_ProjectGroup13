package COMP603_ProjectGroup13;

import java.util.Scanner;

public class CashierAppTesting1 {
    
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
     
        int shift_id = 1;  // Initialize shift_id to 1
        boolean isStartNewShift = false;
        while (!isStartNewShift)
        {
            System.out.print("\tDo you want to start a new shift? (y/n): ");
            System.out.print("> User input: ");
            String userInput = scan.nextLine();                      
           
            if (userInput.trim().equalsIgnoreCase("n"))
            {
               System.out.println("User don't wish to login");
               System.exit(0);
            }
                        
            //NOTE: I can create an obj that will find the last shift_id from BillOrder_Records textFile if
            //file BillOrder_Records is not null. Else, this will start with shift_id = 0
            if (userInput.trim().equalsIgnoreCase("y"))
            {
               AccessStaffFile accessStaffFile = new AccessStaffFile(shift_id);
               accessStaffFile.loginStaff_ID();
               shift_id++;
            }                                                                                
        }
        
       
    }
    
}

//Check bug cartID after paying and cancel. If it cancel still record the latest purchase cart_ID