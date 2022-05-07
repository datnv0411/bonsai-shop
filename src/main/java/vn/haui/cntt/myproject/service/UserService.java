package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.User;

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

    User updateAccount(User user);

    User updateAccountWithoutPassword(User user);

    User getByEmail(String email);
}
