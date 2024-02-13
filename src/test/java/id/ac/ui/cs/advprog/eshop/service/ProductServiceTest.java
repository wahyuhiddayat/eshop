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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

    @Test
    public void testUpdateProductSuccess() {
        when(productRepository.update(any(Product.class))).thenReturn(product);
        Product updatedProduct = productService.update(product);
        assertNotNull(updatedProduct);
        assertEquals(product.getProductName(), updatedProduct.getProductName());
        verify(productRepository).update(any(Product.class));
    }

    @Test
    public void testUpdateProductFailure() {
        when(productRepository.update(any(Product.class))).thenReturn(null);
        Product updatedProduct = productService.update(new Product());
        assertNull(updatedProduct);
    }

    @Test
    public void testDeleteProductSuccess() {
        doNothing().when(productRepository).deleteById(anyString());
        productService.deleteById("test-id");
        verify(productRepository).deleteById("test-id");
    }

    @Test
    public void testDeleteProductFailure() {
        doThrow(new RuntimeException("Product not found")).when(productRepository).deleteById(anyString());
        assertThrows(RuntimeException.class, () -> productService.deleteById("non-existing-id"));
    }

    @Test
    public void testFindAllProducts() {
        Iterator<Product> productIterator = Arrays.asList(product, new Product()).iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> products = productService.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository).findAll();
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.create(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product newProduct = new Product();
        newProduct.setProductName("New Product");
        newProduct.setProductQuantity(5);

        Product createdProduct = productService.create(newProduct);

        assertNotNull(createdProduct.getProductId());
        assertEquals(newProduct.getProductName(), createdProduct.getProductName());
        verify(productRepository).create(any(Product.class));
    }

    @Test
    public void testFindProductByIdSuccess() {
        when(productRepository.findById(anyString())).thenReturn(product);

        Product foundProduct = productService.findById("test-id");

        assertNotNull(foundProduct);
        assertEquals("test-id", foundProduct.getProductId());
        verify(productRepository).findById("test-id");
    }

    @Test
    public void testFindProductByIdFailure() {
        when(productRepository.findById(anyString())).thenReturn(null);

        Product foundProduct = productService.findById("non-existing-id");

        assertNull(foundProduct);
        verify(productRepository).findById("non-existing-id");
    }
}