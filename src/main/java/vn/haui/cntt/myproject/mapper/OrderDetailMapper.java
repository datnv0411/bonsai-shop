package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.OrderDetailDto;
import vn.haui.cntt.myproject.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailMapper {
    public OrderDetailMapper(){super();}

    public static OrderDetailDto toOrderDetailDto(OrderDetail orderDetail){
        return new OrderDetailDto(orderDetail.getId(), orderDetail.getQuantity(), orderDetail.getPrice(), orderDetail.getDiscount(),
                orderDetail.getOrder(), orderDetail.getProduct(), orderDetail.getDeletedFlag(), orderDetail.getCreatedDate(),
                orderDetail.getUpdatedDate(), orderDetail.getCreatedBy(), orderDetail.getUpdatedBy());
    }

    public static List<OrderDetail> toListOrderDetail(List<OrderDetailDto> orderDetail){
        List<OrderDetail> list = new ArrayList<>();
        int i =0;
        for (OrderDetailDto od : orderDetail
             ) {
            list.add(i, od.toOrderDetail());
            i++;
        }
        return list;
    }
}
