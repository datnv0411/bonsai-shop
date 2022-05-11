package vn.haui.cntt.myproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.UserRepository;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("User is not found !");
        }
        return new CustomUserDetailImpl(user);
    }
}
