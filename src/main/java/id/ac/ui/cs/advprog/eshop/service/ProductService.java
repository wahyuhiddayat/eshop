package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product create(Product product);
    Product findById(String productId);
    Product update(Product product);
    void deleteById(String productId);
}
