package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.ProductCommentDto;
import vn.haui.cntt.myproject.entity.ProductComment;

public class ProductCommentMapper {
    public ProductCommentMapper(){super();}
    public static ProductCommentDto toProductCommentDto(ProductComment productComment){
        return new ProductCommentDto(productComment.getId(), productComment.getContent(), productComment.getParentId(),
                productComment.getProduct(), productComment.getUser(), productComment.getDeletedFlag(), productComment.getCreatedDate(),
                productComment.getUpdatedDate(), productComment.getCreatedBy(), productComment.getUpdatedBy());
    }
}
