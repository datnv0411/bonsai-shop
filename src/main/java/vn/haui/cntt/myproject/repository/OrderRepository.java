package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from ordered o inner join order_detail od on o.id=od.order_id " +
            "where o.user_id = :userId and o.deleted_flag = :deletedFlag and od.deleted_flag = 0 group by o.order_code", nativeQuery = true)
    Page<Order> findByUserId(@Param(value = "deletedFlag") int i, @Param(value = "userId") Long userId, Pageable pageable);

    @Query(value = "select * from ordered where user_id = :userId and id = :orderId and deleted_flag = :deletedFlag", nativeQuery = true)
    Order findByUserIdAndOrderId(@Param(value = "deletedFlag") int i, @Param(value = "userId") Long id, @Param(value = "orderId") Long orderId);

    @Query(value = "select o.id, o.created_by, o.created_date, o.deleted_flag, o.updated_by, o.updated_date, o.order_status, o.user_id, o.order_code, o.address_id, o.voucher_id from ordered o inner join order_detail od on o.id = od.order_id " +
            "where o.user_id = :userId and od.product_id = :productId and o.order_status LIKE :orderStatus%", nativeQuery = true)
    Order findUserBoughtOrNot(@Param(value ="userId") Long id, @Param(value = "productId") Long productId, @Param(value = "orderStatus") String os);

    @Query(value = "select * from ordered where deleted_flag = 0", nativeQuery = true)
    Page<Order> findByDeletedFlag(Pageable pageable);

    Order findByIdAndDeletedFlag(Long id, boolean b);

    List<Order> findByDeletedFlag(boolean b);

    List<Order> findByOrderStatusAndDeletedFlag(OrderStatusEnum status, boolean b);
}
