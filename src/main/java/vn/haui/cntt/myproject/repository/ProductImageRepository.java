package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.ProductImage;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductIdAndDeletedFlag(Long productId, boolean b);

    @Query(value = "select * from product_image where deleted_flag = 0", nativeQuery = true)
    List<ProductImage> findAllWithDeletedFlag();
}
