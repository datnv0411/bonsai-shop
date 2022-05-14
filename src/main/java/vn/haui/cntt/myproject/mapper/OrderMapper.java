package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.OrderDto;
import vn.haui.cntt.myproject.entity.Order;

public class OrderMapper {
    public OrderMapper(){super();}

    public static OrderDto toOrderDto(Order order){
        return new OrderDto(order.getId(), order.getOrderStatus(), order.getOrderCode(), order.getUser(), order.getOrderDetails(),
                order.getVoucher(), order.getDeliveryAddress(), order.getPaymentOrders(), order.getDeletedFlag(), order.getCreatedDate(),
                order.getUpdatedDate(), order.getCreatedBy(), order.getUpdatedBy());
    }
}
