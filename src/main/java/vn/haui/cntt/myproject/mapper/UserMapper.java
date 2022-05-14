package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.entity.User;

public class UserMapper {
    public UserMapper(){super();}

    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getGender(), user.getAvatar(), user.getCellphone(), user.getRole(), user.getAddress(),
                user.getDeletedFlag(), user.getCreatedDate(), user.getUpdatedDate(), user.getCreatedBy(), user.getUpdatedBy());
    }
}
