package vn.haui.cntt.myproject.repository;

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
            "where ur.role_id = 1 and email = :email and u.deleted_flag = 0 and ur.deleted_flag = 0", nativeQuery = true)
    User findByEmailAndRoleAdmin(@Param(value = "email") String email);

    User findByIdAndDeletedFlag(Long id, boolean b);
}
