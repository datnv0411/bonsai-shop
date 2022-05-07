package vn.haui.cntt.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.haui.cntt.myproject.base.BaseEntity;
import vn.haui.cntt.myproject.enums.OrderStatusEnum;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "ordered")
public class Order extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatusEnum orderStatus;

    private String orderCode;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(columnDefinition = "user_id")
    private User user;

    @OneToMany(targetEntity = OrderDetail.class, mappedBy = "order")
    private Collection<OrderDetail> orderDetails;

    @ManyToOne(targetEntity = Voucher.class, cascade=CascadeType.ALL)
    @JoinColumn(columnDefinition = "voucher_id")
    private Voucher voucher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address deliveryAddress;

    @OneToMany(targetEntity = PaymentOrder.class, mappedBy = "order")
    private Collection<PaymentOrder> paymentOrders;
}
