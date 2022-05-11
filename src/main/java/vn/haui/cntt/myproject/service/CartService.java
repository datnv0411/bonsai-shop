package vn.haui.cntt.myproject.service;

import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.User;

import java.util.List;

public interface CartService {
    List<Cart> listCart(User user);

    Integer addProductToCart(Long productId, Integer quantity, User user);

    long updateQuantity(Integer quantity, long productId, User user);

    void removeProduct(Long productId, User user);

    void deleteCart();

    Integer countCart(User user);
}
