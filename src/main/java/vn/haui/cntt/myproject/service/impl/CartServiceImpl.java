package vn.haui.cntt.myproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.repository.CartRepository;
import vn.haui.cntt.myproject.repository.ProductRepository;
import vn.haui.cntt.myproject.service.CartService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Cart> listCart(User user){
        return cartRepository.findByUser(user);
    }

    public Integer addProductToCart(Long productId, Integer quantity, User user){
        Integer addedQuantity = quantity;

        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);

        if(cart != null){
            addedQuantity = cart.getQuantity() + quantity;
            cart.setQuantity(addedQuantity);
        } else {
            cart = new Cart();
            cart.setQuantity(quantity);
            cart.setUser(user);
            cart.setProduct(product);
            cart.setCreatedDate(LocalDateTime.now());
            cart.setCreatedBy(user.getUsername());
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        cartRepository.save(cart);

        return addedQuantity;
    }

    public long updateQuantity(Integer quantity, long productId, User user){
        cartRepository.updateQuantity(quantity, productId, user.getId(), user.getUsername(), LocalDateTime.now());
        Product product = productRepository.findById(productId).get();
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        long subtotal = product.getPriceSale() * quantity;
        return subtotal;
    }

    public void removeProduct(Long productId, User user){
        Cart cart = cartRepository.findByUserIdAndProductId(user.getId(), productId);
        Integer qty = cart.getQuantity();
        Product product = productRepository.findById(productId).get();
        product.setQuantity(product.getQuantity() + qty);
        productRepository.save(product);
        cartRepository.delete(cart);
    }

    @Override
    public void deleteCart() {
        cartRepository.deleteAll();
    }

    @Override
    public Integer countCart(User user) {
        List<Cart> carts = cartRepository.findByUser(user);
        return carts.size();
    }
}
