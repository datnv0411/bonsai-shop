package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.entity.User;

import java.util.List;

public interface UserService {

    void createAdmin(User userEntity);

    void createUser(User userEntity);

    String getUserName();

    Boolean checkRoleAdmin(String email);

    Boolean checkRoleUser();

    User getCurrentUser();

    void encodePassword(User user);

    void isRoleUser(User user);

    void isRoleAdmin(User user);

    void setAvatarDefault(User user);

    User updateAccount(User user, String username);

    User updateAccountWithoutPassword(User user, String username);

    User getByUsername(String username);

    Page<User> listAll(String pageStr, String sortField, String sortDir);

    User findById(Long id);

    void deleteUser(User foundUser, String username);

    User save(User user, Role foundRole, String username);

    User saveUser(User user);

    void updateResetPassword(String token, String email) throws Exception;

    User get(String rpt);

    public void updatePassword(User user, String newPass);

    List<User> checkExistUser(String username, String email, String cellphone);

    Page<User> findUser(String page, String sortField, String sortDir, String keySearch);

    boolean decodePass(String password, User user);
}
