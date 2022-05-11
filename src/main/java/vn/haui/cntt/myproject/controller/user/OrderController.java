package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private static final String LOGIN = "admin/auth-login-basic";

    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final PaymentService paymentService;
    @Autowired
    private final OrderDetailService orderDetailService;

    @GetMapping("/order")
    public String getUserDetailInformation(@Param("page") int page, @Param("sortField") String sortField,
                                           @Param("sortDir") String sortDir,
                                           @AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                                           Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            String pageStr = String.valueOf(page);
            Page<Order> pages = orderService.listAll(pageStr, sortField, sortDir, user.getId());
            long totalItems = pages.getTotalElements();
            int totalPages = pages.getTotalPages();
            List<Order> list = pages.getContent();

            model.addAttribute("orders", list);
            model.addAttribute("page", page);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);

            return "user/order";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/order-detail")
    public String viewOrderDetail(Model model,
                                  @Param(value = "orderId") Long orderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            List<OrderDetail> list = orderDetailService.findByOrderId(orderId);

            model.addAttribute("listOrderDetails", list);

            return "user/order-detail";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/create-order")
    public String createOrder(@AuthenticationPrincipal CustomUserDetailImpl loggedUser, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            List<Cart> carts = cartService.listCart(user);
            List<Address> addresses = addressService.findByUserId(user.getId());

            model.addAttribute("addresses", addresses);
            model.addAttribute("cartItems", carts);

            return "user/checkout";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping("/order/cancel")
    public String cancelOrder(@Param(value = "orderId") Long orderId,
                              @AuthenticationPrincipal CustomUserDetailImpl loggedUser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            orderService.cancelOrder(user, orderId);
            return "redirect:/order?page=1&sortField=id&sortDir=des";
        } catch (Exception e){
            return "404";
        }
    }

    @GetMapping(value = "vnpay", name = "responsePaymentVnpay")
    public String checkPaymentVnPay(@RequestParam(name = "vnp_ResponseCode") String vnpResponseCode,
                                    @RequestParam(name = "vnp_TxnRef") String vnpTxnRef,
                                    @RequestParam(name = "vnp_Amount") String vnpAmount,
                                    @AuthenticationPrincipal CustomUserDetailImpl loggedUser){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return LOGIN;
        }
        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);
            paymentService.checkResultPaid(vnpResponseCode, vnpTxnRef , vnpAmount);
            List<Cart> carts = cartService.listCart(user);
            for (Cart c : carts
            ) {
                cartService.deleteCart(c);
            }

            long orderId = Long.parseLong(vnpTxnRef);

            return "redirect:/order-detail?orderId=" + orderId;
        } catch (Exception e){
            return "404";
        }
    }
}
