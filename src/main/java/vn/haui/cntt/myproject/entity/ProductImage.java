package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "product_image")
public class ProductImage extends BaseEntity {

    @Column(columnDefinition = "text")
    private String path;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(columnDefinition = "product_id")
    private Product product;
}
