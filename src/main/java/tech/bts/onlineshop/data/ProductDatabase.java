package tech.bts.onlineshop.data;

import tech.bts.onlineshop.model.Product;

import java.util.*;

public class ProductDatabase {

    private long nextId;
    private Map<Long, Product> productMap;

    public ProductDatabase() {
        this.nextId = 1;
        this.productMap = new HashMap<>();
    }

    public void add(Product product) {

        product.setId(this.nextId);
        productMap.put(product.getId(), product);
        this.nextId++;
    }

    public Product get(long id) {

        return productMap.get(id);
    }

    public int getCount() {

        return productMap.size();
    }


    /**
     * Given 2 prices (min, max) returns a list of products that are in that price range (included)
     */
    public List<Product> productsByPriceRange (double minPrice, double maxPrice){

        List<Product> result = new ArrayList<>();

        for (Product product : productMap.values()){
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice){
                result.add(product);
            }
        }
        return result;
    }

    /**
     * Given id, remove
     */


    public void remove (long id){
        productMap.remove(id);
    }

    public Collection<Product> getAll (){

        return productMap.values();
    }



    public List<Product> getByBrand(String brand) {

        List<Product> result = new ArrayList<>();

        for (Product product : productMap.values()) {
            if (product.getBrand().equals(brand)) {
                result.add(product);
            }
        }

        return result;
    }


    public int getCountByBrand(String brand) {

        List<Product> products = getByBrand(brand);
        return products.size();
    }

}