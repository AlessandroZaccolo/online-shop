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



    // Adds a product and returns the id for that product.
    public long createProduct (Product product){
        long productId = this.productDatabase.add(product);
        return productId;

    }


    public void addProductStock (long productId, int quantity){


        Product product = this.productDatabase.get(productId);

        int total = product.getQuantity() + quantity;

        product.setQuantity(total);
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



}
