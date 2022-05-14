package vn.haui.cntt.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.Product;
import vn.haui.cntt.myproject.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);

    Cart findByUserAndProduct(User user, Product product);

    @Query(value = "update cart c set c.quantity = :quantity, c.updated_by = :updateBy, c.updated_date = :updateDate " +
            "where c.product_id = :productId and c.user_id = :userId", nativeQuery = true)
    @Modifying
    void updateQuantity(@Param(value = "quantity") Integer quantity,
                        @Param(value = "productId") long productId,
                        @Param(value = "userId") long userId,
                        @Param(value = "updateBy") String updateBy,
                        @Param(value = "updateDate") LocalDateTime localDateTime);

    @Query(value = "delete from cart c where c.user_id = :userId and c.product_id = :productId", nativeQuery = true)
    @Modifying
    void deleteByUserIdAndProductId(@Param(value = "productId") long productId,
                                    @Param(value = "userId") long userId);

    Cart findByUserIdAndProductId(Long id, Long productId);
}
