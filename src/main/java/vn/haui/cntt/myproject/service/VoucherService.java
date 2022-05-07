package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.Voucher;

public interface VoucherService {
    Integer applyVoucher(String voucherCode, Integer subTotal);

    void decreaseVoucher(Voucher voucher, String username);

    Voucher findByVoucherCode(String voucherCode);

    Page<Voucher> listAll(String pageStr, String sortField, String sortDir);

    void save(Voucher voucher);

    Voucher findByIdAndDeletedFlag(Long id);

    void delete(Voucher voucher);
}
