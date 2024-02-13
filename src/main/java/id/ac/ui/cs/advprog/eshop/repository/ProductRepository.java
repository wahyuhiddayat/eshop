package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        productData.add(product);
        return product;
    }

    public Product update(Product product) {
        if (product == null || product.getProductId() == null) {
            return null;
        }

        for (int i = 0; i < productData.size(); i++) {
            Product existingProduct = productData.get(i);
            if (existingProduct != null && existingProduct.getProductId().equals(product.getProductId())) {
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }


    public Product findById(String productId) {
        if (productId == null) {
            return null;
        }
        for (Product product : productData) {
            if (productId.equals(product.getProductId())) {
                return product;
            }
        }
        return null;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public void deleteById(String productId) {
        productData.removeIf(product -> product.getProductId().equals(productId));
    }

    void setProductData(List<Product> productData) {
        this.productData = productData;
    }
}
