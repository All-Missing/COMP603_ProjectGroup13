package cashier;

public interface Feature {
    
    public void addToCart(Product item);
    public void removeCartItem(int indexRemove);
    public void refresh();
    public String print_receipt();
    public double total_price();
    public void payment(double amount, boolean endOfTransaction);
    
}
