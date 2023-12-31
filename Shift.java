package COMP603_ProjectGroup13;

import java.util.Scanner;

public class Shift {
        
    private static Scanner scan;   
    private int shift_id;
    private CheckShiftID cShiftID;
    
    public Shift() {
        scan = new Scanner(System.in);
        cShiftID = new CheckShiftID();
        shift_id = cShiftID.checkShiftID();
    }
    
    public static void main(String[] args) 
    {
        Shift shift = new Shift();
        shift.processLogin();
    }
    
    public void processLogin()
    {        
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
               LogIn login = new LogIn(shift_id);
               login.loginStaff_ID();
               shift_id = cShiftID.checkShiftID();
            }
            else
                System.out.println("> Invalid input, Please answer (y/n) !");                                                               
        }
    }
        
}