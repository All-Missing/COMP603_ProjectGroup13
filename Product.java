package cashier;

public class Product extends PointOfSale {

    private String item;
    private String item_id;
    
//    public Product()
//    {
//        
//    }
    
    public Product(String item, double price)
    {
        this.setItem(item);
        this.itemPrice = price;
    }
    
    public Product(String item_id, String Item, double itemPrice)
    {
        this.setItem_id(item_id);
        this.setItem(Item);
        this.setItemPrice(itemPrice);
    }
    
    //-------------------------------------
    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }                
    
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }
    
    public void setItemPrice(double itemPrice)
    {
        this.itemPrice = itemPrice;
    }
    
    public double getItemPrice()
    {
        return this.itemPrice;
    }
    
    public void set_quantity(int amount)
    {
        this.amount = amount;
    }
    
    public double get_quantity()
    {
        return this.quantity;
    }
    //------------------------------------
   
}
