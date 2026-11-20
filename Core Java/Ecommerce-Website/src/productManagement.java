
import java.util.*;
public class productManagement {
    private static final HashMap<Integer,Product> prod_id = new HashMap<>();
    public static void addProduct(Product p) {
        int id = p.getProductId();
        if (prod_id.containsKey(id)) {
            System.out.println("Product ID already exists!");
            return;
        }
        prod_id.put(id,p);
        System.out.println("Product added successfully!");
    }
    public static void deleteProduct(int productId) {
        if (prod_id.remove(productId) != null) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }
    public static Product getProductById(int productId) {
        return prod_id.get(productId);
    }
    public static void modifyProduct(int productId) {
        Product p = prod_id.get(productId);
        if (p == null) {
            System.out.println("Product not found!");
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Category");
        System.out.println("2. Product Name");
        System.out.println("3. Price");
        System.out.println("4. Company");
        int ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1:
                System.out.println("Enter new category:");
                p.setCategory(sc.nextLine());
                break;
            case 2:
                System.out.println("Enter new product name:");
                p.setProduct_name(sc.nextLine());
                break;
            case 3:
                System.out.println("Enter new price:");
                p.setPrice(sc.nextDouble());
                break;
            case 4:
                System.out.println("Enter new company:");
                p.setCompany(sc.nextLine());
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        System.out.println("Product updated successfully!");
    }
    public static ArrayList<Product> searchByName(String name) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : prod_id.values()) {
            if (p.getProduct_name().equalsIgnoreCase(name)) {
                result.add(p);
            }
        }
        return result;
    }
    public static ArrayList<Product> searchByNameAndPrice(String name,double price) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : prod_id.values()) {
            if (p.getProduct_name().equalsIgnoreCase(name) && Double.compare(p.getPrice(),price) == 0) {
                result.add(p);
            }
        }
        return result;
    }
    public static ArrayList<Product> searchByNameAndPriceRange(String name,double minPrice,double maxPrice) {
        ArrayList<Product> result = new ArrayList<>();
        if (minPrice > maxPrice) {
            return result;
        }
        for (Product p : prod_id.values()) {
            if (p.getProduct_name().equalsIgnoreCase(name) && p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                result.add(p);
            }
        }
        return result;
    }
    public static ArrayList<Product> searchByNameAndCompany(String name,String company) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : prod_id.values()) {
            if (p.getProduct_name().equalsIgnoreCase(name) && p.getCompany().equalsIgnoreCase(company)) {
                result.add(p);
            }
        }
        return result;
    }
    public static ArrayList<Product> searchByNameCompanyAndPrice(String name,String company,double price) {
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : prod_id.values()) {
            if (p.getProduct_name().equalsIgnoreCase(name) && p.getCompany().equalsIgnoreCase(company) && Double.compare(p.getPrice(),price) == 0) {
                result.add(p);
            }
        }
        return result;
    }
    public static void displayAllProducts() {
        for (Product p : prod_id.values()) {
            displayProduct(p);
        }
    }
    public static void displayProduct(Product p) {
        System.out.println("Product ID: " + p.getProductId());
        System.out.println("Name: " + p.getProduct_name());
        System.out.println("Category: " + p.getCategory());
        System.out.println("Price: " + p.getPrice());
        System.out.println("Company: " + p.getCompany());
    }
}
