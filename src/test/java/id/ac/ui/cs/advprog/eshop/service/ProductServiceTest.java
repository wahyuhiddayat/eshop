package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setProductId("test-id");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    // Positive Test for Edit Product
    @Test
    public void testUpdateProductSuccess() {
        when(productRepository.update(any(Product.class))).thenReturn(product);
        Product updatedProduct = productService.update(product);
        assertNotNull(updatedProduct);
        assertEquals(product.getProductName(), updatedProduct.getProductName());
        verify(productRepository).update(any(Product.class));
    }

    // Negative Test for Edit Product
    @Test
    public void testUpdateProductFailure() {
        when(productRepository.update(any(Product.class))).thenReturn(null);
        Product updatedProduct = productService.update(new Product());
        assertNull(updatedProduct);
    }

    // Positive Test for Delete Product
    @Test
    public void testDeleteProductSuccess() {
        doNothing().when(productRepository).deleteById(anyString());
        productService.deleteById("test-id");
        verify(productRepository).deleteById("test-id");
    }

    // Negative Test for Delete Product (Trying to delete a product that does not exist)
    @Test
    public void testDeleteProductFailure() {
        doThrow(new RuntimeException("Product not found")).when(productRepository).deleteById(anyString());
        assertThrows(RuntimeException.class, () -> productService.deleteById("non-existing-id"));
    }
}