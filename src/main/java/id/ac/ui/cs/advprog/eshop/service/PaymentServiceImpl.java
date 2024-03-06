package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment addPayment(Order order, String method, Map<String, String> data) {
    }

    @Override
    public List<Payment> getAllPayments() {
    }

    @Override
    public Payment getPaymentById(String id) {
    }

    @Override
    public Payment setStatus(String id, String status) {
    }
}