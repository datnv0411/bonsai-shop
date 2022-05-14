package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Order;
import vn.haui.cntt.myproject.entity.OrderDetail;
import vn.haui.cntt.myproject.entity.Product;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {
    private long id;
    private Integer quantity;
    private long price;
    private long discount;
    private Order order;
    private Product product;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public OrderDetail toOrderDetail(){
        return OrderDetail.builder()
                .id(id)
                .quantity(quantity)
                .price(price)
                .discount(discount)
                .order(order)
                .product(product)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }
}
