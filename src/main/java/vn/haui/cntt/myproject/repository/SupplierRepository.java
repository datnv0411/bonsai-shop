package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Supplier;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query(value = "select * from supplier where deleted_flag = 0", nativeQuery = true)
    List<Supplier> findAllByDeletedFlag();

    @Query(value = "select * from supplier where deleted_flag = 0", nativeQuery = true)
    Page<Supplier> findAllByDeletedFlag(Pageable pageable);

    Supplier findByIdAndDeletedFlag(Long id, boolean b);

    @Query(value = "select * from supplier where deleted_flag = :deletedFlag and name_supplier like %:name%", nativeQuery = true)
    Supplier findByNameAndDeletedFlag(@Param(value = "name") String name, @Param(value = "deletedFlag") boolean b);

    @Query(value = "select * from supplier s where " +
            "concat(s.name_supplier, s.tax_code, s.cellphone, s.address) " +
            "like %:keySearch% and s.deleted_flag = 0", nativeQuery = true)
    Page<Supplier> findSupplier(@Param(value = "keySearch") String keySearch, Pageable pageable);
}
