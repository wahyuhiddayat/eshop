package id.ac.ui.cs.advprog.eshop.model;

@Getter
public class BankPayment {
    public BankPayment(String id, String method, Order order, Map<String, String> paymentData, String status) {
    }

    public BankPayment(String id, String method, Order order, Map<String, String> paymentData) {
    }
}
