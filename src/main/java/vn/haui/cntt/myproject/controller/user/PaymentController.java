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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.haui.cntt.myproject.dto.*;
import vn.haui.cntt.myproject.mapper.*;
import vn.haui.cntt.myproject.service.*;
import vn.haui.cntt.myproject.service.impl.CustomUserDetailImpl;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentController {
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
    @Autowired
    private final ProductService productService;

    @PostMapping("/save-order")
    public String saveOrder(@AuthenticationPrincipal CustomUserDetailImpl loggedUser,
                            @Param(value = "addressId") String addressId,
                            @Param(value = "voucherCode") String voucherCode,
                            @Param(value = "paymentName") String paymentName,
                            @Param(value = "totalPrice") Long totalPrice,
                            RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/auth-login-basic";
        }

//        try {
            String username = loggedUser.getUsername();
            UserDto user = UserMapper.toUserDto(mUserService.getByUsername(username));

            if(addressId.equals(null) || addressId.equals("null")){
                return "Địa chỉ nhận hàng không được để trống.";
            }

            AddressDto checkAddress = AddressMapper.toAddressDto(addressService.findByAddressId(addressId));

            OrderDto newOrder = new OrderDto();
            OrderDto order = OrderMapper.toOrderDto(orderService.save(newOrder.toOrder()));

            List<CartDto> carts = cartService.listCart(user.toUser()).stream().map(CartMapper::toCartDto).collect(Collectors.toList());
            VoucherDto voucher = new VoucherDto();

            if(voucherCode != null && !voucherCode.equals("")){
                voucher = VoucherMapper.toVoucherDto(voucherService.findByVoucherCode(voucherCode));
                voucherService.decreaseVoucher(voucher.toVoucher(), user.getUsername());
            }

            PaymentDto foundPayment = PaymentMapper.toPaymentDto(paymentService.findByPaymentName(paymentName));

            orderService.save(order.toOrder(), user.toUser(), voucher.toVoucher(), checkAddress.toAddress(), foundPayment.toPayment(), totalPrice);

            List<OrderDetailDto> orderDetail = orderDetailService.addFromCart(order.toOrder(), CartMapper.toListCart(carts), user.getUsername()).stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());

            order.setOrderDetails(OrderDetailMapper.toListOrderDetail(orderDetail));

            if(foundPayment.getPaymentName().equals("Truc Tiep")){
                for (CartDto c : carts
                     ) {
                    cartService.deleteCart(c.toCart());
                }

                List<OrderDetailDto> orderDetailDtos = orderDetailService.findByOrderId(order.getId())
                        .stream().map(OrderDetailMapper::toOrderDetailDto).collect(Collectors.toList());
                for (OrderDetailDto odd : orderDetailDtos
                     ) {
                    ProductDto productDto = ProductMapper.toProductDto(productService.findById(odd.getProduct().getId()));
                    productDto.setQuantity(productDto.getQuantity() - odd.getQuantity());
                    productService.save(productDto.toProduct());
                }

                return "localhost:8080/order?page=1&sortField=id&sortDir=des";
            } else {
                return paymentService.createLink(order.getId(), totalPrice, "13.160.92.202",
                        MvcUriComponentsBuilder.fromController(OrderController.class).toUriString() +"vnpay");
            }

//        } catch (Exception e){
//            return "404";
//        }
    }
}
