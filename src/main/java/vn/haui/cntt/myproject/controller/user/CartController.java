package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.haui.cntt.myproject.dto.CartDto;
import vn.haui.cntt.myproject.dto.UserDto;
import vn.haui.cntt.myproject.mapper.CartMapper;
import vn.haui.cntt.myproject.mapper.UserMapper;
import vn.haui.cntt.myproject.service.CartService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private final CartService cartService;
    @Autowired
    private final UserService mUserService;

    @GetMapping("/cart")
    public String viewCart(Model model, @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            List<CartDto> carts = cartService.listCart(user.toUser()).stream().map(CartMapper::toCartDto).collect(Collectors.toList());

            model.addAttribute("cartItems", carts);
            model.addAttribute("pageTitle", "Shopping Cart");

            return "user/cart";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/cart/delete")
    public String removeProduct(@Param(value = "productId") Long productId,
                                @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            cartService.removeProduct(productId, user.toUser());

            return "redirect:/cart";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/cart/delete-all")
    public String removeAllProduct(@AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

        try {
            cartService.removeProduct();

            return "redirect:/cart";
        } catch (Exception e){
            return "404";
        }
    }
}
