package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.Payment;
import vn.haui.cntt.myproject.entity.PaymentOrder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentOrderDto {
    private long id;
    private Payment payment;
    private Order order;
    private long totalPaid;
    private String status;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public PaymentOrder toPaymentOrder(){
        return PaymentOrder.builder()
                .id(id)
                .payment(payment)
                .order(order)
                .totalPaid(totalPaid)
                .status(status)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
