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
@Table(name = "payment_order")
public class PaymentOrder extends BaseEntity {
    @ManyToOne(targetEntity = Payment.class)
    @JoinColumn(columnDefinition = "payment_id")
    private Payment payment;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(columnDefinition = "order_id")
    private Order order;

    private long totalPaid;
    private String status;
}
