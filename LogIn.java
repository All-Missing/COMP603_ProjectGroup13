package COMP603_ProjectGroup13;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LogIn {

    private static Scanner scan;
    private HashMap<String, String> staffList;
    private int retrieve_shift_id;
    private final Staff_Record staff_records;    
    private RunSaleProcess rsProcess;
    
    public LogIn(int shift_id) {        
        scan = new Scanner(System.in);
        this.staff_records = new Staff_Record();
        this.retrieve_shift_id = shift_id;
    }

    //Login staff_ID
    public void loginStaff_ID() {
        this.staffList = staff_records.getStaff_list();      
        // Prompt and display CUI
        System.out.println("You are signing in to this POS...");
        System.out.println("Please wait for a few seconds to connect to the main system!\n");
        String shift_id = String.valueOf(retrieve_shift_id);
        boolean isValid = false;

        while (!isValid) {
            System.out.println("NOTE! Press X to exit this function\n");
            System.out.print("Please enter your staff_ID: ");
            String userInputID = scan.nextLine().toLowerCase().trim();

            //Exit function login staff.
            if (userInputID.equalsIgnoreCase("x")) {
                break;
            }

            if (isValidStaff(userInputID)) {

                // Extract staff_name and staff_id from the valid staff entry
                for (Map.Entry<String, String> entry : staffList.entrySet()) {

                    if (entry.getValue().equals(userInputID.trim())) {
                        String staff_name = entry.getKey();
                        String staff_id = entry.getValue();
                        System.out.println("User: " + staff_id + " login succeeded!\n");
                        isValid = true;
                        //Starting Sale process
                        rsProcess = new RunSaleProcess(shift_id, staff_id, staff_name);
                    }
                }
            } else {
                System.out.println("Invalid input! Please enter a valid staff_id as String type");
            }
        }
    }

    private boolean isValidStaff(String staffID) {
        return staffList.containsValue(staffID);
    }

}
