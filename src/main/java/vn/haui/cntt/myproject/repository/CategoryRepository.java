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
    Category findByNameAndDeletedFlag(String name, boolean b);

    @Query(value = "select * from category where deleted_flag = 0", nativeQuery = true)
    List<Category> findAllWithDeletedFlag();

    @Query(value = "select * from category where deleted_flag = :deletedFlag", nativeQuery = true)
    Page<Category> findAllBYDeletedFlag(@Param(value = "deletedFlag") int i, Pageable pageable);
}
