package COMP603_ProjectGroup13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Staff_Record {

    private static HashMap<String, String> staff_list; 
    
    //Staff_Records constructor
    public Staff_Record()
    {
        staff_list = new HashMap<>();
        load_staff_files();
    }
    
    //Get the staff lists
    public HashMap<String, String> getStaff_list() {
        return staff_list;
    }
       
    //Method loading the staff files
    public static void load_staff_files()
    {        
        BufferedReader br; 
        try
        {            
            br = new BufferedReader(new FileReader("./file_records/Staff_Records.txt"));
            String line ="";
            while ((line = br.readLine()) != null)
            {
                String[] lineParts = line.split(" ");
                Staff_Record.staff_list.put(lineParts[0], lineParts[1]);                
            }
            br.close();
        }
        catch (FileNotFoundException e)
        {   System.out.println("Found not found!");
        }
        catch (IOException e)
        {            
        }
    }
           
}
