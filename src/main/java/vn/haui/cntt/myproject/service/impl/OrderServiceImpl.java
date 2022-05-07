package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;
import vn.haui.cntt.myproject.repository.OrderRepository;
import vn.haui.cntt.myproject.repository.PaymentOrderRepository;
import vn.haui.cntt.myproject.repository.ProductRepository;
import vn.haui.cntt.myproject.service.OrderService;
import vn.haui.cntt.myproject.util.RandomOrderCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentOrderRepository paymentOrderRepository;

    @Override
    public Page<Order> listAll(String pageNumber, String sortField, String sortDir, Long userId) {
        if (pageNumber==null || !pageNumber.chars().allMatch(Character::isDigit) || pageNumber.equals("")) pageNumber="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="asc";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageNumber);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,4, sort);

        return orderRepository.findByUserId(0, userId, pageable);
    }

    @Override
    public void cancelOrder(User user, Long orderId) {
        Order order = orderRepository.findByUserIdAndOrderId(0, user.getId(), orderId);
        order.setOrderStatus(OrderStatusEnum.Đã_hủy);
        order.setUpdatedBy(user.getUsername());
        order.setUpdatedDate(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Override
    public void save(Order order, User user, List<OrderDetail> orderDetail, Voucher voucher, Address checkAddress, Payment foundPayment, Long totalPrice) {
        if(voucher.getCodeVoucher() != null && !voucher.getCodeVoucher().equals("")){
            order.setVoucher(voucher);
        }

        order.setUser(user);
        order.setOrderStatus(OrderStatusEnum.Chờ);
        order.setOrderCode(RandomOrderCode.main());
        order.setOrderDetails(orderDetail);
        order.setDeliveryAddress(checkAddress);
        order.setDeletedFlag(false);
        order.setCreatedBy(user.getUsername());
        order.setCreatedDate(LocalDateTime.now());
        orderRepository.save(order);

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setOrder(order);
        paymentOrder.setPayment(foundPayment);
        paymentOrder.setDeletedFlag(false);
        paymentOrder.setCreatedBy(user.getUsername());
        paymentOrder.setCreatedDate(LocalDateTime.now());
        paymentOrder.setStatus("Chưa thanh toán");
        paymentOrder.setTotalPaid(totalPrice);
        paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public Order findById(long orderId, long userId) {
        return orderRepository.findByUserIdAndOrderId(0, userId, orderId);
    }

    @Override
    public boolean isBought(Long id, Long productId, String os) {
        Order order = orderRepository.findUserBoughtOrNot(id, productId, os);
        if (order != null){
            return true;
        }
        return false;
    }
}
