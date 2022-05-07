package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> listProductImage();

    List<ProductImage> findById(Long productId);
}
