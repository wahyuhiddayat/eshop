package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("createProduct"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        mockMvc.perform(post("/product/create")
                        .param("productId", UUID.randomUUID().toString())
                        .param("productName", "New Product")
                        .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    public void testEditProductPageFound() throws Exception {
        Product product = new Product();
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        product.setProductName("Existing Product");
        product.setProductQuantity(50);

        when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("EditProduct"));

        verify(productService, times(1)).findById(productId);
    }

    @Test
    public void testEditProductPageNotFound() throws Exception {
        String productId = UUID.randomUUID().toString();
        when(productService.findById(productId)).thenReturn(null);

        mockMvc.perform(get("/product/edit/" + productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).findById(productId);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mockMvc.perform(post("/product/update")
                        .param("productId", UUID.randomUUID().toString())
                        .param("productName", "Updated Product")
                        .param("productQuantity", "150"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    @Test
    public void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("productList"));

        verify(productService, times(1)).findAll();
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String productId = UUID.randomUUID().toString();
        mockMvc.perform(get("/product/delete/" + productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).deleteById(productId);
    }
}