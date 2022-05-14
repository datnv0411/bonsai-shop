package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.ProductImageDto;
import vn.haui.cntt.myproject.entity.ProductImage;

import java.util.ArrayList;
import java.util.List;

public class ProductImageMapper {
    public ProductImageMapper(){super();}

    public static ProductImageDto toProductImageDto(ProductImage productImage){
        return new ProductImageDto(productImage.getId(), productImage.getPath(), productImage.getProduct(), productImage.getDeletedFlag(),
                productImage.getCreatedDate(), productImage.getUpdatedDate(), productImage.getCreatedBy(), productImage.getUpdatedBy());
    }

    public static List<ProductImage> toListProductImage(List<ProductImageDto> productImage){
        List<ProductImage> list = new ArrayList<>();
        int i = 0;
        for (ProductImageDto pid : productImage
             ) {
            list.add(i, pid.toProductImage());
            i++;
        }
        return list;
    }
}
