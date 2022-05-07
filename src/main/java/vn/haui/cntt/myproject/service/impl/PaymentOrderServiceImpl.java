package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.PaymentOrder;
import vn.haui.cntt.myproject.repository.PaymentOrderRepository;
import vn.haui.cntt.myproject.service.PaymentOrderService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService {
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Override
    public PaymentOrder findByOrderId(Long id) {
        return paymentOrderRepository.findByOrderId(id);
    }

    @Override
    public void save(PaymentOrder foundPaymentOrder) {
        paymentOrderRepository.save(foundPaymentOrder);
    }

    @Override
    public List<PaymentOrder> findByStatus(String status, String st, String et) {
        return paymentOrderRepository.findByStatusAndDeletedFlag(status, false, st, et);
    }
}
