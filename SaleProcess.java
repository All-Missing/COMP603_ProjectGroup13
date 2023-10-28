package COMP603_ProjectGroup13;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class SaleProcess extends Cashier {

    private static Scanner scan = new Scanner(System.in);
    private Cashier cashier;
    private DecimalFormat df = new DecimalFormat("#.00");
    private static int NEXT_ORDER_ID = 0;
    private int cart_order_id;
    private double change;
    private final HashMap<String, Double> cashier_records;
    private CheckOrderID cOrderID;

    public SaleProcess() {
        this.cashier = new Cashier();
        cOrderID = new CheckOrderID();
        SaleProcess.NEXT_ORDER_ID = cOrderID.checkOrderID();
        this.cart_order_id = SaleProcess.NEXT_ORDER_ID;
        this.cashier_records = new HashMap<>();
    }

    public HashMap<String, Double> getCashierRecord() {
        return cashier_records;
    }

    //purchase function to add product to cart
    public void purchase(HashMap<String, Product> product_records) {
        //Create an cashier instance obj after each purchase is invoked!
        SaleProcess saleProcess = this;
        double currentBill = cashier.getBill();

        while (true) {
            System.out.println("Enter X to stop purchasing.\n");
            System.out.println("Enter product Name or ID to add to cart.");
            System.out.print("Choose product > ");
            String checkProductInput = scan.nextLine().trim();

            //exit purchase when enter "x"
            if (checkProductInput.equalsIgnoreCase("x")) {
                break;
            }

            //check if product match with item record in product records
            Product purchaseFuel = checkFuel(product_records, checkProductInput);
            Product purchaseProduct = checkProduct(product_records, checkProductInput);

            boolean productFound = false;
            if (purchaseFuel != null) {

                //fuel purchasable amount options
                Set<Integer> validOptions = new HashSet<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70));
                try {
                    System.out.println("Fuel Options: 10L, 20L, 30L, 40L, 50L, 60L, 70L");
                    System.out.print("Select an option: ");
                    int selectedOption = scan.nextInt();
                    scan.nextLine();

                    //check if user input match with options available in HashSet
                    if (validOptions.contains(selectedOption)) {
                        double costOfFuel = selectedOption * purchaseFuel.getItemPrice(); // Calculate total cost of fuel
                        double amountOfLiter = selectedOption; // Amount in liters

                        System.out.println("\nID: " + purchaseFuel.getItem_id() + " Name: " + purchaseFuel.getItem()
                                + " Price per Liter: $" + df.format(purchaseFuel.getItemPrice()) + "\nTotal: $"
                                + df.format(costOfFuel) + " Liters: " + df.format(amountOfLiter));
                        System.out.println("Fuel has been added.\n");

                        cashier.setFuelCost(costOfFuel);
                        currentBill += costOfFuel;
                        cashier.setBill(currentBill);

                        cashier.addToCart(new Product(purchaseFuel.getItem_id(), purchaseFuel.getItem(), purchaseFuel.getItemPrice(), purchaseFuel.getCategory()));

                        productFound = true;
                    } else {
                        System.out.println("\nInvalid option. Please select a valid fuel option (10L, 20L, 30L, 40L, 50L, 60L, 70L).");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a valid input.");
                    scan.nextLine(); //clear invalid input
                }
            } else if (purchaseProduct != null && purchaseProduct.getCategory().equalsIgnoreCase("Product")) {
                try {
                    System.out.print("Please enter quantity of items purchased: ");
                    int amountOfItem = scan.nextInt();
                    scan.nextLine();

                    if (amountOfItem > 0) {
                        double costOfProduct = amountOfItem * purchaseProduct.getItemPrice();
                        System.out.println("\nID: " + purchaseProduct.getItem_id() + " Name: " + purchaseProduct.getItem()
                                + " Price: $ " + df.format(purchaseProduct.getItemPrice()) + "\nTotal: $"
                                + df.format(costOfProduct) + " Quantity: " + amountOfItem);
                        System.out.println("Item has been added.\n");

                        currentBill += costOfProduct;
                        cashier.setBill(currentBill);

                        for (int i = 0; i < amountOfItem; i++) {
                            cashier.addToCart(new Product(purchaseProduct.getItem_id(), purchaseProduct.getItem(), purchaseProduct.getItemPrice(), purchaseProduct.getCategory()));
                        }

                        productFound = true;
                    } else {
                        System.out.println("\nInvalid input. Please check if quantity input is correct.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a valid input.");
                    scan.nextLine(); // clear invalid input
                }
            } else {
                System.out.println("Error! Product Not Found!\n");
            }
        }
    }

    //delete
    public Product checkFuel(HashMap<String, Product> product_records, String checkProductInput) {

        checkProductInput = checkProductInput.trim().toUpperCase();

        // Check if checkProductInput match item name "FUEL" or id "FU"
        if ((checkProductInput.contains("FU")) || (checkProductInput.contains("FUEL"))) {
            // Iterate through product_records to check for matches
            for (Product MatchFuel : product_records.values()) {
                String itemID = MatchFuel.getItem_id();
                String itemName = MatchFuel.getItem();

                // Check whether the user input item_id or item name contains checkProduct_input
                if (itemID.contains(checkProductInput) || itemName.contains(checkProductInput)) {
                    return MatchFuel;
                }
            }
            return null;
        }

        return null;
    }

    //This method is to check if there are existence items in the product records
    public Product checkProduct(HashMap<String, Product> product_records, String checkProduct_input) {
        //Iterate each element through the product records to check
        for (Product checkProduct : product_records.values()) {
            String itemID = checkProduct.getItem_id();
            String itemName = checkProduct.getItem();

            //Check whether the user input item_id or item name that are matches with product records
            if ((itemID.trim().equalsIgnoreCase(checkProduct_input))
                    || (itemName.trim().equalsIgnoreCase(checkProduct_input))) {
                return checkProduct;
            }
        }
        return null;
    }

    //Remove item from carts
    public void checkRemoveCartItem() {
        boolean isValid = false;
        if (cashier.getCartSize() > 0) {
            System.out.print("What item number in the cart that you wish to remove?  ");
            String input_index = scan.nextLine();
            int indexRemoveInput;

            try {
                indexRemoveInput = Integer.parseInt(input_index.trim());
                if (indexRemoveInput >= 1 && indexRemoveInput <= cashier.getCartSize()) {
                    int indexRemove = indexRemoveInput - 1;
                    cashier.removeCartItem(indexRemove);
                    System.out.println("This item number [" + indexRemoveInput + "] in the cart has been removed.\n");
                    isValid = true;
                } else {
                    System.out.println("Invalid index. Please enter a valid index to remove product.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Number format exception. Please try to input again!");
            }
        } else {
            System.out.println("\nCart is currently empty. Please try again when items has been added to cart.");
        }

    }

    public void saleProcess(HashMap<String, Product> product_records) {
        boolean endOfTransaction = false;
        while (!endOfTransaction) {
            System.out.print("\nEnter the following number to\n");
            System.out.print("1: Purchase\n2: Remove\n3: Refresh\n");
            System.out.print("4: Check Cart\n5: Payment\n6: Cancel Transaction\n");
            System.out.println("7: Print Cashier Record\n8: Exit Sale Process");
            System.out.print("> User choice: ");
            String user_choice = scan.nextLine();
            System.out.println();
            int option = 0;

            //Check if user got bad input
            try {
                option = Integer.parseInt(user_choice.trim());
                switch (option) {
                    case 1: //Call purchase method
                        this.purchase(product_records);
                        break;
                    case 2: //Remove purchase method
                        this.checkRemoveCartItem();
                        break;
                    case 3: //Call refresh to clear the cart. In case, it is cashier error from user input
                        System.out.println("Cart has been refresh.");
                        cashier.refresh();
                        break;
                    case 4: //Prompt to display item details in each cart //
                        //Something wrong with fuel with its price!
                        System.out.println("Display current item in cart:");
                        System.out.print(cashier.print_receipt());
                        break;
                    case 5: //Call payment method
                        this.paymentProcess(endOfTransaction);
                        break;
                    case 6: //Cancel transaction if user got cashier error
                        System.out.println("\nTransaction has been cancel.");
                        cashier.refresh();
                        break;
                    case 7: //Print sale records
                        this.printSaleRecord();
                        break;
                    case 8:
                        //log out option
                        cashier.refresh(); //To avoid any error payment before log out
                        System.out.println("Exiting sale process....\n");
                        endOfTransaction = true;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Number format exception. Please try to enter valid input again!");
            }
        }

    }

    public void paymentProcess(boolean endOfTransaction) {
        if (cashier.getCartSize() > 0) {   //if(this transaction is a success print receipt else terminate)
            System.out.println("\nCartID: " + cart_order_id + "\nQuantity: " + cashier.getCartSize() + "\nTotal price: " + df.format(cashier.total_price()));
            //if cancel payment this is print causing error. No receipt was suppose to be print in case of cancelation
            //++method
            boolean isInputValid = false;
            while (!isInputValid) {
                double payAmount = 0;
                System.out.print("Amount paid: ");
                String userInputAmount = scan.nextLine();
                //Check user input got bad input
                try {
                    Double checkPayAmount = Double.parseDouble(userInputAmount.trim());
                    payAmount = checkPayAmount;
                    isInputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Number format is error. Pls try again");
                }

                if (isInputValid) {
                    this.payment(payAmount, endOfTransaction);
                }
            }
        } else {
            System.out.println("Cart is currently empty. Please try again when items has been added to cart.");
        }
    }

    public void payment(double payAmount, boolean endOfTransaction) {
        //Pay by cash or EPTOS - Press(1) pay by EPTOS or Press(2) py by cash
        //Press (3) cancel all payment by invoking refresh method.
        boolean isValid = false;
        double currentChange = 0;

        while (!isValid) {
            System.out.println("\n1:Eftpos\n2:Cash\n3:Cancel Payment\n");
            System.out.print("Option to pay > ");
            String customerPayOption = scan.nextLine();
            int option;

            try {
                option = Integer.parseInt(customerPayOption.trim());

                switch (option) {
                    case 1://Option 1 by pay EPTOS
                        if (payAmount >= cashier.getBill()) {
                            currentChange -= payAmount - cashier.getBill();
                            cashier.setAmountChange(currentChange);
                            System.out.println("Pay by EPTOS....");
                            System.out.println("Payment proceed succefully");
                            currentChange = cashier.getChange();
                            this.transactionComplete(endOfTransaction, currentChange);
                            isValid = true;
                        } else //If it is not enough balance, it make user put valid price again
                        {
                            System.out.println("Not enough balance");
                            System.out.println("Card is decline");
                            System.out.println("Please try to input valid amount again!");
                            this.paymentProcess(endOfTransaction);
                            isValid = true;
                        }
                        break;
                    case 2: //Option 2 pay by cash
                        if (payAmount >= cashier.getBill()) {   //bug with change calculation
                            System.out.println("Pay by Cash...");
                            currentChange -= payAmount - cashier.getBill();
                            cashier.setAmountChange(currentChange);
                            //current balance should be add with bill receive from customer
                            System.out.println("Payment proceed succesfully");
                            currentChange = cashier.getChange();
                            this.transactionComplete(endOfTransaction, currentChange);
                            isValid = true;
                        } else //If it is not enough balance, it make user put valid price again
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
                        cashier.refresh();
                        isValid = true;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Number format exception! Pls enter valid amount to pay again");
            }
        }
    }

    public void transactionComplete(boolean endOfTransaction, double amountChange) {
        this.change = amountChange;
        boolean isPaymentFinish = endOfTransaction; //Set the flag if payment is finished        
        while (!isPaymentFinish) {
            System.out.println("1:Print Receipt\n2:Don't print receipt");
            System.out.print("\nDo you want to print a receipt? ");
            String userinput = scan.nextLine();

            int option = 0;
            try {
                option = Integer.parseInt(userinput.trim());
                switch (option) {
                    case 1: //Option 1 allow to print the receipt after transaction complete!                     
                        System.out.println("\nTransaction complete");
                        cart_order_id = ++SaleProcess.NEXT_ORDER_ID;
                        System.out.println(print_receipt());
                        addCashierRecord(cart_order_id, cashier);
                        cashier.refresh();
                        isPaymentFinish = true;
                        break;
                    case 2: //Option 2 don't need to print receipt
                        System.out.println("Transction complete!");
                        cart_order_id = ++SaleProcess.NEXT_ORDER_ID;
                        System.out.println(print_receipt());
                        addCashierRecord(cart_order_id, cashier);
                        cashier.refresh();
                        isPaymentFinish = true;
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Number format exception. Please enter valid input again!");
            }
        }
    }

    //Staff user can print out the sale records, then report it back to the manager 
    public void printSaleRecord() {
        for (Map.Entry<String, Double> entry : cashier_records.entrySet()) {
            String order_id = entry.getKey();
            double bill_order = entry.getValue();
            //Print orderID and bill for each record
            System.out.println("OrderId: " + order_id + " Bill: $ " + df.format(bill_order) + "\n");
        }
    }

    //Cashier receipt is recorded
    //Add each cart_orderID and each cashier object in cashier_records - works
    public void addCashierRecord(int order_id, Cashier cashier) {
        //There are bugs when put str_order_id bill cashier_records in if block condition
//        if (!(cashier.carts.isEmpty()) && cashier.getCartSize() > 0)
//        {
//        }    
//        else
//            System.out.println("There are no cart_orderID recorded.");

        String str_order_id = String.valueOf(order_id);
        double bill = cashier.getBill();
        cashier_records.put(str_order_id, bill);

    }

    @Override
    public String print_receipt() {
        String out = "";
        out += "--------------------------------------------\n";
        out += "Cart order id: " + cart_order_id + "\n" + cashier.print_receipt()
                + "\t\tTotal Price: " + df.format(cashier.getBill()) + "\n"
                + " \t\tTotal change: $ " + df.format(change) + "\n";
        out += "--------------------------------------------\n";
        return out;
    }

}
