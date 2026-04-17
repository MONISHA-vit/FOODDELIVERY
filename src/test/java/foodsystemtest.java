package src.test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import src.main.java.foodsystem;

public class foodsystemTest {

    @Test
    public void testPlaceOrderSuccess() {
        foodsystem fs = new foodsystem();
        assertEquals("SUCCESS", fs.placeOrderLogic("101", "Pizza"));
    }

    @Test
    public void testDuplicateOrderFailure() {
        foodsystem fs = new foodsystem();
        fs.placeOrderLogic("101", "Pizza");
        assertEquals("DUPLICATE", fs.placeOrderLogic("101", "Burger"), "Should fail for duplicate ID");
    }

    @Test
    public void testEmptyOrderFailure() {
        foodsystem fs = new foodsystem();
        assertEquals("INVALID", fs.placeOrderLogic("", ""), "Should fail for empty inputs");
    }

    @Test
    public void testTrackingSuccess() {
        foodsystem fs = new foodsystem();
        fs.placeOrderLogic("102", "Pasta");
        assertEquals("Order Placed - Preparing", fs.trackOrderLogic("102"));
    }
}
