package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Role;
import vn.haui.cntt.myproject.enums.RoleEnum;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role where name = :name and deleted_flag = 0", nativeQuery = true)
    Role findByName(@Param(value = "name") String name);

    @Query(value = "select * from role r inner join users_roles ur on r.id = ur.role_id " +
            "where ur.user_id = :userId and r.deleted_flag = 0", nativeQuery = true)
    Role findByUserId(@Param(value = "userId") Long id);

    Role findByNameAndDeletedFlag(RoleEnum name, boolean b);

    List<Role> findAllByDeletedFlag(boolean b);
}
