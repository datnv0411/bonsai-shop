package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.OrderDetail;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderIdAndDeletedFlag(Long orderId, boolean b);

    @Query(value = "select * from order_detail where " +
            "created_date >= :startTime and created_date <= :endTime and deleted_flag = 0", nativeQuery = true)
    List<OrderDetail> findOrderDetail(@Param(value = "startTime") String startTime,
                                      @Param(value = "endTime") String endTime);

    List<OrderDetail> findAllByDeletedFlag(boolean b);
}
