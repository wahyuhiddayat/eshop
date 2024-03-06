package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class VoucherPaymentTest {
    Map<String, String> paymentData;
    Order order;
    List<Product> products;

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

        order = new Order("a2c6328-4a37-4664-83c7-f32db8620155", products, 1708560000L, "Safira Sudrajat");
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreateVoucherPaymentPendingStatus() {
        Payment payment = new VoucherPayment("d2c33a0e-3c06-4fa6-a097-6b38b52e6854", PaymentMethod.VOUCHER.getValue(), order, paymentData);

        assertEquals("d2c33a0e-3c06-4fa6-a097-6b38b52e6854", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentSuccessStatus() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentRejectedStatus() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(order, payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testCreateVoucherPaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData, "MEOW");
        });
    }

    @Test
    void testCreateVoucherPaymentNullStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData, null);
        });
    }

    @Test
    void testSetVoucherPaymentStatStatusToSuccess() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testSetVoucherPaymentStatStatusToRejected() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetVoucherPaymentStatStatusToPending() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.PENDING.getValue());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testSetVoucherPaymentStatStatusToInvalid() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testSetVoucherPaymentStatStatusToNull() {
        Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
    }

    @Test
    void testCreateVoucherPaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), null, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithEmptyPaymentData() {
        paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithNullPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, null);
        });
    }

    @Test
    void testCreateVoucherPaymentWithEmptyVoucherCode() {
        paymentData.put("voucherCode", "");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithNullVoucherCode() {
        paymentData.put("voucherCode", null);
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithInvalidVoucherCodeBecauseMoreThan16Length() {
        paymentData.put("voucherCode", "ESHOP1234ABCD5679");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithInvalidVoucherCodeBecauseNotStartWithESHOP() {
        paymentData.put("voucherCode", "XSHOP1234ABCD5679");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithInvalidVoucherCodeBecauseNot8Numerics() {
        paymentData.put("voucherCode", "ESHOP1234ABCD567X");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreateVoucherPaymentWithInvalidVoucherCodeBecauseLessThan16Length() {
        paymentData.put("voucherCode", "ESHOP1234ABCD567");
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new VoucherPayment("eb558e9f-1c39-460e-8860-71af6af63bd6", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
    }
}