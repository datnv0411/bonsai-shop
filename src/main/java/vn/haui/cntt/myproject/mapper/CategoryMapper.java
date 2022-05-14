package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.CategoryDto;
import vn.haui.cntt.myproject.entity.Category;

public class CategoryMapper {
    public CategoryMapper(){super();}

    public static CategoryDto toCategoryDto(Category category){
        return new CategoryDto(category.getId(), category.getName(), category.getParentId(), category.getProductCategories(),
                category.getDeletedFlag(), category.getCreatedDate(), category.getUpdatedDate(), category.getCreatedBy(), category.getUpdatedBy());
    }
}
