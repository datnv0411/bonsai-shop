package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.Voucher;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoucherDto {
    private long id;
    private String codeVoucher;
    private String startTime;
    private String endTime;
    private int timesOfUse;
    private int quantity;
    private String image;
    private String title;
    private int percentValue;
    private int upToValue;
    private int applicableValue;
    private Collection<Order> orders;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Voucher toVoucher(){
        return Voucher.builder()
                .id(id)
                .codeVoucher(codeVoucher)
                .startTime(startTime)
                .endTime(endTime)
                .timesOfUse(timesOfUse)
                .quantity(quantity)
                .image(image)
                .title(title)
                .percentValue(percentValue)
                .upToValue(upToValue)
                .applicableValue(applicableValue)
                .orders(orders)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
