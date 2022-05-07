package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.PaymentOrder;

import java.util.List;

public interface PaymentOrderService {
    PaymentOrder findByOrderId(Long id);

    void save(PaymentOrder foundPaymentOrder);

    List<PaymentOrder> findByStatus(String status, String st, String et);
}
