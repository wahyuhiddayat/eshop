package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this(id, method, order, paymentData, PaymentStatus.PENDING.getValue());
    }

    public void setStatus(String status) {
        if (!PaymentStatus.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }

    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }
    protected void setPaymentData(Map<String, String> paymentData) {
        if (PaymentMethod.contains(this.method)) {
            throw new IllegalArgumentException("Cannot set method payment data for non method specific payment");
        }
        this.paymentData = null;
    }
}