package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.ProductDto;
import vn.haui.cntt.myproject.entity.Product;

public class ProductMapper {
    public ProductMapper(){super();}

    public static ProductDto toProductDto(Product product){
        return new ProductDto(product.getId(), product.getNameProduct(), product.getStatus(), product.getProductSearch(),
                product.getQuantity(), product.getDescription(), product.getPrice(), product.getPriceSale(), product.getOrigin(),
                product.getProductImages(), product.getProductCategories(), product.getProductComments(), product.getOrderDetails(),
                product.getDeletedFlag(), product.getCreatedDate(), product.getUpdatedDate(), product.getCreatedBy(), product.getUpdatedBy());
    }
}
