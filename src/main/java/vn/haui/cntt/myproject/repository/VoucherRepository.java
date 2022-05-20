package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "select * from voucher where deleted_flag = :deletedFlag", nativeQuery = true)
    Page<Voucher> findAllByDeletedFlag(@Param(value = "deletedFlag") Integer i, Pageable pageable);

    @Query(value = "select * from voucher where deleted_flag = :deletedFlag and id = :voucherId", nativeQuery = true)
    Voucher findByIdAndDeletedFlag(@Param("voucherId") Long id, @Param(value = "deletedFlag") Integer i);

    @Query(value = "select * from voucher " +
            "where deleted_flag = 0", nativeQuery = true)
    Page<Voucher> findAllVoucher(Pageable pageable);

    @Query(value = "select * from voucher where " +
            "concat(code_voucher, applicable, end_time, start_time, percent_value, quantity, title, up_to_value) " +
            "like %:keySearch% and deleted_flag = 0", nativeQuery = true)
    Page<Voucher> findVoucher(@Param(value = "keySearch") String keySearch, Pageable pageable);
}
