package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Category;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.ProductCategory;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDto {
    private long id;
    private Product product;
    private Category category;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public ProductCategory toProductCategory(){
        return ProductCategory.builder()
                .id(id)
                .product(product)
                .category(category)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
