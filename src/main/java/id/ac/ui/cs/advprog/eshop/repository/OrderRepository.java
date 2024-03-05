package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();
    public Order save(order order) {return null;}
    public Order findById(String id) {return null;}
    public List<Order> findAllByAuthor(String author) {return null;}
}
