package COMP603_ProjectGroup13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ProductList {

    private HashMap<String, Product> product_records;
    
    public ProductList() {
        product_records = new HashMap<>();
        load_product_list();
    }
    
    //Get product list
    public HashMap<String, Product> getProduct_records() {
        return product_records;
    }
    
    
    //Load product list from file IO
    public HashMap<String, Product> load_product_list()
    {
        BufferedReader br;
        try
        {
            br = new BufferedReader(new FileReader("./file_records/Product_Records.txt"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split(",");
                String product_id = lineParts[0];
                String product_name = lineParts[1];
                double product_price = Double.parseDouble(lineParts[2].trim());
                String category = lineParts[3];
                System.out.println(product_id+" "+product_name+" "+product_price+" "+category);
                Product load_product = new Product(product_id, product_name, product_price, category);
                //load_product.setItem_id(product_id);                  
                getProduct_records().put(product_id, load_product);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IOException error");
        }
        return getProduct_records();
    }
    
    public static void main(String[] args) {
        ProductList list = new ProductList();
        list.load_product_list();
    }
}
