import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private final List<Product> products = new ArrayList<>();

    public boolean addProduct(Product product) {
        if (findProduct(product.getProductId()).isPresent()) {
            return false;
        }
        products.add(product);
        return true;
    }

    public boolean removeProduct(String productId) {
        return products.removeIf(product -> product.getProductId().equalsIgnoreCase(productId));
    }

    public Optional<Product> findProduct(String productId) {
        return products.stream()
                .filter(product -> product.getProductId().equalsIgnoreCase(productId))
                .findFirst();
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
