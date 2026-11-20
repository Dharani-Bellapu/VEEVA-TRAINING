public class Product {
    private String category;
    private String product_name;
    private double price;
    private int productId;
    private String company;
    public String getCategory(){
        return category;
    }
    public String getProduct_name(){
        return product_name;
    }
    public String getCompany(){
        return company;
    }
    public double getPrice(){
        return price;
    }
    public int getProductId(){
        return productId;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setProduct_name(String product_name){
        this.product_name=product_name;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public void setProductId(int productId){
        this.productId=productId;
    }
    public void setCompany(String company){
        this.company=company;
    }
}
