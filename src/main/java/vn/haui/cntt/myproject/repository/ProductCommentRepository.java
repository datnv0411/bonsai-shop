package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.ProductComment;

import java.util.List;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    List<ProductComment> findAllByProductIdAndDeletedFlag(Long productId, boolean b);

    ProductComment findByIdAndDeletedFlag(Long id, boolean b);
}
