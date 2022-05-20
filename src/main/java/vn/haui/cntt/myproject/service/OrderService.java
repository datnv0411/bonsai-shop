package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;

import java.util.Collection;
import java.util.List;

public interface OrderService {
    Page<Order> listAll(String pageNumber, String sortField, String sortDir, Long userId);

    void cancelOrder(User user, Long orderId);

    void save(Order order, User user, Voucher voucher, Address checkAddress, Payment foundPayment, Long totalPrice);

    Order findById(long orderId, long userId);

    boolean isBought(Long id, Long productId, String os);

    Page<Order> listAll(String pageStr, String sortField, String sortDir, String keySearch);

    Order findById(Long id);

    Order save(Order foundOrder);

    List<Order> findAll();

    List<Order> findByStatus(OrderStatusEnum orderStatusEnum);

    List<Order> findAllByTime(String st, String et);

    List<Order> findByStatusByTime(OrderStatusEnum chờ, String startTime, String endTime);
}
