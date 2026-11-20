
import java.util.*;
public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Cart cart = new Cart();
    private static User loggedInUser = null;
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== E-COMMERCE SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Display Products");
            System.out.println("4. Add Product");
            System.out.println("5. Search Product");
            System.out.println("6. Add To Cart");
            System.out.println("7. Remove From Cart");
            System.out.println("8. View Cart");
            System.out.println("9. Place Order");
            System.out.println("10. View My Orders");
            System.out.println("11. View Order By ID");
            System.out.println("12. Logout");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    productManagement.displayAllProducts();
                    break;
                case 4:
                    addProduct();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    addToCart();
                    break;
                case 7:
                    removeFromCart();
                    break;
                case 8:
                    viewCart();
                    break;
                case 9:
                    placeOrder();
                    break;
                case 10:
                    viewMyOrders();
                    break;
                case 11:
                    viewOrderById();
                    break;
                case 12:
                    logout();
                    break;
                case 13:
                    System.out.println("Thank you for using our application!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    private static void register() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        User user = AuthenticationService.register(name,email,phone,address,password);
        if (user != null) {
            System.out.println("Registration successful!");
        }
    }
    private static void login() {
        if (loggedInUser != null) {
            System.out.println("You are already logged in!");
            return;
        }
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        loggedInUser = AuthenticationService.login(email,password);
        if (loggedInUser != null) {
            System.out.println("Welcome, " + loggedInUser.getName());
        } else {
            System.out.println("Invalid email or password!");
        }
    }
    private static void addProduct() {
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter company: ");
        String company = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        Product p = new Product();
        p.setProductId(id);
        p.setProduct_name(name);
        p.setCategory(category);
        p.setCompany(company);
        p.setPrice(price);
        productManagement.addProduct(p);
    }
    private static void searchProduct() {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        ArrayList<Product> products = productManagement.searchByName(name);
        if (products.isEmpty()) {
            System.out.println("No products found!");
            return;
        }
        for (Product p : products) {
            productManagement.displayProduct(p);
        }
    }
    private static void addToCart() {
        if (!checkLogin()) return;
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();
        Product product = productManagement.getProductById(id);
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        cart.addToCart(loggedInUser,product);
    }
    private static void removeFromCart() {
        if (!checkLogin()) return;
        System.out.print("Enter product ID: ");
        int id = sc.nextInt();
        cart.removeFromCart(loggedInUser,id);
    }
    private static void viewCart() {
        if (!checkLogin()) return;
        cart.displayCart(loggedInUser);
    }
    private static void placeOrder() {
        if (!checkLogin()) return;
        System.out.print("Enter shipping charge: ");
        double shippingCharge = sc.nextDouble();
        Order order = OrderManagement.createOrder(loggedInUser,cart,shippingCharge);
        if (order != null) {
            order.displayOrder();
        }
    }
    private static void viewMyOrders() {
        if (!checkLogin()) return;
        ArrayList<Order> orders = OrderManagement.getOrdersByUser(loggedInUser);
        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }
        for (Order order : orders) {
            order.displayOrder();
            System.out.println("-------------------------");
        }
    }
    private static void viewOrderById() {
        System.out.print("Enter order ID: ");
        int id = sc.nextInt();
        Order order = OrderManagement.getOrderById(id);
        if (order == null) {
            System.out.println("Order not found!");
            return;
        }
        order.displayOrder();
    }
    private static void logout() {
        if (loggedInUser == null) {
            System.out.println("No user is logged in!");
            return;
        }
        AuthenticationService.logout();
        loggedInUser = null;
        System.out.println("Logged out successfully!");
    }
    private static boolean checkLogin() {
        if (loggedInUser == null) {
            System.out.println("Please login first!");
            return false;
        }
        return true;
    }
}
