package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private long id;
    private OrderStatusEnum orderStatus;
    private String orderCode;
    private User user;
    private Collection<OrderDetail> orderDetails;
    private Voucher voucher;
    private Address deliveryAddress;
    private Collection<PaymentOrder> paymentOrders;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Order toOrder(){
        return Order.builder()
                .id(id)
                .orderStatus(orderStatus)
                .orderCode(orderCode)
                .user(user)
                .orderDetails(orderDetails)
                .voucher(voucher)
                .deliveryAddress(deliveryAddress)
                .paymentOrders(paymentOrders)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
