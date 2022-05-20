package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from category where name like %:name% and deleted_flag = :deletedFlag", nativeQuery = true)
    Category findByNameAndDeletedFlag(@Param(value = "name") String name, @Param(value = "deletedFlag") boolean b);

    @Query(value = "select * from category where deleted_flag = 0", nativeQuery = true)
    List<Category> findAllWithDeletedFlag();

    @Query(value = "select * from category where deleted_flag = :deletedFlag", nativeQuery = true)
    Page<Category> findAllBYDeletedFlag(@Param(value = "deletedFlag") int i, Pageable pageable);

    @Query(value = "select * from category c where " +
            "concat(c.name) " +
            "like %:keySearch% and deleted_flag = 0", nativeQuery = true)
    Page<Category> findCategory(@Param(value = "keySearch") String productSearch, Pageable pageable);
}
