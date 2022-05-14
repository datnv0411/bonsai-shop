package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.ProductComment;
import vn.haui.cntt.myproject.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCommentDto {
    private long id;
    private String content;
    private Long parentId;
    private Product product;
    private User user;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public ProductComment toProductComment(){
        return ProductComment.builder()
                .id(id)
                .content(content)
                .parentId(parentId)
                .product(product)
                .user(user)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
