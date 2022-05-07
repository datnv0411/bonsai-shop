package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.RoleRepository;
import vn.haui.cntt.myproject.repository.UserRepository;
import vn.haui.cntt.myproject.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository mUserRepository;
    private final RoleRepository mRoleRepository;
    private final PasswordEncoder mPasswordEncoder;
    private final HttpServletRequest mHttpServletRequest;
    //private final UserMapper mUserMapper;
//    private final DeviceMapper mDeviceMapper;
    @Override
    public void createAdmin(User userEntity) {
        userEntity.setPassword(mPasswordEncoder.encode(userEntity.getPassword()));
        List<Role> roleEntityList = mRoleRepository.findAll();
        userEntity.setRole(roleEntityList);
        mUserRepository.save(userEntity);
    }

    @Override
    public void createUser(User userEntity) {
        userEntity.setPassword(mPasswordEncoder.encode(userEntity.getPassword()));
        List<Role> roleEntityList = mRoleRepository.findAllById(Collections.singleton(2L));
        userEntity.setRole(roleEntityList);
        mUserRepository.save(userEntity);
    }

    @Override
    public String getUserName() {
//        String authorizationHeader = mHttpServletRequest.getHeader(AUTHORIZATION);
//        String refreshToken = authorizationHeader.substring("Bearer ".length());
//        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET.getBytes());
//        JWTVerifier verifier = JWT.require(algorithm).build();
//        DecodedJWT decodedJWT = verifier.verify(refreshToken);
//        return decodedJWT.getSubject();
        return null;
    }

    @Override
    public Boolean checkRoleAdmin(String email) {
        User user = mUserRepository.findByEmailAndRoleAdmin(email);
        if(user == null){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean checkRoleUser() {
        return mUserRepository.findByUsername(getUserName()).getRole().stream().anyMatch(o ->
                o.getName().equals("ROLE_USER"));
    }

    @Override
    public User getCurrentUser() {
        return mUserRepository.findByUsername(getUserName());
    }

    @Override
    public void isRoleUser(User user) {
        Role roleUser = mRoleRepository.findByName("ROLE_USER");
        user.addRole(roleUser);
        mUserRepository.save(user);
    }

    @Override
    public void isRoleAdmin(User user) {
        Role roleUser = mRoleRepository.findByName("ROLE_ADMIN");
        user.addRole(roleUser);
        mUserRepository.save(user);
    }

    @Override
    public void setAvatarDefault(User user) {
        user.setAvatar("avatar-default.png");
        user.setCreatedDate(LocalDateTime.now());
        user.setCreatedBy(user.getUsername());
        user.setDeletedFlag(false);
    }

    @Override
    public User updateAccount(User user) {
        User newUser = mUserRepository.findByIdAndDeletedFlag(user.getId(), false);
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCellphone(user.getCellphone());
        newUser.setGender(user.getGender());
        newUser.setAvatar(user.getAvatar());
        newUser.setPassword(user.getPassword());
        encodePassword(newUser);
        return mUserRepository.save(newUser);
    }

    @Override
    public User updateAccountWithoutPassword(User user) {
        User newUser = mUserRepository.findByIdAndDeletedFlag(user.getId(), false);
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCellphone(user.getCellphone());
        newUser.setGender(user.getGender());
        newUser.setAvatar(user.getAvatar());
        return mUserRepository.save(newUser);
    }

    @Override
    public User getByEmail(String email) {
        return mUserRepository.findByEmail(email);
    }

    @Override
    public void encodePassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
