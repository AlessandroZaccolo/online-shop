package tech.bts.onlineshop.business;

import tech.bts.onlineshop.model.Discount;

import java.util.HashMap;
import java.util.Map;

public class DiscountService<discount> {


    private String discountId;

    private Map<String, Discount> discountMap;

    public DiscountService(){
        this.discountMap = new HashMap<>();
    }


    // 3. create a discount
    public String createDiscount(Discount discount) {
        discountMap.put(discount.getId(), discount);
        return discount.getId();
    }

    // 4.create a method calculateFinalAmount that takes a discount id and an amount
    // and returns the final amount after applying the discount.

    public double calculateFinalAmount(String discountId, double amount) {

        Discount discount = discountMap.get(discountId);

        if (discount.getId() != null) {
            if (discount.isPercentage()) {
                amount = amount * (discount.getAmount() / 100);
                return amount;
            } else {
                amount = amount - discount.getAmount();
                return amount;
            }
        } else {
            return amount;
        }
    }
}