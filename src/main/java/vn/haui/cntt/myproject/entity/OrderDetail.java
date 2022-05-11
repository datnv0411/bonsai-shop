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
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {

    private Integer quantity;

    private long price;

    private long discount;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(columnDefinition = "order_id")
    private Order order;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(columnDefinition = "product_id")
    private Product product;
}
