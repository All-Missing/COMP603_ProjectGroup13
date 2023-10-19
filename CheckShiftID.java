package COMP603_ProjectGroup13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class CheckShiftID {                   
    
    public int checkShiftID() {
        
        int highestShiftID = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("./file_records/BillOrder_Records.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("---ShiftID: ")) {
                    int currentShiftID = Integer.parseInt(line.split(" ")[1]);
//                    System.out.println("Found ShiftID: " + currentShiftID); // Debug statement
                    if (currentShiftID > highestShiftID) {
                        highestShiftID = currentShiftID;
                    }
                }
            }
//            System.out.println("Calculated Next ShiftID: " + (highestShiftID + 1)); // Debug statement
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highestShiftID + 1;
    }
}