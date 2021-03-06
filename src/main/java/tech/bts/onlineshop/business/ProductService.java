package tech.bts.onlineshop.business;

import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

public class ProductService {

    private ProductDatabase productDatabase;

    public ProductService (ProductDatabase productDatabase){
        this.productDatabase = productDatabase;
    }

    public long createProduct (Product product){
        return this.productDatabase.add(product);
    }

    // Adds a product with initial stock and returns the id for that product.
    public long createProductAndAddStock (Product product, int initialStock){

        long productId = this.createProduct(product);
        this.addProductStock(productId, initialStock);
        return productId;
    }

    public void addProductStock (long productId, int quantity){


        Product product = this.productDatabase.get(productId);

        int total = product.getQuantity() + quantity;

        product.setQuantity(total);
    }

    public int getCount(){
        return productDatabase.getCount();
    }


    public Product getProductById (long productId){

        return productDatabase.get(productId);
    }

    /** Returns true if the given quantity is available for that product
     *
     */

    public boolean checkProductAvailable (long productId, int quantity){

        Product product = productDatabase.get(productId);
        return product.getQuantity() >= quantity;
    }

    // Return the quantity that is possible to deliver for that product

    public int getAvailableQuantity (long productId, int quantity){

        Product product = productDatabase.get(productId);

        return Math.min(product.getQuantity(), quantity);
    }


    // Reduce the quantities of the products by the quantities in the cart
    // If some quantity is greater than the available stock, the stock will be set to 0.
    // Return shopping cart with the actual quantities purchased (depending on stock).

    public ShoppingCart purchase(ShoppingCart cart) {

        ShoppingCart result = new ShoppingCart();


        for (CartItem item : cart.getItems()) {
            Product product = productDatabase.get(item.getproductId());
            int actualQuantity = Math.min(product.getQuantity(), item.getQuantity());
            int remainingQuantity = product.getQuantity() - actualQuantity;
            product.setQuantity(remainingQuantity);
            result.add(new CartItem(item.getproductId(), actualQuantity));
        }

        return result;
    }
}
