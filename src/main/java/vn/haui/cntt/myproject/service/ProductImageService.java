package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> listProductImage();

    List<ProductImage> findById(Long productId);

    void deleteProductImage(ProductImage pi, String username);

    void save(ProductImage pi);
}
