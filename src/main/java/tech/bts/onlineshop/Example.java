package tech.bts.onlineshop;

import tech.bts.onlineshop.business.PurchaseService;
import tech.bts.onlineshop.data.ProductDatabase;
import tech.bts.onlineshop.model.CartItem;
import tech.bts.onlineshop.model.Product;
import tech.bts.onlineshop.model.ShoppingCart;

import java.lang.reflect.Array;
import java.util.*;

public class Example {



    public static void main(String[] args) {

        Product p1 = new Product("MacBook", "Apple", 1500);
        Product p2 = new Product("iPhone xs", "Apple", 1200);
        Product p3 = new Product("Pixel 3", "Google", 900);

        ProductDatabase productDatabase = new ProductDatabase();
        productDatabase.add(p1);
        productDatabase.add(p2);
        productDatabase.add(p3);

        Product product = productDatabase.get(1);
        System.out.println("The name of the product is: " + product.getName());

        int count = productDatabase.getCount();
        System.out.println("I have " + count + " products in the database");

        int countApple = productDatabase.getCountByBrand("Apple");
        System.out.println("I have " + countApple + " Apple products");


        List<Product> productsByApple = productDatabase.getByBrand("Apple");
        System.out.println("Products by Apple: " + productsByApple);
        for (Product p : productsByApple) {
            System.out.println(p.getName() + ", " + p.getBrand() + ", " + p.getPrice());
        }


        List<CartItem> items = Arrays.asList(
                new CartItem(p1, 2),
                new CartItem(p2, 3),
                new CartItem(p3, 1));

        ShoppingCart cart = new ShoppingCart(items);

        PurchaseService purchaseService = new PurchaseService();

        double total = purchaseService.calculateTotalAmount(cart);
        System.out.println("Total amount of cart: " + total);


        double minPrice = 1000;
        double maxPrice = 1600;

        List<Product> productsByFirstRange = productDatabase.productsByPriceRange(minPrice, maxPrice);

        for (Product p : productsByFirstRange) {
            System.out.println(p.getName() + " is in the price range of " + minPrice + "-" + maxPrice + "€");
        }

        HashMap<Long, Product> removeProductId2 = (HashMap<Long, Product>) productDatabase.removeProduct(2L);

        System.out.println(removeProductId2);

    }

}
