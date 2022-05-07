package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findCategoryByProductId(Long productId);
}
