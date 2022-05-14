package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.PaymentOrderDto;
import vn.haui.cntt.myproject.entity.PaymentOrder;

public class PaymentOrderMapper {
    public PaymentOrderMapper(){super();}

    public static PaymentOrderDto toPaymentOrderDto(PaymentOrder paymentOrder){
        return new PaymentOrderDto(paymentOrder.getId(), paymentOrder.getPayment(), paymentOrder.getOrder(), paymentOrder.getTotalPaid(),
                paymentOrder.getStatus(), paymentOrder.getDeletedFlag(), paymentOrder.getCreatedDate(), paymentOrder.getUpdatedDate(),
                paymentOrder.getCreatedBy(), paymentOrder.getUpdatedBy());
    }
}
