package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;
import vn.haui.cntt.myproject.base.BaseProduceDto;
import vn.haui.cntt.myproject.enums.ProductStatusEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private String nameProduct;

    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private ProductStatusEnum status;

    private String productSearch;

    private Integer quantity;

    private String description;

    private long price;

    private long priceSale;

    private String origin;

    @OneToMany(targetEntity = ProductImage.class, mappedBy = "product")
    private Collection<ProductImage> productImages;

    @OneToMany(targetEntity = ProductCategory.class, mappedBy = "product")
    private Collection<ProductCategory> productCategories;

    @OneToMany(targetEntity = ProductComment.class, mappedBy = "product")
    private Collection<ProductComment> productComments;

    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "product")
    private Collection<OrderDetail> orderDetails;
}
