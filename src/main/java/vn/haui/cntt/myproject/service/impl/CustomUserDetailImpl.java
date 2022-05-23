package vn.haui.cntt.myproject.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;

import java.util.*;

public class CustomUserDetailImpl implements UserDetails {

    private User user;

    public CustomUserDetailImpl(User user){
        this.user = user;
    }

    public String getFullName(){
        return user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = user.getRole();
        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        for (Role role : roles){
            authority.add(new SimpleGrantedAuthority(role.getName().toString()));
        }
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.user.setUsername(username);
    }

//    public String getEmail(){
//        return user.getUsername();
//    }
}
