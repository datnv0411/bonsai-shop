package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where email = :email and deleted_flag = 0", nativeQuery = true)
    User findByEmail(@Param(value = "email") String email);

    User findByUsername(String username);

    @Query(value = "select * from user u inner join users_roles ur on u.id=ur.user_id " +
            "where ur.role_id = 1 and email = :email and u.deleted_flag = 0", nativeQuery = true)
    User findByEmailAndRoleAdmin(@Param(value = "email") String email);

    User findByIdAndDeletedFlag(Long id, boolean b);

    @Query(value = "select * from user where username = :username and deleted_flag = :deletedFlag", nativeQuery = true)
    User findByUsernameAndDeletedFlag(@Param(value = "username") String username, @Param(value = "deletedFlag") int i);

    @Query(value = "select * from user u inner join users_roles ur on u.id = ur.user_id " +
            "where ur.role_id = 2 and u.deleted_flag = :deletedFlag", nativeQuery = true)
    Page<User> findAllUser(@Param(value = "deletedFlag") Integer i, Pageable pageable);

    @Query(value = "select * from user u inner join users_roles ur on u.id = ur.user_id " +
            "where ur.role_id = 2 and u.id = :userId and u.deleted_flag = :deletedFlag", nativeQuery = true)
    User findByUserId(@Param(value = "userId") Long id, @Param(value = "deletedFlag") int i);

    public User findByResetPasswordToken(String rpt);
}
