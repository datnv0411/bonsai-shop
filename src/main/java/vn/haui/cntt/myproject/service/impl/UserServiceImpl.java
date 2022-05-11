package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return mUserRepository.findByEmailAndRoleAdmin(email).getEmail().isEmpty();
    }

    @Override
    public Boolean checkRoleUser() {
        return mUserRepository.findByUsernameAndDeletedFlag(getUserName(), 0).getRole().stream().anyMatch(o ->
                o.getName().equals("ROLE_USER"));
    }

    @Override
    public User getCurrentUser() {
        return mUserRepository.findByUsernameAndDeletedFlag(getUserName(), 0);
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
    public User updateAccount(User user, String username) {
        User newUser = mUserRepository.findByIdAndDeletedFlag(user.getId(), false);
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCellphone(user.getCellphone());
        newUser.setGender(user.getGender());
        newUser.setAvatar(user.getAvatar());
        newUser.setPassword(user.getPassword());
        encodePassword(newUser);
        newUser.setUpdatedBy(username);
        newUser.setUpdatedDate(LocalDateTime.now());
        return mUserRepository.save(newUser);
    }

    @Override
    public User updateAccountWithoutPassword(User user, String username) {
        User newUser = mUserRepository.findByIdAndDeletedFlag(user.getId(), false);
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCellphone(user.getCellphone());
        newUser.setGender(user.getGender());
        newUser.setAvatar(user.getAvatar());
        newUser.setUpdatedBy(username);
        newUser.setUpdatedDate(LocalDateTime.now());
        return mUserRepository.save(newUser);
    }

    @Override
    public User getByEmail(String email) {
        return mUserRepository.findByEmail(email);
    }

    @Override
    public Page<User> listAll(String pageStr, String sortField, String sortDir) {
        if (pageStr==null || !pageStr.chars().allMatch(Character::isDigit) || pageStr.equals("")) pageStr="1";
        if (sortField==null || sortField.equals("")) sortField="id";
        if (sortDir == null || sortDir.equals("")) sortDir="asc";

        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("des") ? sort.descending() : sort.ascending();

        int pageNumberInt = Integer.parseInt(pageStr);

        Pageable pageable = PageRequest.of(pageNumberInt - 1,5, sort);

        return mUserRepository.findAllUser(0, pageable);
    }

    @Override
    public User findById(Long id) {
        return mUserRepository.findByUserId(id, 0);
    }

    @Override
    public void deleteUser(User foundUser , String username) {
        foundUser.setDeletedFlag(true);
        foundUser.setUpdatedDate(LocalDateTime.now());
        foundUser.setUpdatedBy(username);
        mUserRepository.save(foundUser);
    }

    @Override
    public void save(User user, Role foundRole, String username) {
        user.addRole(foundRole);
        user.setDeletedFlag(false);
        user.setCreatedDate(LocalDateTime.now());
        user.setCreatedBy(username);
        mUserRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        mUserRepository.save(user);
    }

    @Override
    public void updateResetPassword(String token, String email) throws Exception {
        User user = mUserRepository.findByEmail(email);

        if (user != null){
            user.setResetPasswordToken(token);
            mUserRepository.save(user);
        } else {
            throw new Exception("Không tìm thấy thông tin phù hợp!");
        }
    }

    @Override
    public User get(String rpt) {
        return mUserRepository.findByResetPasswordToken(rpt);
    }

    @Override
    public void updatePassword(User user, String newPass) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePass = passwordEncoder.encode(newPass);

        user.setPassword(encodePass);
        user.setResetPasswordToken(null);
        user.setUpdatedBy(user.getUsername());
        user.setUpdatedDate(LocalDateTime.now());

        mUserRepository.save(user);
    }

    @Override
    public void encodePassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
