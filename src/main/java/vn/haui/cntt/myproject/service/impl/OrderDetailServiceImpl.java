package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.OrderDetail;
import vn.haui.cntt.myproject.repository.OrderDetailRepository;
import vn.haui.cntt.myproject.service.OrderDetailService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public void save(OrderDetail orderDetail, String username) {
        orderDetail.setDeletedFlag(false);
        orderDetail.setCreatedDate(LocalDateTime.now());
        orderDetail.setCreatedBy(username);
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> addFromCart(List<Cart> carts, String username) {
        List<OrderDetail> list = new ArrayList<>();

        int i=0;
        for (Cart c : carts
        ) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(c.getProduct());
            orderDetail.setQuantity(c.getQuantity());
            orderDetail.setPrice(c.getQuantity() * c.getProduct().getPriceSale());
            orderDetail.setDiscount(c.getQuantity() * c.getProduct().getPriceSale() * 5 / 100);
            orderDetail.setDeletedFlag(false);
            orderDetail.setCreatedBy(username);
            orderDetail.setCreatedDate(LocalDateTime.now());
            list.add(i, orderDetail);

            i++;
        }

        return list;
    }

    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderIdAndDeletedFlag(orderId, false);
    }
}
