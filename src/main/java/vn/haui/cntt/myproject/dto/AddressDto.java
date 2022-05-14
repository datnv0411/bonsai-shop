package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Address;
import vn.haui.cntt.myproject.entity.User;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private long id;
    private boolean isDefault;
    private String typeAddress;
    private String fullName;
    private String cellphone;
    private String province;
    private String district;
    private String town;
    private String specificAddress;
    private User user;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Address toAddress(){
        return Address.builder()
                .id(id)
                .isDefault(isDefault)
                .typeAddress(typeAddress)
                .fullName(fullName)
                .cellphone(cellphone)
                .province(province)
                .district(district)
                .town(town)
                .specificAddress(specificAddress)
                .user(user)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
