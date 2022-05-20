package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.OrderDetail;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> addFromCart(Order order, List<Cart> carts, String username);

    List<OrderDetail> findByOrderId(Long orderId);

    void save(OrderDetail od);

    List<OrderDetail> findByTime(String startTime, String endTime);

    List<OrderDetail> findAll();
}
