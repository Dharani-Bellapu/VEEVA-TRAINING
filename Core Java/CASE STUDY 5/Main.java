import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner;
    private final ProductService productService;

    private Main(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Main(scanner, new ProductService()).run();
        }
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> display(productService.getAllProducts());
                    case 3 -> display(productService.sortByPriceDescending());
                    case 4 -> filterByPriceRange();
                    case 5 -> display(productService.sortByPriceAndManufactureDateDescending());
                    case 6 -> display(productService.searchByName(readText("Product name: ")));
                    case 7 -> display(productService.searchByManufactureDate(
                            readDate("Manufacturing date (yyyy-MM-dd): ")));
                    case 8 -> display(productService.searchByPrice(readDouble("Price: ")));
                    case 9 -> searchByNameCompanyAndPrice();
                    case 10 -> display(productService.findTopKExpensive(readInt("K: ")));
                    case 11 -> display(productService.findLeastKExpensive(readInt("K: ")));
                    case 0 -> running = false;
                    default -> System.out.println("Choose a menu option from 0 to 11.");
                }
            } catch (InvalidQuantityException | IllegalArgumentException exception) {
                System.out.println("Unable to complete operation: " + exception.getMessage());
            }
        }
        System.out.println("Goodbye.");
    }

    private void addProduct() throws InvalidQuantityException {
        int id = readInt("Product ID: ");
        String name = readText("Product name: ");
        double price = readDouble("Price: ");
        String manufactureDate = readDate("Manufacturing date (yyyy-MM-dd): ");
        int quantity = readInt("Quantity in hand: ");
        String company = readText("Company name: ");
        productService.addProduct(new Product(id, name, price, manufactureDate, quantity, company));
        System.out.println("Product added.");
    }

    private void filterByPriceRange() {
        double minimum = readDouble("Minimum price: ");
        double maximum = readDouble("Maximum price: ");
        display(productService.findInPriceRange(minimum, maximum));
    }

    private void searchByNameCompanyAndPrice() {
        String name = readText("Product name: ");
        String company = readText("Company name: ");
        double price = readDouble("Price: ");
        display(productService.searchByNameCompanyAndPrice(name, company, price));
    }

    private static void display(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No matching products found.");
            return;
        }
        products.forEach(System.out::println);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This value cannot be empty.");
        }
    }

    private String readDate(String prompt) {
        while (true) {
            String input = readText(prompt);
            try {
                return LocalDate.parse(input).toString();
            } catch (DateTimeParseException exception) {
                System.out.println("Enter a valid date in yyyy-MM-dd format.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n=== Product Management System ===
                1. Add product
                2. Display all products
                3. Sort by price (descending)
                4. Filter by price range (descending)
                5. Sort by price and manufacturing date (descending)
                6. Search by product name
                7. Search by manufacturing date
                8. Search by price
                9. Search by name, company, and price
                10. Find top K expensive products
                11. Find least K expensive products
                0. Exit
                """);
    }
}
