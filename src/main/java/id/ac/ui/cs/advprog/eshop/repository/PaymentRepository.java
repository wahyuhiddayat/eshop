package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        for (Payment existingPayment : payments) {
            if (existingPayment.getId().equals(payment.getId())) {
                throw new IllegalArgumentException("Payment with ID " + payment.getId() + " already exists.");
            }
        }
        payments.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment p : payments) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return payments;
    }
}