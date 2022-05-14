package vn.haui.cntt.myproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    @Query(value = "select * from product where deleted_flag = :deletedFlag and product_search LIKE %:productSearch%", nativeQuery = true)
    Page<Product> findByNameWithoutCategoryId(@Param(value = "deletedFlag") int i, @Param(value = "productSearch") String productSearch, Pageable pageable);

    @Query(value = "select * from product p " +
            "inner join product_category pc on p.id=pc.product_id " +
            "where p.deleted_flag = :deletedFlag and pc.deleted_flag = :deletedFlag2 and pc.category_id = :category_id and product_search LIKE %:productSearch%", nativeQuery = true)
    Page<Product> findByNameWithCategoryId(@Param(value = "deletedFlag") int i,
                                           @Param(value = "deletedFlag2") int j,
                                           @Param(value = "productSearch") String productSearch,
                                           @Param(value = "category_id") String category_id, Pageable pageable);

    @Query(value = "select * from product p " +
            "inner join product_category pc on p.id=pc.product_id " +
            "where p.deleted_flag = 0 and pc.deleted_flag = 0 and pc.category_id = :category_id", nativeQuery = true)
    Page<Product> findAllWithCategoryId(@Param(value = "category_id") String category_id, Pageable pageable);

    @Query(value = "select * from product p " +
            "inner join product_category pc on p.id=pc.product_id " +
            "where p.deleted_flag = 0 and pc.deleted_flag = 0 and pc.category_id = :category_id", nativeQuery = true)
    List<Product> findByCategoryId(@Param(value = "category_id") Long category_id);

    @Query(value = "select * from product p " +
            "where p.deleted_flag = 0", nativeQuery = true)
    Page<Product> findAllWithDeletedFlag(Pageable pageable);

    Product findByIdAndDeletedFlag(Long productId, boolean b);

    @Query(value = "select * from product where deleted_flag = :deletedFlag", nativeQuery = true)
    List<Product> findAllByDeletedFlag(@Param(value = "deletedFlag") int i);
}
