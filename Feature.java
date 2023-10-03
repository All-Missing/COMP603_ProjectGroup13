package COMP603_ProjectGroup13;

public interface Feature {
    
    public void addToCart(Product item);
    public void removeCartItem(int indexRemove);
    public void refresh();
    public String print_receipt();
    public double total_price();
    
}
