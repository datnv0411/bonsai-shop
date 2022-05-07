package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    @Query(value = "select * from voucher " +
            "where start_time < now() and end_time > now() and quantity > 0 " +
            "and code_voucher = :codeVoucher and applicable_value <= :subTotal and deleted_flag = 0"
            , nativeQuery = true)
    Voucher applyVoucher(@Param("codeVoucher") String codeVoucher, @Param("subTotal") long subTotal);

    Voucher findByCodeVoucherAndDeletedFlag(String voucherCode, boolean b);
}
