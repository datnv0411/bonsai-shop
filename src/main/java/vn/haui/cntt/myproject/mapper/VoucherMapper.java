package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.VoucherDto;
import vn.haui.cntt.myproject.entity.Voucher;

public class VoucherMapper {
    public VoucherMapper(){super();}

    public static VoucherDto toVoucherDto(Voucher voucher){
        return new VoucherDto(voucher.getId(), voucher.getCodeVoucher(), voucher.getStartTime(), voucher.getEndTime(), voucher.getTimesOfUse(),
                voucher.getQuantity(), voucher.getImage(), voucher.getTitle(), voucher.getPercentValue(), voucher.getUpToValue(),
                voucher.getApplicableValue(), voucher.getOrders(), voucher.getDeletedFlag(), voucher.getCreatedDate(), voucher.getUpdatedDate(),
                voucher.getCreatedBy(), voucher.getUpdatedBy());
    }
}
