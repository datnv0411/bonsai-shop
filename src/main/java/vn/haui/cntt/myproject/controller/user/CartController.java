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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.CartService;
import vn.haui.cntt.myproject.service.ProductCategoryService;
import vn.haui.cntt.myproject.service.ProductService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.List;

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
            return "user/login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            List<Cart> carts = cartService.listCart(user);

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
            return "user/login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            cartService.removeProduct(productId, user);

            return "redirect:/cart";
        } catch (Exception e){
            return "404";
        }
    }
}
