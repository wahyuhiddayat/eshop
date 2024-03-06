package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void testCreatePaymentWithNoOrder() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), null, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithNoPaymentMethod() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", null, order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData() {
        paymentData.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        });
    }

    @Test
    void testCreatePaymentWithInvalidPaymentMethod() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", "MEOW", order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithDefaultStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.WAITING_CONFIRMATION.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithSuccessStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(order, payment.getOrder());
        assertEquals("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithInvalidStatus() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData, "MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithRejectedStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(order, payment.getOrder());
        assertEquals("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testSetPaymentStatusToRejected() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentStatusToInvalidStatus() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetPaymentStatusToWaitingConfirmation() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.WAITING_CONFIRMATION.getValue());
        assertEquals(PaymentStatus.WAITING_CONFIRMATION.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherButPaymentDataIncorrect() {
        prepareBankTransferData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferButPaymentDataIncorrect() {
        prepareVoucherPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferButPaymentDataHasNoBankName() {
        prepareBankTransferData();
        paymentData.remove("bankName");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferButPaymentDataHasNoRefferenceCode() {
        prepareBankTransferData();
        paymentData.remove("referenceCode");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherButPaymentDataHasNoVoucherCode() {
        prepareVoucherPaymentData();
        paymentData.remove("voucherCode");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherSuccess() {
        prepareVoucherPaymentData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferSuccess() {
        prepareBankTransferData();
        Payment payment = new Payment("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", PaymentMethod.BANK.getValue(), order, paymentData);
        assertSame(order, payment.getOrder());
        assertEquals("1509f3cf-8e1b-4ce3-bf8c-afbdac066e8b", payment.getId());
        assertEquals(PaymentMethod.BANK.getValue(), payment.getPaymentMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.WAITING_CONFIRMATION.getValue(), payment.getStatus());
        paymentData.clear();
    }
}