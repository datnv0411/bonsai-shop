package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.ProductComment;

import java.util.List;

public interface ProductCommentService {
    List<ProductComment> findAll(Long productId);

    void save(ProductComment productComment);

    ProductComment findById(Long id);

    List<ProductComment> findByProductId(Long id);

    void deleteProductComment(ProductComment pc, String username);
}
