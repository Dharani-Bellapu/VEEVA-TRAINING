
import java.util.*;
public class OrderManagement {
    private static final HashMap<Integer,Order> orders = new HashMap<>();
    private static int orderCounter = 1001;
    public static Order createOrder(User user,Cart cart,double shippingCharge) {
        if (user == null) {
            System.out.println("Please login first!");
            return null;
        }
        ArrayList<Product> products = cart.getCart(user);
        if (products.isEmpty()) {
            System.out.println("Cart is empty!");
            return null;
        }
        ArrayList<orderItem> items = new ArrayList<>();
        for (Product p : products) {
            items.add(new orderItem(p,1));
        }
        Order order = new Order(orderCounter++,user,items,shippingCharge);
        orders.put(order.getOrderId(),order);
        cart.clearCart(user);
        System.out.println("Order created successfully!");
        return order;
    }
    public static Order getOrderById(int orderId) {
        return orders.get(orderId);
    }
    public static ArrayList<Order> getOrdersByUser(User user) {
        ArrayList<Order> result = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getUser().getUser_id() == user.getUser_id()) {
                result.add(order);
            }
        }
        return result;
    }
    public static void updateOrderStatus(int orderId,String status) {
        Order order = orders.get(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }
        order.setStatus(status);
        System.out.println("Order status updated!");
    }
    public static void displayAllOrders() {
        for (Order order : orders.values()) {
            order.displayOrder();
        }
    }
}
