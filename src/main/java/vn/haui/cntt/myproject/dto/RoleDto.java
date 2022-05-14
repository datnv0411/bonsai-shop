package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.enums.RoleEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private long id;
    public RoleEnum name;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Role toRole(){
        return Role.builder()
                .id(id)
                .name(name)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
