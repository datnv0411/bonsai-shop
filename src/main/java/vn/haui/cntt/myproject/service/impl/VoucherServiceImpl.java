package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Voucher;
import vn.haui.cntt.myproject.repository.VoucherRepository;
import vn.haui.cntt.myproject.service.VoucherService;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public Integer applyVoucher(String voucherCode, Integer subTotal){
        Voucher voucher = voucherRepository.applyVoucher(voucherCode, subTotal);
        Integer discount = 0;
        if (voucher == null){
            return 0;
        } else {
            if(subTotal * voucher.getPercentValue() / 100 > voucher.getUpToValue()){
                discount = voucher.getUpToValue();
            } else {
                discount = subTotal * voucher.getPercentValue() / 100;
            }
        }
        return discount;
    }

    @Override
    public void decreaseVoucher(Voucher voucher, String username) {
        Voucher foundVoucher = voucherRepository.findById(voucher.getId()).orElse(null);
        foundVoucher.setQuantity(voucher.getQuantity() - 1);
        foundVoucher.setUpdatedBy(username);
        foundVoucher.setUpdatedDate(LocalDateTime.now());
        voucherRepository.save(foundVoucher);
    }

    @Override
    public Voucher findByVoucherCode(String voucherCode) {
        return voucherRepository.findByCodeVoucherAndDeletedFlag(voucherCode, false);
    }
}
