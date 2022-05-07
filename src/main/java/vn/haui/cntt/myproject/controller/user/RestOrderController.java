package vn.haui.cntt.myproject.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import vn.haui.cntt.myproject.entity.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestOrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService mUserService;
    @Autowired
    private final CartService cartService;
    @Autowired
    private final OrderDetailService orderDetailService;
    @Autowired
    private final VoucherService voucherService;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final PaymentService paymentService;

    @PostMapping("/save-order")
    public String saveOrder(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                            @Param(value = "addressId") String addressId,
                            @Param(value = "voucherCode") String voucherCode,
                            @Param(value = "paymentName") String paymentName,
                            @Param(value = "totalPrice") Long totalPrice) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "user/login";
        }

        try {
            String email = loggedUser.getEmail();
            User user = mUserService.getByEmail(email);

            List<Cart> carts = cartService.listCart(user);
            List<OrderDetail> orderDetail = orderDetailService.addFromCart(carts, user.getUsername());
            Voucher voucher = new Voucher();

            if(voucherCode != null && !voucherCode.equals("")){
                voucher = voucherService.findByVoucherCode(voucherCode);
                voucherService.decreaseVoucher(voucher, user.getUsername());
            }

            Payment foundPayment = paymentService.findByPaymentName(paymentName);

            Address checkAddress = addressService.findByAddressId(addressId);

            Order order = new Order();

            orderService.save(order, user, orderDetail, voucher, checkAddress, foundPayment, totalPrice);

            for (OrderDetail od : orderDetail
            ) {
                od.setOrder(order);
                orderDetailService.save(od, user.getUsername());
            }

            cartService.deleteCart();

            if(foundPayment.getPaymentName().equals("Truc Tiep")){
                return "order-detail?orderId=" + order.getId();
            } else {
                String linkVNPAY = paymentService.createLink(order.getId(), totalPrice, "13.160.92.202",
                        MvcUriComponentsBuilder.fromController(OrderController.class).toUriString() +"vnpay");
                return linkVNPAY;
            }

        } catch (Exception e){
            return "404";
        }
    }
}
