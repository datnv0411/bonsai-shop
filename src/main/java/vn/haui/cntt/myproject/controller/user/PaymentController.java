package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.haui.cntt.myproject.entity.Cart;
import vn.haui.cntt.myproject.entity.User;
import vn.haui.cntt.myproject.service.CartService;
import vn.haui.cntt.myproject.service.PaymentService;
import vn.haui.cntt.myproject.service.UserService;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final PaymentService paymentService;



}
