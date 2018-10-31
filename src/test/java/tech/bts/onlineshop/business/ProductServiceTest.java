package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

public class ProductServiceTest {

    @Test
    public void empty_catalog_has_no_products() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        int count = productService.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void add_product_to_catalog() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);
        int count = productService.getCount();
        Assert.assertEquals(1, count);
        Product p = productService.getProductById(pixelId);
        Assert.assertEquals("pixel", p.getName());
    }

    @Test
    public void product_availability() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        Assert.assertFalse(productService.checkProductAvailable(pixelId, 500));

        productService.addProductStock(pixelId, 500);

        Assert.assertTrue(productService.checkProductAvailable(pixelId, 500));
    }

    @Test
    public void product_available_quantity() {

        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        Product product = new Product("pixel", "Google", 800);
        long pixelId = productService.createProduct(product);

        Assert.assertEquals(0, productService.getAvailableQuantity(pixelId, 50));

        productService.addProductStock(pixelId, 100);

        Assert.assertEquals(50, productService.getAvailableQuantity(pixelId, 50));
        Assert.assertEquals(100, productService.getAvailableQuantity(pixelId, 200));
    }

    @Test
    public void purchase_reduces_products_stock(){

        // 1 - setup the necessary objects
        ProductDatabase productDatabase = new ProductDatabase();
        ProductService productService = new ProductService(productDatabase);
        long penId = productService.createProductAndAddStock(new Product("pen", "Pitol", 3),100);
        long macbookId = productService.createProductAndAddStock(new Product("Macbook", "Apple", 1500),50);
        long tvId = productService.createProductAndAddStock(new Product("tv", "Samsung", 2000),40);

        ShoppingCart cart = new ShoppingCart();
        cart.add(new CartItem(penId, 20));
        cart.add(new CartItem(macbookId, 30));

        // 2 - calling the method(s) we are testing
        productService.purchase(cart);

        // 3 - check the expected result

        Assert.assertEquals(80,productService.getProductById(penId).getQuantity());
        Assert.assertEquals(20,productService.getProductById(macbookId).getQuantity());
        Assert.assertEquals(40,productService.getProductById(tvId).getQuantity());


    }

    @Test
    public void purchase_more_than_available(){

        // 1 - setup the necessary objects

        ProductService productService = new ProductService(new ProductDatabase());
        long penId = productService.createProductAndAddStock(new Product("pen", "Pitol", 3),100);


        ShoppingCart cart = new ShoppingCart();
        cart.add(new CartItem(penId, 200));

        // 2 - calling the method(s) we are testing
        productService.purchase(cart);

        // 3 - check the expected result

        Assert.assertEquals(0,productService.getProductById(penId).getQuantity());

    }
}
