package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.model.Discount;

import static org.junit.Assert.*;

public class DiscountServiceTest {

    //5- Create a test for this class (DiscountServiceTest) with at least 3 tests.
    // One test will use a discount with percentage.
    // Another test will use a discount with absolute amount (not percentage).
    // And another test will try to use a discount that doesn't exist, no discount to be applied.

    @Test
    public void getDiscountMap() {
    }

    @Test
    public void createDiscount() {
    }

    @Test
    public void calculateFinalAmount() {

        DiscountService discountService = new DiscountService();
        Discount discount = new Discount("Halloween2018", 50, true);
        String HalloweenId = discountService.createDiscount(discount);

        Assert.assertEquals(25, discountService.calculateFinalAmount(HalloweenId, 50), 0.0);

        Discount discount1 = new Discount("Christmas2018", 50, false);
        String ChristmasId = discountService.createDiscount(discount1);

        Assert.assertEquals(50, discountService.calculateFinalAmount(ChristmasId, 100), 0.0);



        Assert.assertEquals(50, discountService.calculateFinalAmount("SummerId", 50), 0.0);


    }

}