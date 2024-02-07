package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }

    // Positive Tests

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }

    // Negative Tests

    @Test
    void testSetProductIdWithNull() {
        product.setProductId(null);
        assertNull(product.getProductId(), "ProductId should be null after setting it to null.");
    }

    @Test
    void testSetProductNameWithNull() {
        product.setProductName(null);
        assertNull(product.getProductName(), "ProductName should be null after setting it to null.");
    }

    @Test
    void testSetProductQuantityWithNegativeValue() {
        product.setProductQuantity(-10);
        assertEquals(-10, product.getProductQuantity(), "ProductQuantity should accept negative values, validation expected elsewhere.");
    }
}