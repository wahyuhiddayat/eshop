package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product create(Product product);
    public Product findById(String productId);
    public Product update(Product product);
    public void deleteById(String productId);
}
