package COMP603_ProjectGroup13;

import java.util.HashMap;
import java.util.Scanner;

public class LogOut {
   
    private SaveCashierFileRecords saveRecords;    
    
    public LogOut() {        
        this.saveRecords = new SaveCashierFileRecords();
    }
    
    
    public void logOutStaff_ID(HashMap<String, Double> bill_records, String shift_id, String staff_id, String staff_name) {
        boolean isLoginValid = false;
        Scanner scan = new Scanner(System.in);

        while (!isLoginValid) {
            // Implement CUI log here - minor fix
            System.out.print("> staff name: ");
            String check_staffName = scan.nextLine().trim();
            System.out.print("> staff id: ");
            String check_staffID = scan.nextLine().trim();
            System.out.println();

            if (check_staffID.equals(staff_id) && check_staffName.equalsIgnoreCase(staff_name)) {
                boolean isExit = false;
                while (!isExit) {
                    notifyUser(staff_name, staff_id);
                    String user_input = scan.nextLine();
                    int user_option;
                    try {
                        user_option = Integer.parseInt(user_input.trim());
                        switch (user_option) {
                            case 1:
                                saveRecords.saveFileRecord(bill_records, shift_id, staff_id, staff_name);
                                System.out.println("File is saved successfully!");
                                break;
                            case 2:
                                verifyLogOut(staff_id, staff_name);
                                isExit = true;
                                isLoginValid = true;
                                break;
                            default:
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Number format exception! Please enter valid input!");
                    }
                }
            }

            if (!isLoginValid) {
                System.out.println("Log out failed! Please enter valid staffID and staff name again!");
            }
        }
    }

    public void notifyUser(String staff_name, String staff_id) {
        System.out.println("Please follow up instructions for staff users.");
        System.out.println("1. Please save first before logging out!");
        System.out.println("2. Log out\n");
        System.out.println("-----End of shift-----");
        System.out.println("Staff name: " + staff_name + " Staff_id: " + staff_id);
        System.out.print("> Staff choice: ");
    }

    public void verifyLogOut(String staff_id, String staff_name) {
        System.out.println("Successfully log out for this staff " + staff_id + " - " + staff_name);
        System.out.println("-------------------------------------");
        System.out.println();
    }
}
