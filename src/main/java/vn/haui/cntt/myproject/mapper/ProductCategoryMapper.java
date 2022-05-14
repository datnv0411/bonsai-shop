package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.ProductCategoryDto;
import vn.haui.cntt.myproject.entity.ProductCategory;

public class ProductCategoryMapper {
    public ProductCategoryMapper(){super();}

    public static ProductCategoryDto toProductCategoryDto(ProductCategory productCategory){
        return new ProductCategoryDto(productCategory.getId(), productCategory.getProduct(), productCategory.getCategory(),
                productCategory.getDeletedFlag(), productCategory.getCreatedDate(), productCategory.getUpdatedDate(),
                productCategory.getCreatedBy(), productCategory.getUpdatedBy());
    }
}
