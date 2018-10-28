package tech.bts.onlineshop.business;

import tech.bts.onlineshop.model.Discount;

import java.util.HashMap;
import java.util.Map;

public class DiscountService {

    private String id;

    private Map<String, Discount> discountMap;

    public DiscountService() {
        this.discountMap = new HashMap<>();
    }


    public Map<String, Discount> getDiscountMap() {
        return discountMap;
    }

    // 3. create a discount
    public Discount createDiscount(Discount discount){
        this.discountMap.put(id, discount);
        return discount;
    }

    // 4.create a method calculateFinalAmount that takes a discount id and an amount
    // and returns the final amount after applying the discount.

    public double calculateFinalAmount (String id, double amount){

        if (id.equals("SUMMERSALES")){
            amount = amount * (amount /100);
        }
        return amount;
    }


}
