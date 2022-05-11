package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.PaymentOrder;

import java.util.List;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByOrderIdAndDeletedFlag(long orderId, boolean b);

    PaymentOrder findByOrderId(Long id);

    @Query(value = "select * from payment_order po inner join ordered o on po.order_id = o.id " +
            "where po.status = :status and po.deleted_flag = :deletedFlag " +
            "and po.created_date >= :startTime and po.created_date <= :endTime " +
            "and o.order_status = 'Đã_giao'", nativeQuery = true)
    List<PaymentOrder> findByStatusAndDeletedFlag(@Param(value = "status") String status,
                                                  @Param(value = "deletedFlag") boolean b,
                                                  @Param(value = "startTime") String st,
                                                  @Param(value = "endTime") String et);
}
