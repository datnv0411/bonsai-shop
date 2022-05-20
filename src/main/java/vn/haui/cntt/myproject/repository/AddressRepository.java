package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "select * from address a inner join user u on a.user_id=u.id" +
            " where u.username = :username and a.deleted_flag = :deletedFlag and u.deleted_flag = 0", nativeQuery = true)
    Page<Address> findAllByDeletedFlag(@Param(value = "deletedFlag") int i, @Param(value = "username") String username, Pageable pageable);

    @Query(value = "select * from address where id = :addressId and user_id = :userId and deleted_flag = :deletedFlag", nativeQuery = true)
    Address findByUserIdAndAddressId(@Param(value = "deletedFlag") int i, @Param(value = "userId") Long userId, @Param(value = "addressId") Long addressId);

    @Query(value = "select * from address where is_default = :isDefault and deleted_flag = :deletedFlag and user_id = :userId", nativeQuery = true)
    Address findByIsDefault(@Param(value = "deletedFlag") int i, @Param(value = "isDefault") boolean isDefault,
                            @Param(value = "userId") Long userId);

    @Query(value = "select * from address where user_id = :userId and deleted_flag = :deletedFlag and is_default = 1", nativeQuery = true)
    List<Address> findByUserId(@Param(value = "deletedFlag") int i, @Param(value = "userId") Long id);

    Address findByIdAndDeletedFlag(Long i, boolean b);
}
