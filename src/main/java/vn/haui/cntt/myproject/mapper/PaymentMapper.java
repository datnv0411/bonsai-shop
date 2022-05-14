package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.PaymentDto;
import vn.haui.cntt.myproject.entity.Payment;

public class PaymentMapper {
    public PaymentMapper(){super();}

    public static PaymentDto toPaymentDto(Payment payment){
        return new PaymentDto(payment.getId(), payment.getPaymentName(), payment.getPaymentOrders(), payment.getDeletedFlag(),
                payment.getCreatedDate(), payment.getUpdatedDate(), payment.getCreatedBy(), payment.getUpdatedBy());
    }
}
