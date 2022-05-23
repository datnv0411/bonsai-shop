package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where email = :email and deleted_flag = 0", nativeQuery = true)
    User findByEmail(@Param(value = "email") String email);

    User findByUsername(String username);

    @Query(value = "select * from user u inner join users_roles ur on u.id=ur.user_id " +
            "where ur.role_id = 1 and username = :username and u.deleted_flag = 0", nativeQuery = true)
    User findByUsernameAndRoleAdmin(@Param(value = "username") String username);

    User findByIdAndDeletedFlag(Long id, boolean b);

    @Query(value = "select * from user where username = :username and deleted_flag = :deletedFlag", nativeQuery = true)
    User findByUsernameAndDeletedFlag(@Param(value = "username") String username, @Param(value = "deletedFlag") int i);

    @Query(value = "select * from user u inner join users_roles ur on u.id = ur.user_id " +
            "where ur.role_id = 2 and u.deleted_flag = :deletedFlag", nativeQuery = true)
    Page<User> findAllUser(@Param(value = "deletedFlag") Integer i, Pageable pageable);

    @Query(value = "select * from user u inner join users_roles ur on u.id = ur.user_id " +
            "where ur.role_id = 2 and u.id = :userId and u.deleted_flag = :deletedFlag", nativeQuery = true)
    User findByUserId(@Param(value = "userId") Long id, @Param(value = "deletedFlag") int i);

    User findByResetPasswordToken(String rpt);

    @Query(value = "select * from user where username = :username or email = :email or cellphone = :cellphone and deleted_flag = 0", nativeQuery = true)
    List<User> checkExistUser(@Param(value = "username") String username, @Param(value = "email") String email, @Param(value = "cellphone") String cellphone);

    @Query(value = "select * from user u inner join users_roles ur on u.id = ur.user_id where " +
            "concat(u.first_name, u.cellphone, u.email, u.last_name) " +
            "like %:keySearch% and deleted_flag = 0 and ur.role_id = 2", nativeQuery = true)
    Page<User> findUser(@Param(value = "keySearch") String keySearch, Pageable pageable);
}
