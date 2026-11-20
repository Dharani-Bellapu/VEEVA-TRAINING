import java.util.Optional;
import java.util.Scanner;

public class InventoryManagement {
    private final Inventory inventory = new Inventory();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new InventoryManagement().run();
    }

    private void run() {
        System.out.println("=== Inventory Management System ===");
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addProduct();
                case 2 -> removeProduct();
                case 3 -> checkProductDetails();
                case 4 -> displayAllProducts();
                case 5 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Choose a number from 1 to 5.");
            }
        }

        scanner.close();
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Add Product");
        System.out.println("2. Remove Product");
        System.out.println("3. Check Product Details");
        System.out.println("4. Display All Products");
        System.out.println("5. Exit");
    }

    private void addProduct() {
        String productId = readNonBlank("Product ID: ");
        if (inventory.findProduct(productId).isPresent()) {
            System.out.println("A product with that ID already exists.");
            return;
        }

        String productName = readNonBlank("Product name: ");
        int quantity = readNonNegativeInt("Quantity: ");
        double price = readNonNegativeDouble("Price: ");
        inventory.addProduct(new Product(productId, productName, quantity, price));
        System.out.println("Product added successfully.");
    }

    private void removeProduct() {
        String productId = readNonBlank("Product ID to remove: ");
        if (inventory.removeProduct(productId)) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void checkProductDetails() {
        String productId = readNonBlank("Product ID to find: ");
        Optional<Product> product = inventory.findProduct(productId);
        if (product.isPresent()) {
            System.out.println(product.get());
        } else {
            System.out.println("Product not found.");
        }
    }

    private void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("--- Products ---");
        for (Product product : inventory.getProducts()) {
            System.out.println(product);
        }
    }

    private String readNonBlank(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Value cannot be empty.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private int readNonNegativeInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value >= 0) {
                return value;
            }
            System.out.println("Value cannot be negative.");
        }
    }

    private double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException exception) {
                // Report invalid input below.
            }
            System.out.println("Enter a valid non-negative price.");
        }
    }
}
