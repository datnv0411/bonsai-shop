package vn.haui.cntt.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.CartService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    @Autowired
    private final CartService cartService;
    @Autowired
    private final UserService mUserService;

    @PostMapping("/cart/add")
    public String addProductToCart(@Param(value = "productId") Long productId, @Param(value = "quantity") Integer quantity,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggedUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        String email = loggedUser.getEmail();
        User user = mUserService.getByEmail(email);

        Integer addedQuantity = cartService.addProductToCart(productId, quantity, user);

        return String.valueOf(addedQuantity);
    }

    @PostMapping("/cart/update")
    public String updateQuantity(@Param(value = "productId") Long productId, @Param(value = "quantity") Integer quantity,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        String email = loggedUser.getEmail();
        User user = mUserService.getByEmail(email);

        Long subtotal = cartService.updateQuantity(quantity, productId, user);

        return String.valueOf(subtotal);
    }

    @PostMapping("/count")
    public String countCart(@AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            Integer count = cartService.countCart(user);

            return String.valueOf(count);
        } catch (Exception e){
            return "404";
        }
    }
}
