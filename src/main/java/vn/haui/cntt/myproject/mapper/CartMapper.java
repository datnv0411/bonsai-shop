package vn.haui.cntt.myproject.mapper;

import vn.haui.cntt.myproject.dto.CartDto;
import vn.haui.cntt.myproject.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {
    public CartMapper(){super();}

    public static CartDto toCartDto(Cart cart){
        return new CartDto(cart.getId(), cart.getProduct(), cart.getUser(), cart.getQuantity(), cart.getDeletedFlag(),
                cart.getCreatedDate(), cart.getUpdatedDate(), cart.getCreatedBy(), cart.getUpdatedBy());
    }

    public static List<Cart> toListCart(List<CartDto> cart){
        List<Cart> list = new ArrayList<>();
        int i = 0;
        for (CartDto cd : cart
             ) {
            list.add(i, cd.toCart());
            i++;
        }
        return list;
    }
}
