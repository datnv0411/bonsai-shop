package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role where name = :name and deleted_flag = 0", nativeQuery = true)
    Role findByName(@Param(value = "name") String name);
}
