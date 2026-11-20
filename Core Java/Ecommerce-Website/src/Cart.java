
import java.util.*;
public class Cart {
    private HashMap<User,ArrayList<Product>> list = new HashMap<>();
    public void addToCart(User u,Product p) {
        if (u == null || p == null) {
            System.out.println("Invalid user or product!");
            return;
        }
        ArrayList<Product> products = list.get(u);
        if (products == null) {
            products = new ArrayList<>();
            list.put(u,products);
        }
        products.add(p);
        System.out.println("Product added to cart!");
    }
    public void removeFromCart(User u,int productId) {
        ArrayList<Product> products = list.get(u);
        if (products == null) {
            System.out.println("Cart is empty!");
            return;
        }
        boolean removed = products.removeIf(p -> p.getProductId() == productId);
        if (removed) {
            System.out.println("Product removed from cart!");
        } else {
            System.out.println("Product not found in cart!");
        }
    }
    public ArrayList<Product> getCart(User u) {
        return list.getOrDefault(u,new ArrayList<>());
    }
    public void displayCart(User u) {
        ArrayList<Product> products = list.get(u);
        if (products == null || products.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }
        double total = 0;
        for (Product p : products) {
            System.out.println("Product ID: " + p.getProductId());
            System.out.println("Name: " + p.getProduct_name());
            System.out.println("Price: " + p.getPrice());
            total += p.getPrice();
        }
        System.out.println("Cart Total: " + total);
    }
    public void clearCart(User u) {
        list.remove(u);
        System.out.println("Cart cleared!");
    }
}
