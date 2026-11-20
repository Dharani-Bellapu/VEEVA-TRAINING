
import java.util.*;
public class Order {
    private int orderId;
    private User user;
    private ArrayList<orderItem> items;
    private double shippingCharge;
    private double totalAmount;
    private String status;
    private Date orderDate;
    public Order(int orderId,User user,ArrayList<orderItem> items,double shippingCharge) {
        this.orderId = orderId;
        this.user = user;
        this.items = items;
        this.shippingCharge = shippingCharge;
        this.orderDate = new Date();
        this.status = "PLACED";
        calculateTotal();
    }
    public void calculateTotal() {
        double amount = 0;
        for (orderItem item : items) {
            amount += item.getSubtotal();
        }
        totalAmount = amount + shippingCharge;
    }
    public int getOrderId() {
        return orderId;
    }
    public User getUser() {
        return user;
    }
    public ArrayList<orderItem> getItems() {
        return items;
    }
    public double getShippingCharge() {
        return shippingCharge;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + user.getName());
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        for (orderItem item : items) {
            System.out.println(item.getProduct().getProduct_name() + " x " + item.getQuantity() + " = " + item.getSubtotal());
        }
        System.out.println("Shipping Charge: " + shippingCharge);
        System.out.println("Total Amount: " + totalAmount);
    }
}
