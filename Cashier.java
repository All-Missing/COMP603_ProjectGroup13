package cashier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cashier implements Feature {

    private static Scanner scan = new Scanner(System.in);
    private static int INITIAL_CART_ORDER = 0;       
    private int cart_orderID;    
    private final HashMap<String, Double> cashier_records;
    List<Product> carts;
    private double bill;
    private int size; // navigate how much the size of ArrayList carts
    private Product product;  
    private double amountChange;

    public Cashier() {
        this.carts = new ArrayList<>();
        this.cashier_records = new HashMap<>();
        this.size = 0;
        this.bill = 0;
        this.amountChange = 0;       
        this.cart_orderID = 1;         
        this.product = null;
// SaleProcess sale1 = new ...

    }
              
    public double getChange() {
        return amountChange;
    }

    public double getBill() {
        return this.bill;
    }

    public List<Product> getList() {
        return this.carts;
    }

    public int get_order_id() {
        return cart_orderID;
    }

    @Override
    public void addToCart(Product aProduct) {
        this.product = aProduct;
        this.carts.add(new Product(product.getItem(), product.getItemPrice()));
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

    public int getCartSize() {
        return size;
    }

    @Override
    public void refresh() {
        this.carts.clear();
        this.bill = 0;
        this.amountChange = 0;
        this.size = 0;
    }
    
     //Print the receipt with its order_cartID
    @Override
    public String print_receipt()
    {
        String out = "";
        out += "orderID: " + get_order_id() + "\n";
        for (Product p : getList())      
            out += "Item: " + p.getItem() + " " + "ItemPrice: $" + p.getItemPrice() + "\n";
        
        out += "-------------------------------\n";
        out += "\tTotal Price: $" + total_price();
        return out;
    }

    //Compute all the item prices in a cart
    @Override
    public double total_price()
    {
        double totalPrice = 0;
        for (Product p : getList())        
            totalPrice += p.getItemPrice();
        
        this.bill = totalPrice;        
        return totalPrice;
    }

    //Add each cart_orderID and each cashier object in cashier_records
    public void addCashierRecord(int order_id, Cashier cashier)
    {
        if (!(cashier.carts.isEmpty()) && cashier.getCartSize() > 0)
        {
            String str_order_id = String.valueOf(order_id);
            double bill = cashier.getBill();            
            cashier.cashier_records.put(str_order_id, bill);
        }    
        else
            System.out.println("There are no cart_orderID recorded.");      
    }
    
    //Cashier receipt is recorded
    public HashMap<String, Double> getCashierRecord() {
        return cashier_records;
    }
    
    //Implement laters
    public void checkRemoveCartItem() {
        boolean isValid = false;
        if (this.size > 0)
        {
            System.out.print("What item number in the cart that you wish to remove?  ");
            String input_index = scan.nextLine();
            int indexRemoveInput = 0;
            
            try
            {
                indexRemoveInput = Integer.parseInt(input_index.trim());
                if (indexRemoveInput >= 1 && indexRemoveInput <= carts.size())
                {
                    int indexRemove = indexRemoveInput - 1;
                    this.removeCartItem(indexRemove);
                    System.out.println("This item number [" +indexRemoveInput +"] in the cart has been removed.\n");
                    isValid = true;
                }
                else
                {   System.out.println("Invalid index. Please enter a valid index to remove product.");
                }
            }
            catch (NumberFormatException e)
            {   System.out.println("Number format exception. Please try to input again!");
            }
        }
        else        
            System.out.println("\nCart is currently empty. Please try again when items has been added to cart.");
        
    }
    
    public void saleProcess(HashMap<String, Product> product_records)
    {
        boolean endOfTransaction = false;
        while (!endOfTransaction)
        {
            System.out.print("\nEnter the following number to\n");
            System.out.print("1: Purchase\n2: Remove\n3: Refresh\n");
            System.out.print("4: Check Cart\n5: Payment\n6: Cancel Transaction\n");
            System.out.println("7: Print Cashier Record\n8: Exit Sale Process");
            System.out.print("> User choice: ");
            String user_choice = scan.nextLine();
            System.out.println();
            int option = 0;
            
            //Check if user got bad input
            try
            {   
                option = Integer.parseInt(user_choice.trim());
                switch (option)
                {              
                    case 1: //Call purchase method
                        this.purchase(product_records);
                        break;                                    
                    case 2: //Remove purchase method
                        this.checkRemoveCartItem();
                        break;                    
                    case 3: //Call refresh to clear the cart. In case, it is cashier error from user input
                        System.out.println("Cart has been refresh.");
                        this.refresh();
                        break;                                    
                    case 4: //Prompt to display item details in each cart
                        System.out.println("Display current item in cart:");
                        System.out.print(this.print_receipt());
                        break;                                    
                    case 5: //Call payment method
                        this.paymentProcess(endOfTransaction);
                        break;                    
                    case 6: //Cancel transaction if user got cashier error
                        System.out.println("\nTransaction has been cancel.");
                        this.refresh();
                        break;                                    
                    case 7: //Print sale records
                        this.printSaleRecord();
                        break;                    
                    case 8:
                        //log out option
                        this.refresh(); //To avoid any error payment before log out
                        System.out.println("Exiting sale process....\n");
                        endOfTransaction = true;
                        break;
                    default:                    
                        break;
                }
            }
            catch (NumberFormatException e)
            {   System.out.print("Number format exception. Please try to enter valid input again!");
            }                        
        }

    }

    @Override
    public void payment(double payAmount, boolean endOfTransaction)
    {        
        //Pay by cash or EPTOS - Press(1) pay by EPTOS or Press(2) py by cash
        //Press (3) cancel all payment by invoking refresh method.
        boolean isValid = false;
        while (!isValid)
        {
            System.out.println ("\n1:Eftpos\n2:Cash\n3:Cancel Payment\n");
            System.out.print ("Option to pay > ");        
            String customerPayOption = scan.nextLine();
            int option = 0;
            
            try {
                option =Integer.parseInt(customerPayOption.trim());
                
                switch (option)
                {
                    case 1://Option 1 by pay EPTOS
                        if (payAmount >= bill)
                        {
                            amountChange -= payAmount - bill;                                                        
                            System.out.println("Pay by EPTOS....");
                            System.out.println("Payment proceed succefully");
                            this.transactionComplete(endOfTransaction, amountChange);
                            isValid = true;
                        }
                        else //If it is not enough balance, it make user put valid price again
                        {
                            System.out.println("Not enough balance");
                            System.out.println("Card is decline");
                            System.out.println("Please try to input valid amount again!");
                            this.paymentProcess(endOfTransaction);
                            isValid = true;
                        }
                        break;
                    case 2: //Option 2 pay by cash
                        if (payAmount >= bill)
                        {   //bug with change calculation
                            System.out.println("Pay by Cash...");
                            amountChange -= payAmount - bill;                            
                            //current balance should be add with bill receive from customer
                            System.out.println("Payment proceed succesfully");
                            this.transactionComplete(endOfTransaction, amountChange);
                            isValid = true;           
                        }
                        else //If it is not enough balance, it make user put valid price again
                        {
                            System.out.println("Not enough balance");
                            System.out.println("Please try to input valid amount again!");
                            this.paymentProcess(endOfTransaction);
                            isValid = true;
                        }
                        break;
                    case 3:
                         //This option in case, if user accidentally press payment so this will fresh everything to ensure
                        // customer won't get wrong charged. Or the cashier not sure what are they trying to make correct payment or not!
                        System.out.println("Cancel complete!");
                        this.refresh();
                        isValid = true;
                        break;
                    default:
                        break;
                }
            }
            catch (NumberFormatException e)
            {   System.out.println("Number format exception! Pls enter valid amount to pay again");
            }                        
        }        
              
    }

    public void paymentProcess(boolean endOfTransaction)
    {
        if (this.size > 0)
        {   //if(this transaction is a success print receipt else terminate)
            System.out.println("\nCartID: " + this.cart_orderID + "\nQuantity: " + this.size + "\nTotal price: " + this.total_price());
            //if cancel payment this is print causing error. No receipt was suppose to be print in case of cancelation
            //++method
            boolean isInputValid = false;            
            while (!isInputValid)
            {  
                double payAmount = 0;
                System.out.print("Amount paid: ");
                String userInputAmount = scan.nextLine();
                //Check user input got bad input
                try
                {   Double checkPayAmount = Double.parseDouble(userInputAmount.trim());
                     payAmount = checkPayAmount;
                     isInputValid = true;
                }
                catch (NumberFormatException e)
                {   System.out.println("Number format is error. Pls try again");                    
                }
                
                if (isInputValid)
                    this.payment(payAmount, endOfTransaction);
            }                                           
        }
        else
            System.out.println("Cart is currently empty. Please try again when items has been added to cart.");
    }
        
    //This method is to check if there are existence items in the product records
    public Product checkProduct(HashMap<String, Product> product_records, String checkProduct_input)
    {
        //Iterate each element through the product records to check
        for (Product checkProduct : product_records.values())
        {   String itemID = checkProduct.getItem_id();
            String itemName = checkProduct.getItem();
            
            //Check whether the user input item_id or item name that are matches with product records
            if ((itemID != null && itemID.trim().equalsIgnoreCase(checkProduct_input)) || 
                 (itemName != null && itemName.trim().equalsIgnoreCase(checkProduct_input)))
                return checkProduct;
        }
        return null;
    }
    
    public void purchase(HashMap<String, Product> product_records) {
        //Create an cashier instance obj after each purchase is invoked!
        Cashier currentCashier = this;        
        while (true)
        {  System.out.println("\nEnter X to stop purchasing.\n");
            System.out.println("Enter product Name or ID to add to cart.");
            System.out.print("Choose product > ");
            String userInput = scan.nextLine();
            Product purchaseProduct = checkProduct(product_records, userInput);          
            
            if (userInput.equalsIgnoreCase("x"))
                break;
            else if (purchaseProduct != null)
            {   
                System.out.println("ID: " + purchaseProduct.getItem_id() + " Name: " + purchaseProduct.getItem() + " Price: $" + purchaseProduct.getItemPrice());
                System.out.println("Item has been added.\n");
                
                currentCashier.bill += purchaseProduct.getItemPrice();
                currentCashier.addToCart(new Product(purchaseProduct.getItem_id(), purchaseProduct.getItem(), purchaseProduct.getItemPrice()));
            }
            else
                System.out.println("Error! Product Not Found!\n");           
        }
    }
    
    
    public void transactionComplete(boolean endOfTransaction, double amountChange)
    {   
        boolean isPaymentFinish = endOfTransaction; //Set the flag if payment is finished
        while (!isPaymentFinish)
        {              
            System.out.println("1:Print Receipt\n2:Don't print receipt");
            System.out.print("\nDo you want to print a receipt? ");
            String  userinput = scan.nextLine();
            int option = 0;            
                                
            try
            {   option = Integer.parseInt(userinput.trim());
            
                switch (option)
                {
                    case 1: //Option 1 allow to print the receipt after transaction complete!                     
                        System.out.println("\nTransaction complete");
                        this.addCashierRecord(this.get_order_id(), this);                        
                        System.out.println(this.print_receipt()+" Total change: $ "+amountChange);                             
                        ++cart_orderID; //Increment cart_orderID after finish payment
                        this.refresh();
                        isPaymentFinish = true;
                        break;                        
                    case 2: //Option 2 don't need to print receipt
                        System.out.println("Transction complete!");
                        this.addCashierRecord(this.get_order_id(), this);                                                
                        ++cart_orderID; //Increment cart_orderID after finish payment
                        this.refresh();
                        isPaymentFinish = true;
                        break;                        
                    default:                        
                        break;
                }
            }
            catch(NumberFormatException e)
            {   System.out.println("Number format exception. Please enter valid input again!");
            }                                     
        }
    }
             
    //Staff user can print out the sale records, then report it back to the manager 
    public void printSaleRecord()
    {               
        for (Map.Entry<String, Double> entry : cashier_records.entrySet())
        {
            String order_id = entry.getKey();            
            double bill_order = entry.getValue();            
            //Print orderID and bill for each record
            System.out.println("OrderId: " + order_id + " Bill: $" + bill_order + "\n");
        }
    }    
    
    
}
