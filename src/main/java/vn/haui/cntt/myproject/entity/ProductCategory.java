package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "product_category")
public class ProductCategory extends BaseEntity {

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(columnDefinition = "product_id")
    private Product product;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(columnDefinition = "category_id")
    private Category category;
}
