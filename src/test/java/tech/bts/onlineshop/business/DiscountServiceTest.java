package tech.bts.onlineshop.business;

import org.junit.Assert;
import org.junit.Test;
import tech.bts.onlineshop.model.Discount;

import static org.junit.Assert.*;

public class DiscountServiceTest {


    @Test
    public void test_absolute_discount(){

        // 1 - setup the objects you need
        DiscountService discountService = new DiscountService();
        discountService.add(new Discount("WINTER18", "Discount for winter", 40, false));
        discountService.add(new Discount("SUMMER18", "Discount for summer", 20, false));

        // 2 - you call same methods
        double totalAmount1 = discountService.calculateFinalAmount("WINTER18", 500);
        double totalAmount2 = discountService.calculateFinalAmount("SUMMER18", 500);

        // 3 - you check that the result is the one expected
        assertEquals(480.0, totalAmount2, 0.0);
        assertEquals(460.0, totalAmount1, 0.0);
    }

    @Test
    public void test_percentage_discount(){

        // 1 - setup the objects you need
        DiscountService discountService = new DiscountService();
        discountService.add(new Discount("WINTER18", "Discount for winter", 10, true));
        discountService.add(new Discount("SUMMER18", "Discount for summer", 20, true));

        // 2 - you call same methods
        double totalAmount1 = discountService.calculateFinalAmount("WINTER18", 500);
        double totalAmount2 = discountService.calculateFinalAmount("SUMMER18", 500);

        // 3 - you check that the result is the one expected
        assertEquals(450.0,totalAmount1, 0.0);
        assertEquals(400.0, totalAmount2, 0.0);
    }

    @Test
    public void test_discount_higher_than_amount(){

        // 1 - setup the objects you need
        DiscountService discountService = new DiscountService();
        discountService.add(new Discount("WINTER18", "Discount for winter", 50, false));

        // 2 - you call same methods
        double totalAmount = discountService.calculateFinalAmount("WINTER18", 30);
        assertEquals(0, totalAmount, 0.0);
    }

    @Test
    public void test_non_existing_discount(){

        // 1 - setup the objects you need
        DiscountService discountService = new DiscountService();

        // 2 - you call same methods
        double totalAmount = discountService.calculateFinalAmount("WRONG", 500);

        // 3 - you check that the result is the one expected
        assertEquals(500.0,totalAmount, 0.0);
    }

}