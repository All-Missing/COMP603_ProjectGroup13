package COMP603_ProjectGroup13;

import java.util.Scanner;

public class Shift {
    
    private static int INITIAL_SHIFT_ORDER = 1;
    private static Scanner scan;   
    private int shift_id;
    
    public Shift() {
        scan = new Scanner(System.in);
        this.shift_id = Shift.INITIAL_SHIFT_ORDER++;
    }
    
    public static void main(String[] args) 
    {
        Shift shift = new Shift();
        shift.processLogin();
    }
    
    public void processLogin()
    {        
//        int shift_id = 1;  // Initialize shift_id to 1
        boolean isStartNewShift = false;
        while (!isStartNewShift)
        {
            System.out.print("\tDo you want to start a new shift? (y/n): ");
            System.out.print("> User input: ");
            String userInput = scan.nextLine();
            System.out.println("User answer: " + userInput);
           
            //Check if user's answer is 'n' then exist the entire program
            if (userInput.trim().equalsIgnoreCase("n"))
            {
               System.out.println("User don't wish to login");
               System.exit(0);
            }
                        
            //Check if user's answer is 'y' then process login staff_id 
            if (userInput.trim().equalsIgnoreCase("y"))
            {
               AccessStaffFile accessStaffFile = new AccessStaffFile(shift_id);
               accessStaffFile.loginStaff_ID();
               shift_id = Shift.INITIAL_SHIFT_ORDER++;
            }
            else
            {   System.out.println("Invalid input, Please answer (y/n) !");               
            }
                                    
        }
    }
    
    public int checkFile() {
        
        
        
        return 0;
    }
    
}