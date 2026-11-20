import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ProductService {
    private static final Comparator<Product> PRICE_ASCENDING =
            Comparator.comparingDouble(Product::getPrice);
    private static final Comparator<Product> PRICE_DESCENDING =
            PRICE_ASCENDING.reversed();
    private static final Comparator<Product> PRICE_AND_DATE_DESCENDING =
            Comparator.comparingDouble(Product::getPrice)
                    .thenComparing(Product::getManufactureDate)
                    .reversed();

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) throws InvalidQuantityException {
        if (product == null) {
            throw new IllegalArgumentException("Product is required.");
        }
        if (product.getQuantity_inhand() < 100) {
            throw new InvalidQuantityException(product.getQuantity_inhand());
        }
        if (product.getProd_id() <= 0) {
            throw new IllegalArgumentException("Product ID must be positive.");
        }
        if (product.getProd_name() == null || product.getProd_name().isBlank()
                || product.getComp_name() == null || product.getComp_name().isBlank()) {
            throw new IllegalArgumentException("Product and company names are required.");
        }
        if (!Double.isFinite(product.getPrice()) || product.getPrice() < 0) {
            throw new IllegalArgumentException("Price must be a non-negative finite number.");
        }
        try {
            product.getManufactureDate();
        } catch (IllegalStateException exception) {
            throw new IllegalArgumentException(exception.getMessage(), exception);
        }
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products);
    }

    public List<Product> sortByPriceDescending() {
        return products.stream().sorted(PRICE_DESCENDING).collect(Collectors.toList());
    }

    public List<Product> findInPriceRange(double minimum, double maximum) {
        if (!Double.isFinite(minimum) || !Double.isFinite(maximum)
                || minimum < 0 || maximum < minimum) {
            throw new IllegalArgumentException("Enter a valid price range (minimum <= maximum).");
        }
        return products.stream()
                .filter(product -> product.getPrice() >= minimum && product.getPrice() <= maximum)
                .sorted(PRICE_DESCENDING)
                .collect(Collectors.toList());
    }

    public List<Product> sortByPriceAndManufactureDateDescending() {
        return products.stream()
                .sorted(PRICE_AND_DATE_DESCENDING)
                .collect(Collectors.toList());
    }

    public List<Product> searchByName(String name) {
        return products.stream()
                .filter(product -> product.getProd_name().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Product> searchByManufactureDate(String date) {
        LocalDate manufactureDate = parseDate(date);
        return products.stream()
                .filter(product -> product.getManufactureDate().equals(manufactureDate))
                .collect(Collectors.toList());
    }

    public List<Product> searchByPrice(double price) {
        return products.stream()
                .filter(product -> Double.compare(product.getPrice(), price) == 0)
                .collect(Collectors.toList());
    }

    public List<Product> searchByNameCompanyAndPrice(String name, String company, double price) {
        return products.stream()
                .filter(product -> product.getProd_name().equalsIgnoreCase(name)
                        && product.getComp_name().equalsIgnoreCase(company)
                        && Double.compare(product.getPrice(), price) == 0)
                .collect(Collectors.toList());
    }

    public List<Product> findTopKExpensive(int count) {
        validateCount(count);
        PriorityQueue<Product> queue = new PriorityQueue<>(PRICE_ASCENDING);
        for (Product product : products) {
            queue.offer(product);
            if (queue.size() > count) {
                queue.poll();
            }
        }
        return drainInPriceOrder(queue, PRICE_DESCENDING);
    }

    public List<Product> findLeastKExpensive(int count) {
        validateCount(count);
        PriorityQueue<Product> queue = new PriorityQueue<>(PRICE_DESCENDING);
        for (Product product : products) {
            queue.offer(product);
            if (queue.size() > count) {
                queue.poll();
            }
        }
        return drainInPriceOrder(queue, PRICE_ASCENDING);
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Date must use yyyy-MM-dd.", exception);
        }
    }

    private static void validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("K must be at least 1.");
        }
    }

    private static List<Product> drainInPriceOrder(
            PriorityQueue<Product> queue, Comparator<Product> order) {
        List<Product> results = new ArrayList<>(queue);
        results.sort(order);
        return results;
    }
}
