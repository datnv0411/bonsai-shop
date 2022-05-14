package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.CartService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    private static final String LOGIN = "admin/auth-login-basic";

    @Autowired
    private final CartService cartService;
    @Autowired
    private final UserService mUserService;

    @PostMapping("/cart/add")
    public String addProductToCart(@Param(value = "productId") Long productId, @Param(value = "quantity") Integer quantity,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggedUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        String email = loggedUser.getEmail();
        UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

        Integer addedQuantity = cartService.addProductToCart(productId, quantity, user.toUser());

        return String.valueOf(addedQuantity);
    }

    @PostMapping("/cart/update")
    public String updateQuantity(@Param(value = "productId") Long productId, @Param(value = "quantity") Integer quantity,
                                   @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        String email = loggedUser.getEmail();
        UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

        Long subtotal = cartService.updateQuantity(quantity, productId, user.toUser());

        return String.valueOf(subtotal);
    }

    @PostMapping("/count")
    public String countCart(@AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            UserDto user = UserMapper.toUserDto(mUserService.getByEmail(email));

            Integer count = cartService.countCart(user.toUser());

            return String.valueOf(count);
        } catch (Exception e){
            return "404";
        }
    }
}
