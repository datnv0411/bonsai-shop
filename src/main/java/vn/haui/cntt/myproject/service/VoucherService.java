package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Voucher;

public interface VoucherService {
    Integer applyVoucher(String voucherCode, Integer subTotal);

    void decreaseVoucher(Voucher voucher, String username);

    Voucher findByVoucherCode(String voucherCode);
}
