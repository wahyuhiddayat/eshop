package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProductSuccess() {
        Product originalProduct = new Product();
        originalProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        originalProduct.setProductName("Original Name");
        originalProduct.setProductQuantity(100);
        productRepository.create(originalProduct);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(150);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Name", result.getProductName());
        assertEquals(150, result.getProductQuantity());
    }

    @Test
    void testUpdateProductFailure() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Non Existent");
        nonExistentProduct.setProductQuantity(50);

        Product result = productRepository.update(nonExistentProduct);
        assertNull(result);
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("find-this-id");
        product.setProductName("Find Me");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("find-this-id");

        assertNotNull(foundProduct);
        assertEquals("find-this-id", foundProduct.getProductId());
        assertEquals("Find Me", foundProduct.getProductName());
        assertEquals(100, foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdFailure() {
        Product foundProduct = productRepository.findById("non-existing-id");
        assertNull(foundProduct);
    }

    @Test
    void testFindByIdNullId() {
        Product foundProduct = productRepository.findById(null);
        assertNull(foundProduct);
    }

    @Test
    void testDeleteByIdSuccess() {
        Product product = new Product();
        product.setProductId("delete-me-id");
        product.setProductName("Delete Me");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.deleteById("delete-me-id");
        Product deletedProduct = productRepository.findById("delete-me-id");

        assertNull(deletedProduct);
    }

    @Test
    void testDeleteByIdNonExisting() {
        assertDoesNotThrow(() -> productRepository.deleteById("non-existing-id"));
    }

    @Test
    void testUpdateNonExistentProduct() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Non Existent");
        nonExistentProduct.setProductQuantity(50);

        Product result = productRepository.update(nonExistentProduct);
        assertNull(result);
        Product queryResult = productRepository.findById("non-existent-id");
        assertNull(queryResult);
    }

    @Test
    void testFindByIdWithNonExistentProduct() {
        Product result = productRepository.findById("non-existent-id");
        assertNull(result);
    }

    @Test
    void testUpdateWithNonExistentProduct() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductId("non-existent-id");
        nonExistentProduct.setProductName("Non Existent");
        nonExistentProduct.setProductQuantity(50);

        Product result = productRepository.update(nonExistentProduct);
        assertNull(result);
    }

    @Test
    void testFindByIdWithNonExistentId() {
        String nonExistentId = "non-existent-id";
        Product result = productRepository.findById(nonExistentId);
        assertNull(result);
    }

    @Test
    void testUpdateWithNullProductInData() {
        List<Product> testData = new ArrayList<>();
        testData.add(null);

        Product validProduct = new Product();
        validProduct.setProductId("valid-id");
        validProduct.setProductName("Valid Product");
        validProduct.setProductQuantity(100);
        testData.add(validProduct);

        productRepository.setProductData(testData);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("valid-id");
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getProductName());
        assertEquals(200, result.getProductQuantity());
    }

    @Test
    void testCreateWithNullProduct() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productRepository.create(null);
        });

        String expectedMessage = "Product cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateWithNullProduct() {
        Product result = productRepository.update(null);
        assertNull(result);
    }

    @Test
    void testUpdateWithProductHavingNullId() {
        Product productWithNullId = new Product();
        productWithNullId.setProductName("Product with null ID");
        productWithNullId.setProductQuantity(100);

        Product result = productRepository.update(productWithNullId);
        assertNull(result);
    }

    @Test
    void testUpdateProductWithMismatchedId() {
        Product otherProduct = new Product();
        otherProduct.setProductId("some-other-id");
        otherProduct.setProductName("Other Product");
        otherProduct.setProductQuantity(100);
        productRepository.create(otherProduct);

        Product productToUpdate = new Product();
        productToUpdate.setProductId("non-matching-id");
        productToUpdate.setProductName("Updated Product");
        productToUpdate.setProductQuantity(200);

        Product result = productRepository.update(productToUpdate);

        assertNull(result);
    }

    @Test
    void testFindByIdWithMismatchedProductIds() {
        Product product = new Product();
        product.setProductId("specific-id");
        product.setProductName("Specific Product");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product foundProduct = productRepository.findById("different-id");

        assertNull(foundProduct);
    }
}