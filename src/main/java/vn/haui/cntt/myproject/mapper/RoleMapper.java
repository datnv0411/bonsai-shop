package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.RoleDto;
import vn.haui.cntt.myproject.entity.Role;

public class RoleMapper {
    public RoleMapper(){super();}

    public static RoleDto toRoleDto(Role role){
        return new RoleDto(role.getId(), role.getName(), role.getDeletedFlag(), role.getCreatedDate(), role.getUpdatedDate(),
                role.getCreatedBy(), role.getUpdatedBy());
    }
}
