package COMP603_ProjectGroup13;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cashier implements Feature {

    
    private static Scanner scan = new Scanner(System.in);    
    public DecimalFormat df = new DecimalFormat("#.00");                
    List<Product> carts;
    private double bill;
    private int size; // navigate how much the size of ArrayList carts
    private Product product;  
    private double amountChange;
    private double fuelCost;


    public Cashier() {
        this.carts = new ArrayList<>();
        this.size = 0;
        this.bill = 0;
        this.amountChange = 0;                        
        this.product = null;
        this.fuelCost = 0;
    }
              
    public double getChange() {
        return amountChange;
    }
    
    public void setAmountChange(double amountChange) {
        this.amountChange = amountChange;
    }

    public double getFuelCost() {
        return fuelCost;
    }
    
    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public double getBill() {
        return this.bill;
    }
    
    public void setBill(double currentBill) {
        this.bill = currentBill;
    }

    public List<Product> getList() {
        return this.carts;
    }

    public int getCartSize() {
        return size;
    }
    
    @Override
    public void addToCart(Product aProduct) {
        this.product = aProduct;
        this.carts.add(new Product(product.getItem_id(), product.getItem(), product.getItemPrice(), product.getCategory()));
        size++;
    }
    
    //Remove an item which is base on its index
    @Override
    public void removeCartItem(int indexRemove) {
        if (size >= 0) {
            this.carts.remove(indexRemove);
            size--;
        }
    }

    @Override
    public void refresh() {
        this.carts.clear();
        this.bill = 0;
        this.setAmountChange(0);
        this.size = 0;
    }
    
    //Print the receipt with its order_cartID
    @Override
    public String print_receipt()
    {
        String out = "";        
        
        for (Product p : getList()) 
            if(p.getCategory().equalsIgnoreCase("Fuel")) {
                out += "Item: " + p.getItem() + " " + "Price per Liter: $" + df.format(p.getItemPrice()) 
                        + " " + "Cost: $" + df.format(this.getFuelCost()) + "\n";
            }else {
                out += "Item: " + p.getItem() + " " + "Price per Item: $" + df.format(p.getItemPrice()) 
                        + " " + "Cost: $" + df.format(p.getItemPrice())  + "\n";
            }

        return out;
    }

    //Compute all the item prices in a cart
    @Override
    public double total_price()
    {
        double totalPrice = 0;
        for (Product p : getList()) 
            if(p.getCategory().equalsIgnoreCase("Fuel")) {
                totalPrice += this.getFuelCost();
            }else {
                totalPrice += p.getItemPrice();
            }
        
        this.bill = totalPrice;             
        return totalPrice;
    }
    
}