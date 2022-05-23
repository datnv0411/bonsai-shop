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
            "where p.deleted_flag = :deletedFlag and p.category_id = :category_id and product_search LIKE %:productSearch%", nativeQuery = true)
    Page<Product> findByNameWithCategoryId(@Param(value = "deletedFlag") int i,
                                           @Param(value = "productSearch") String productSearch,
                                           @Param(value = "category_id") String category_id, Pageable pageable);

    @Query(value = "select * from product p " +
            "where p.deleted_flag = 0 and p.category_id = :category_id", nativeQuery = true)
    Page<Product> findAllWithCategoryId(@Param(value = "category_id") String category_id, Pageable pageable);

    @Query(value = "select * from product p " +
            "where p.deleted_flag = 0 and p.category_id = :category_id", nativeQuery = true)
    List<Product> findByCategoryId(@Param(value = "category_id") Long category_id);

    @Query(value = "select * from product p " +
            "where p.deleted_flag = 0", nativeQuery = true)
    Page<Product> findAllWithDeletedFlag(Pageable pageable);

    Product findByIdAndDeletedFlag(Long productId, boolean b);

    @Query(value = "select * from product where deleted_flag = :deletedFlag", nativeQuery = true)
    List<Product> findAllByDeletedFlag(@Param(value = "deletedFlag") int i);

    @Query(value = "select * from product p inner join category c on p.category_id = c.id " +
            "inner join supplier s on p.supplier_id = s.id where " +
            "concat(p.product_search, p.origin, c.name, s.name_supplier) " +
            "like %:keySearch% and p.deleted_flag = 0", nativeQuery = true)
    Page<Product> findProduct(@Param(value = "keySearch") String keySearch, Pageable pageable);
}
