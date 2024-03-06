package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaymentTest {
    private Map<String, String> paymentData;
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
    }

    void prepareBankTransferData() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");
    }

    void prepareVoucherPaymentData() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataPendingStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataPendingStatus() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataSuccessStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataSuccessStatus() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataRejectedStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataRejectedStatus() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", payment.getId());
        assertEquals("", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataInvalidStatus() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataInvalidStatus() {
        prepareVoucherPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataNullStatus() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataNullStatus() {
        prepareVoucherPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToSuccess() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToSuccess() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToRejected() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToRejected() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToInvalidStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToInvalidStatus() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToNullStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToNullStatus() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6", "", null, paymentData);
        });
    }
}