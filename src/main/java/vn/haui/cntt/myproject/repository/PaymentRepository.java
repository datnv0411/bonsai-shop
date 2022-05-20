package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "select * from payment where payment_name = :name and deleted_flag = 0", nativeQuery = true)
    Payment findByPaymentNameAndDeletedFlag(@Param(value = "name") String name);
}
