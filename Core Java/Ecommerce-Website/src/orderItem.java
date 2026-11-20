public class orderItem {
        private Product product;
        private int quantity;
        private double price;
        public orderItem(Product product,int quantity) {
            this.product = product;
            this.quantity = quantity;
            this.price = product.getPrice();
        }
        public Product getProduct() {
            return product;
        }
        public int getQuantity() {
            return quantity;
        }
        public double getPrice() {
            return price;
        }
        public double getSubtotal() {
            return price * quantity;
        }
    }
