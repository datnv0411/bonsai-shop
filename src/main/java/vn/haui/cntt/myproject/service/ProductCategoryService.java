package vn.haui.cntt.myproject.service;

import org.springframework.data.domain.Page;
import vn.haui.cntt.myproject.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory findCategoryByProductId(Long productId);

    Page<ProductCategory> listAll(String pageNumber, String sortField, String sortDir);

    ProductCategory findById(Long id);

    void deleteProductCategory(ProductCategory productCategory, String username);

    void save(ProductCategory productCategory);

    List<ProductCategory> findProductByCategoryId(Long id);
}
