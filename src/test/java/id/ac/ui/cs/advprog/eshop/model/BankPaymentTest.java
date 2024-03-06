package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankPaymentTest {
    Map<String, String> paymentData;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c6328-4a37-4664-83c7-f32db862015");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);

        order= new Order("a2c6328-4a37-4664-83c7-f32db8620155", products, 1708560000L, "Safira Sudrajat");

        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");
    }

    @Test
    void testCreateBankPaymentPendingStatus() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankPaymentSuccessStatus() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankPaymentRejectedStatus() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateBankPaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData, "MEOW");
        });
    }

    @Test
    void testCreateBankPaymentNullStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData, null);
        });
    }

    @Test
    void testSetBankPaymentStatStatusToSuccess() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetBankPaymentStatStatusToRejected() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetBankPaymentStatStatusToPending() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.PENDING.getValue());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testSetBankPaymentStatStatusToInvalid() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testSetBankPaymentStatStatusToNull() {
        Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
    }

    @Test
    void testCreateBankPaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), null, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithEmptyPaymentData() {
        paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, null
            );
        });
    }

    @Test
    void testCreateBankPaymentInvalidBankName() {
        paymentData.put("bankName", "");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentInvalidReferenceCode() {
        paymentData.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithNullBankName() {
        paymentData.put("bankName", null);
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithNullReferenceCode() {
        paymentData.put("referenceCode", null);
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithInvalidBankNameAndReferenceCode() {
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateBankPaymentWithNullBankNameAndReferenceCode() {
        paymentData.put("bankName", null);
        paymentData.put("referenceCode", null);
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new BankPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }
}