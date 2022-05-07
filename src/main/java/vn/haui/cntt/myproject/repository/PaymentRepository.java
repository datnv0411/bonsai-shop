package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Payment;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPaymentNameAndDeletedFlag(String name, boolean b);
}
