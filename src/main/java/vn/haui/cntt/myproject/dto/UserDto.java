package vn.haui.cntt.myproject.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.entity.Address;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.enums.GenderEnum;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private String avatar;
    private String cellphone;
    private String resetPasswordToken;
    private Collection<Role> role= new LinkedHashSet<Role>();
    private Collection<Address> address;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public User toUser(){
        return User.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .gender(gender)
                .avatar(avatar)
                .cellphone(cellphone)
                .resetPasswordToken(resetPasswordToken)
                .address(address)
                .role(role)
                .deletedFlag(deletedFlag)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .updatedBy(updatedBy)
                .updatedDate(updatedDate)
                .build();
    }

    public void addRole(Role role){
        this.role.add(role);
    }
}
