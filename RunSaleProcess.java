package COMP603_ProjectGroup13;

import java.util.HashMap;

public class RunSaleProcess {
    
    private String shift_id;
    private String staff_id;
    private String staff_name;
    private HashMap<String, Product> product_records;
    private HashMap<String, Double> bill_records;
    private LogOut logOut;   
    
    public RunSaleProcess(String shift_id, String staff_id, String staff_name) {
        this.shift_id = shift_id;
        this.staff_id = staff_id;
        this.staff_name = staff_name;        
        this.product_records = new HashMap<>();
        this.bill_records = new HashMap<>();
        this.logOut = new LogOut();
        startSaleProcess();
    }
    
    public void startSaleProcess() {
        SaleProcess saleProcess = new SaleProcess();

        //Invoking ProductList constructor to call load_product_list()
        ProductList productList = new ProductList();
        product_records = productList.getProduct_records();
        saleProcess.saleProcess(product_records);

        //Notify user has exist the sale process successfully                      
        System.out.println("The sale process exits successfully");
        System.out.println("Process log out system... Please wait a few minutes...");

        //Retrive values bill_records,shift_id, staff_id, staff_name and start log out function        
        bill_records = saleProcess.getCashierRecord();                         
        logOut.logOutStaff_ID(bill_records, shift_id, staff_id, staff_name);
    }
}
