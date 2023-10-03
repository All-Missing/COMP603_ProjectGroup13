package COMP603_ProjectGroup13;


abstract public class PointOfSale {

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //Fields
   public  double amount;
   public double itemPrice;
   public double fuelPrice;
   public int quantity;
   public boolean isValid;  
   
}




