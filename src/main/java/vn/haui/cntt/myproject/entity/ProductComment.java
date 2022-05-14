package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductComment extends BaseEntity {
    @Column(columnDefinition = "text")
    private String content;

    private Long parentId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(columnDefinition = "product_id")
    private Product product;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(columnDefinition = "user_id")
    private User user;
}
