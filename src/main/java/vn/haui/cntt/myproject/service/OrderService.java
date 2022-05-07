package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.*;

import java.util.List;

public interface OrderService {
    Page<Order> listAll(String pageNumber, String sortField, String sortDir, Long userId);

    void cancelOrder(User user, Long orderId);

    void save(Order order, User user, List<OrderDetail> orderDetail, Voucher voucher, Address checkAddress, Payment foundPayment, Long totalPrice);

    Order findById(long orderId, long userId);

    boolean isBought(Long id, Long productId, String os);
}
