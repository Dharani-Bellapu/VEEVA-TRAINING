import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Product {
    private int prod_id;
    private String prod_name;
    private String comp_name;
    private double price;
    private String date;
    private int quantity_inhand;

    public Product(int prod_id, String prod_name, double price, String date,
                   int quantity_inhand, String comp_name) {
        this.prod_id = prod_id;
        this.prod_name = Objects.requireNonNull(prod_name, "Product name is required");
        this.comp_name = Objects.requireNonNull(comp_name, "Company name is required");
        this.price = price;
        this.date = Objects.requireNonNull(date, "Manufacturing date is required");
        this.quantity_inhand = quantity_inhand;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public LocalDate getManufactureDate() {
        if (date == null) {
            throw new IllegalStateException("Manufacturing date is required");
        }
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new IllegalStateException("Manufacturing date must use yyyy-MM-dd", exception);
        }
    }

    public int getQuantity_inhand() {
        return quantity_inhand;
    }

    public void setQuantity_inhand(int quantity_inhand) {
        this.quantity_inhand = quantity_inhand;
    }

    @Override
    public String toString() {
        return "Product{id=" + prod_id
                + ", name='" + prod_name + '\''
                + ", price=" + price
                + ", manufactureDate=" + date
                + ", quantity=" + quantity_inhand
                + ", company='" + comp_name + "'}";
    }
}