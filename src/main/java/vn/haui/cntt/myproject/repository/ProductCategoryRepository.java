package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.ProductCategory;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findByProductIdAndDeletedFlag(Long productId, boolean b);

    @Query(value = "select * from product_category where category_id = :categoryId and deleted_flag = :deletedFlag", nativeQuery = true)
    List<ProductCategory> findByCategoryId(@Param(value = "categoryId") Long id, @Param(value = "deletedFlag") int i);

    @Query(value = "select * from product_category where deleted_flag = :deletedFlag", nativeQuery = true)
    Page<ProductCategory> findAllByDeletedFlag(@Param(value = "deletedFlag") int i, Pageable pageable);
}
