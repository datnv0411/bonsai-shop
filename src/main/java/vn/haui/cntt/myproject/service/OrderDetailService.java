package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    void save(OrderDetail orderDetail, String username);

    List<OrderDetail> addFromCart(List<Cart> carts, String username);

    List<OrderDetail> findByOrderId(Long orderId);
}
