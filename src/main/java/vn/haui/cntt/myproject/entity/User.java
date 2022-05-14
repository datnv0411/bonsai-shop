package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;
import vn.haui.cntt.myproject.enums.GenderEnum;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private GenderEnum gender;

    private String avatar;

    @Column(nullable = false, unique = true)
    private String cellphone;

    @Column(name = "reset_password_token", unique = true, length = 45)
    private String resetPasswordToken;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> role = new LinkedHashSet<Role>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Address> address;

    public void addRole(Role role){
        this.role.add(role);
    }

}
