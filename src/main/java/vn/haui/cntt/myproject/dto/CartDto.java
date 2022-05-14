package vn.haui.cntt.myproject.dto;

import lombok.*;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.User;

import java.beans.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private long id;
    private Product product;
    private User user;
    private int quantity;
    private Boolean deletedFlag;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String createdBy;
    private String updatedBy;

    public Cart toCart(){
        return Cart.builder()
                .id(id)
                .product(product)
                .user(user)
                .quantity(quantity)
                .deletedFlag(deletedFlag)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .createdBy(createdBy)
                .updatedBy(updatedBy)
                .build();
    }

    @Transient
    public long getSubtotal(){
        return this.product.getPriceSale() * quantity;
    }
}
