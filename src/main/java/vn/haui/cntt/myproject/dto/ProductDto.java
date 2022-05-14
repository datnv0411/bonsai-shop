package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.enums.ProductStatusEnum;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private long id;
    private String nameProduct;
    private ProductStatusEnum status;
    private String productSearch;
    private Integer quantity;
    private String description;
    private long price;
    private long priceSale;
    private String origin;
    private Collection<ProductImage> productImages;
    private Collection<ProductCategory> productCategories;
    private Collection<ProductComment> productComments;
    private Collection<OrderDetail> orderDetails;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Product toProduct(){
        return Product.builder()
                .id(id)
                .nameProduct(nameProduct)
                .status(status)
                .productSearch(productSearch)
                .quantity(quantity)
                .description(description)
                .price(price)
                .priceSale(priceSale)
                .origin(origin)
                .productImages(productImages)
                .productCategories(productCategories)
                .productComments(productComments)
                .orderDetails(orderDetails)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
