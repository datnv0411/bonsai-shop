package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Payment;
import vn.haui.cntt.myproject.entity.PaymentOrder;

import java.io.UnsupportedEncodingException;

public interface PaymentService {
    Payment findByPaymentName(String paymentName);

    String createLink(long orderId, long totalPrice, String ipClient, String returnUrl) throws UnsupportedEncodingException;

    void checkResultPaid(String vnp_responseCode, String vnp_txnRef, String vnp_amount);

    PaymentOrder findByOrderId(long orderId);
}
