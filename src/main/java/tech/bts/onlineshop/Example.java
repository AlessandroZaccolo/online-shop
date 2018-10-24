package tech.bts.onlineshop;

import tech.bts.onlineshop.business.ProductService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

import java.util.*;

import static java.lang.System.*;

public class Example {



    public static void main(String[] args) {

        Product p1 = new Product("MacBook", "Apple", 1500);
        Product p2 = new Product("iPhone xs", "Apple", 1200);
        Product p3 = new Product("Pixel 3", "Google", 900);

        ProductDatabase productDatabase = new ProductDatabase();
        productDatabase.add(p1);
        productDatabase.add(p2);
        productDatabase.add(p3);


        System.out.println("p1 is available " + p1.isAvailable());

        Product product = productDatabase.get(1);
        out.println("The name of the product is: " + product.getName());

        int count = productDatabase.getCount();
        out.println("I have " + count + " products in the database");

        int countApple = productDatabase.getCountByBrand("Apple");
        out.println("I have " + countApple + " Apple products");


        List<Product> productsByApple = productDatabase.getByBrand("Apple");
        out.println("Products by Apple: " + productsByApple);
        for (Product p : productsByApple) {
            out.println(p.getName() + ", " + p.getBrand() + ", " + p.getPrice());
        }

        productDatabase.remove(2);
        productDatabase.remove(1);

        System.out.println("All products after removing two: ");
        Collection<Product> allProducts = productDatabase.getAll();

        for (Product p : allProducts) {
            System.out.println(p);
        }

        Product p4 = new Product("Lightning cable", "Apple", 10);
        productDatabase.add(p4);

        System.out.println("Number of product now: " + productDatabase.getCount());


        long requestedId = 2;
        Product removedProduct = productDatabase.get(requestedId);

        /**
         * if the requested product exists, write "the name of the product is "
         * if it doesn't, write "the product with ID  doesn't exist"
         */

        if (removedProduct != null){
            System.out.println("The name of the product is " + removedProduct);
        } else {
            System.out.println("The product with ID " + requestedId + " doesn't exist");
        }

        System.out.println("This product was removed: " + removedProduct);

        List<CartItem> items = Arrays.asList(
                new CartItem(p1, 2),
                new CartItem(p2, 3),
                new CartItem(p3, 1));

        ShoppingCart cart = new ShoppingCart(items);




        double minPrice = 1000;
        double maxPrice = 1600;

        List<Product> productsByPrice = productDatabase.productsByPriceRange(minPrice, maxPrice);


        for (Product p : productsByPrice) {
            out.println(p.getName() + " is in the price range of " + minPrice + "-" + maxPrice + "€");
        }

        System.out.println("Products by price, all of them: " + productsByPrice);


    }

}
