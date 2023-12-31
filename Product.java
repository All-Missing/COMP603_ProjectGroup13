package COMP603_ProjectGroup13;

public class Product extends PointOfSale {

    private String item;
    private String item_id;
    private String category;

    public Product()
    {
        this.item = null;
        this.item_id = null;
        this.category = null;
    }
    
    public Product(String item_id) {
        this.setItem_id(item_id);
    }

    public Product(String item_id, String Item, double itemPrice, String category) {
        this.setItem_id(item_id);
        this.setItem(Item);
        this.itemPrice = itemPrice;
        this.setCategory(category);
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

    public void setItem(String item) {
        this.item = item;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public void set_quantity(int amount) {
        this.amount = amount;
    }

    //get product quantity
    public int get_quantity() {
        return this.quantity;
    }

    //set product quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public boolean equals(Object o) {
        Product aProduct = (Product) o;        
        return this.item_id.equalsIgnoreCase(aProduct.item_id);
    }
    
    @Override
    public String toString() {
        return "ItemID: "+getItem_id()+" Item: "+getItem()
                                +" Item price: "+getItemPrice()+" category:"+getCategory();
    }
    
}
